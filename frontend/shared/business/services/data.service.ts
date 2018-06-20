import {Injectable} from '@angular/core';
import {Session} from '../domain/session';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SelectedSeat} from '../domain/selectedSeat';
import {Client} from '../domain/client';
import 'rxjs/add/operator/map';
import {ApplicationStatus} from '../domain/applicationStatus';
import {Checkout} from '../domain/enumeration/checkout';
import {Subject} from 'rxjs/Subject';
import {Square} from '../domain/square';
import {PerformanceResponse} from "../domain/responces/performanceResponse";
import {Availability} from "../domain/enumeration/availability";

@Injectable()
export class DataService {
    public windowResized = new Subject<Square>();

    // todo: move to correct location
    public pages = ['home', 'confirmation', 'contacts', 'performance',
        'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];

    // private baseUrl = 'http://elumixor.com/grani/backend/requests/';
    // public assetsUrl = 'http://elumixor.com/grani/performanceBackgrounds/';
    public assetsUrl = 'http://localhost:8080/resources/performanceBackgrounds/';
    public dataLoaded = false;
    public performances = {};
    public performancesIds = [];
    public sessions = {};
    public sessionsIds = [];
    public maximumSeats: number;
    public applicationStatus: ApplicationStatus;
    public bookingCode: string;
    public viewport: Square;
    // private baseUrl = 'http://localhost/backend/php/requests/';
    private baseUrl = 'http://localhost:8080/api/';
    // private extension = '.php';
    private extension = '';

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
     * Get performances
     */
    public getPerformanceData() {
        this.http.get(`${this.baseUrl}complex/performances${this.extension}`).subscribe((response: PerformanceResponse) => {
            console.log(response);
            this.performances = response.performances;
            this.performancesIds = Object.keys(this.performances);
            this.maximumSeats = response.maximumSeats;

            this.getSessions().subscribe(() => {

                this.getStatus().subscribe(() => {
                    this.dataLoaded = true;
                });

            });
        });
    }

    public getSessionsByPerformance(performanceId: number): Session[] {
        const res = [];

        console.log(this.sessionsIds);
        this.sessionsIds.forEach(id => {
            let session = this.sessions[id];
            if (session.performance == performanceId) {
                res.push(session)
            }
        });

        return res;
    }

    /**
     * Get Sessions
     */
    public getSessions() {
        return this.http.get(`${this.baseUrl}session${this.extension}`).map((response: Object) => {
            console.log(response);
            this.sessions = response;
            this.sessionsIds = Object.keys(this.sessions)
        });
    }

    public getSession(sessionId: number): Observable<Session> {
        return this.http.get(`${this.baseUrl}complex/session/${sessionId}${this.extension}`).map((response: { session: Session, seats: Availability[][] }) => {
            // console.log(response);
            response.session.seats = response.seats.map(s => s.map(s1 => {
                return {status: s1};
            }));
            return response.session;
        });
    }


    /**
     * Get application status
     * @returns {Observable<void>}
     */
    public getStatus(): Observable<void> {
        return this.http.get(`${this.baseUrl}complex/status${this.extension}`,
        ).map((response: ApplicationStatus) => {
            if (response.selectedSeats) {
                response.selectedSeats.forEach(s => {
                    s.seat += 1;
                    s.row += 1;
                })
            }
            console.log(response);
            this.applicationStatus = response;
            if (this.performances[this.applicationStatus.selectedPerformance] == null) {
                this.applicationStatus.selectedPerformance = this.performancesIds[0];
            }
            if (this.sessions[this.applicationStatus.selectedSession] == null) {
                this.applicationStatus.selectedSession = this.sessionsIds[0];

            }

            this.applicationStatus.client = this.applicationStatus.client ? this.applicationStatus.client : new Client(null, null);
        });
    }

    /**
     * Update application status
     * @param param Update object
     */
    public updateSelection(param: {
        performance?: number,
        session?: number,
        seats?: SelectedSeat[],
        client?: Client,
        checkout?: Checkout.Code
    }) {
        console.log(param);
        this.applicationStatus.selectedPerformance = param.performance || this.applicationStatus.selectedPerformance;
        this.applicationStatus.selectedSession = param.session || this.applicationStatus.selectedSession;
        this.applicationStatus.selectedSeats = param.seats === null || param.seats === undefined ?
            this.applicationStatus.selectedSeats : param.seats;
        this.applicationStatus.client = param.client || this.applicationStatus.client;
        this.applicationStatus.checkout = param.checkout || this.applicationStatus.checkout;

        if (this.applicationStatus.selectedSeats) {
            this.applicationStatus.selectedSeats.forEach(s => {
                // s.seat -= 1;
                // s.row -= 1;
            })
        }

        this.postStatus();
    }

    /**
     * Post application status
     */
    public postStatus() {
        console.log(this.applicationStatus);
        this.http.post(`${this.baseUrl}complex/status${this.extension}`, this.applicationStatus,
            // {withCredentials: true, headers: {'Content-Type': ['text/plain']}}
        ).subscribe();
    }

    /**
     * Post Order
     */
    public createOrder() {
        this.http.post(`${this.baseUrl}complex/order/new${this.extension}`, null,
            // {withCredentials: true}
            {responseType: 'text'}
        ).subscribe((response: string) => {
            console.log(response);
            this.bookingCode = '' + response;
            return this.getPerformanceData();
        });
    }

}
