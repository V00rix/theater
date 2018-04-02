import {Injectable} from '@angular/core';
import {Performance} from '../domain/performance';
import {Session} from '../domain/session';
import {SeatStatus} from '../domain/enumeration/seatStatus.enum';
import {HttpClient} from '@angular/common/http';
import {PerformancesResponse} from '../domain/responces/performancesResponse';
import {Observable} from 'rxjs/Observable';
import {Subscription} from 'rxjs/Subscription';
import {StatusResponse} from '../domain/responces/statusResponse';
import {SelectedSeat} from '../domain/selectedSeat';
import {User} from '../domain/user';
import 'rxjs/add/operator/map';
import {ApplicationStatus} from '../domain/applicationStatus';
import {Checkout} from '../domain/enumeration/checkout';

@Injectable()
export class DataService {

    // todo: move to correct location
    public pages = ['home', 'confirmation', 'contacts', 'performance',
        'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];

    private baseUrl = 'http://localhost';
    public dataLoaded = false;

    public performances: Performance[];
    public maximumSeats: number;

    public applicationStatus: ApplicationStatus;

    public bookingCode: string;

    // todo: implement in my next life
    // public loggedIn: boolean;

    /**
     * Constructor
     * @param {HttpClient} http
     */
    constructor(private http: HttpClient) {
        this.getPerformanceData();
    }

    public getPerformanceData() {
        this.http.get(`${this.baseUrl}/backend/php/requests/performances.php`).subscribe((response: PerformancesResponse) => {
            this.performances = PerformancesResponse.map(response);
            this.maximumSeats = response.maximum_seats;

            this.getStatus().subscribe(() => {
                this.dataLoaded = true;
            });

        });
    }

    /**
     * Post application status
     */
    public postStatus() {
        this.http.post(`${this.baseUrl}/backend/php/requests/status.php`, this.applicationStatus.transform(this.performances),
            {withCredentials: true}).subscribe();
    }

    /**
     * Get application status
     * @returns {Observable<void>}
     */
    public getStatus(): Observable<void> {
        return this.http.get(`${this.baseUrl}/backend/php/requests/status.php`, {withCredentials: true}).map((response: StatusResponse) => {
            this.applicationStatus = StatusResponse.map(response, this.performances);
        });
    }

    /**
     * Update application status
     * @param param Update object
     */
    public updateSelection(param: {
        performance?: Performance,
        session?: Session,
        seats?: SelectedSeat[],
        user?: User,
        checkout?: Checkout
    }) {
        this.applicationStatus.selectedPerformance = param.performance || this.applicationStatus.selectedPerformance;
        this.applicationStatus.selectedSession = param.session || this.applicationStatus.selectedSession;
        this.applicationStatus.selectedSeats = param.seats === null || param.seats === undefined ?
            this.applicationStatus.selectedSeats : param.seats;
        this.applicationStatus.user = param.user || this.applicationStatus.user;
        this.applicationStatus.checkout = param.checkout || this.applicationStatus.checkout;
        this.postStatus();
    }

    /**
     * Post booking request
     */
    public postBooking() {
        this.http.post(`${this.baseUrl}/backend/php/requests/reservation.php`, null, {withCredentials: true}).subscribe((response: string) => {
            this.bookingCode = response;
            return this.getPerformanceData();
        });
    }

}
