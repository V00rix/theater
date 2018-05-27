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
import {Subject} from 'rxjs/Subject';
import {Square} from '../domain/square';

@Injectable()
export class DataService {
    public windowResized = new Subject<Square>();

    // todo: move to correct location
    public pages = ['home', 'confirmation', 'contacts', 'performance',
        'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];

    // private baseUrl = 'http://elumixor.com/grani/backend/requests/';
    // private baseUrl = 'http://localhost/backend/php/requests/';
    private baseUrl = 'http://localhost:8080/api/';

    // public assetsUrl = 'http://elumixor.com/grani/performanceBackgrounds/';
    public assetsUrl = 'http://localhost:8080/resources/performanceBackgrounds/';

    // private extension = '.php';
    private extension = '';

    public dataLoaded = false;

    public performances: Performance[];
    public maximumSeats: number;

    public applicationStatus: ApplicationStatus;

    public bookingCode: string;
    public viewport: Square;

    // todo: implement in my next life
    // public loggedIn: boolean;

    /**
     * Constructor
     * @param {HttpClient} http
     */
    constructor(private http: HttpClient) {
        this.getPerformanceData();
        this.onResize();
    }

    public onResize() {
        this.viewport = new Square(window.innerWidth, window.innerHeight);
        this.windowResized.next(this.viewport);
    }

    /**
     * http GET
     */
    public getPerformanceData() {
        this.http.get(`${this.baseUrl}performances${this.extension}`).subscribe((response: PerformancesResponse) => {
            console.log(response);
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
        this.http.post(`${this.baseUrl}status${this.extension}`, this.applicationStatus.transform(this.performances),
            // {withCredentials: true, headers: {'Content-Type': ['text/plain']}}
        ).subscribe();
    }

    /**
     * Get application status
     * @returns {Observable<void>}
     */
    public getStatus(): Observable<void> {
        return this.http.get(`${this.baseUrl}status${this.extension}`,
            // {
            //     withCredentials: true,
            //     headers: {'Content-Type': ['text/plain']}
            // }
        ).map((response: StatusResponse) => {
            console.log(response);
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
        checkout?: Checkout.Code
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
        this.http.post(`${this.baseUrl}reservation${this.extension}`, null, {withCredentials: true}).subscribe((response: string) => {
            this.bookingCode = response;
            return this.getPerformanceData();
        });
    }

}
