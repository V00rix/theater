import {Injectable} from '@angular/core';
import {Performance} from '../models/performance';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Session} from '../models/session';
import {Subject} from 'rxjs/Subject';
import {Seat} from '../models/seat';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class DataManagementService {
    private baseUrl = 'http://localhost/theater/src/php';

    public performances: Performance[];
    public dataStatus = DataStatus.NotFetched;
    public basicDataLoaded = new Subject<void>();
    public performanceDataLoaded = new Subject<void>();
    public sessionDataLoaded = new Subject<void>();
    public sessionData: {
        performanceTitle: String,
        sessionDateTime: number,
        seats: Seat[][]
    } = null;
    selectedSeats: { row: number, seat: number }[];

    constructor(private http: HttpClient) {
        console.log('DMS Loaded!');
    }

    /**
     *  Get images' urls to cache
     *  @returns {Observable<string[]>}
     */
    public getImagesUrls(): Observable<string[]> {
        console.log('Getting image Urls');
        return this.http.get(`${this.baseUrl}/requests/getImagesUrls.php`).map(
            (success: string[]) => {
                return success;
            },
            error => {
                console.warn(error);
                return null;
            }
        );
    }

    /**
     * Get application state
     * @returns {Observable<number>}
     */
    public getAppState(): Observable<number> {
        console.log('Getting application state');
        return this.http.get(`${this.baseUrl}/requests/appState/getAppState.php`).map(
            (appState: AppState) => {
                return appState;
            },
            error => {
                console.warn(error);
                return null;
            }
        );
    }

    // Pages requests
    /**
     * Get performances' images and titles
     * @returns {Observable<{title: string; imageUrl: string}[]>}
     */
    public getPerformances(): Observable<{ title: string, imageUrl: string }[]> {
        console.log('Getting basic performances\' data');
        return this.http.get(`${this.baseUrl}/requests/pages/performances/getPerformances.php`).map(
            (performances: { title: string, imageUrl: string }[]) => {
                return performances;
            },
            error => {
                console.warn(error);
                return error;
            }
        );
    }

    /**
     * Gets performance detail data
     * @param {number} performanceId
     * @returns {Observable<{title: string; imageUrl: string; description: string; sessions: Date[]}>}
     */
    public getPerformanceDetail(performanceId: number): Observable<{ title: string, imageUrl: string, description: string, sessions: Date[] }> {
        console.log(`Getting ${performanceId}.\'s performance data!`);
        this.dataStatus = DataStatus.PerformanceFetchStarted;
        return this.http.get(`${this.baseUrl}/getPerformanceData.php?performanceId=${performanceId}`).subscribe(
            (data: { description: String, sessions: Session[] }) => {
                this.performances[performanceId].description = data.description;
                this.performances[performanceId].sessions = data.sessions
                    .map(session => new Session(new Date(session.date), session.seats));
                this.dataStatus = DataStatus.PerformanceFetchCompleted;
                this.performanceDataLoaded.next();
            }, error => {
                console.error('Http request failed!', error);
            });
    }


    /**
     * Gets performances titles and background images locators
     */
    getBasicData() {
        console.log('Getting basic data!');
        this.dataStatus = DataStatus.BasicFetchStarted;
        this.http.get(`${this.baseUrl}/getBasicData.php`).subscribe(
            data => {
                this.performances = <Performance[]>data;
                console.log('Basic data fetched!');
                this.dataStatus = DataStatus.BasicFetchCompleted;
                this.basicDataLoaded.next();
            }, error => {
                console.error(error);
            }
        );
    }

    /**
     * Gets session data (seats)
     * @param performanceId
     * @param {number} sessionTime
     */
    getSessionData(performanceId: number, sessionTime: number) {
        console.log(`Getting session ${sessionTime} data of performance ${performanceId}!`);
        this.dataStatus = DataStatus.SessionFetchStarted;
        this.http.get(`${this.baseUrl}/getSessionData.php?performanceId=${performanceId}&sessionTime=${sessionTime}`)
            .subscribe(
                (data: {
                    performanceTitle: String,
                    sessionDateTime: number,
                    seats: Seat[][]
                }) => {
                    this.sessionData = data;
                    this.dataStatus = DataStatus.SessionFetchCompleted;
                    this.sessionDataLoaded.next();
                }, error => {
                    console.error('Http request failed!', error);
                });
    }


    saveSeats(seats: { row: number; seat: number }[]) {
        this.selectedSeats = seats;
        console.log('saving seats!');
        this.http.post(`${this.baseUrl}/saveSeats.php`, seats).subscribe(
            success => {
                console.log(success);
            },
            error => {
                console.log(error);
            }
        );
    }

    getSeats(): Promise<any> {
        console.log('fetching seats!');
        this.http.get(`${this.baseUrl}/getSeats.php`).subscribe(
            success => {
                console.log(success);
            },
            error => {
                console.log(error);
            }
        );
        return new Promise<any>((resolve, reject) => {
            console.log(this.selectedSeats);
            if (!this.selectedSeats || this.selectedSeats === undefined) {
                this.http.get(`${this.baseUrl}/getSeats.php`).toPromise().then(
                    success => {
                        console.log(success);
                        resolve(success);
                    },
                    error => {
                        console.log(error);
                        reject(error);
                    }
                );
            } else {
                resolve(this.selectedSeats);
            }
        });
    }


}

export enum DataStatus {
    NotFetched = 0,
    BasicFetchStarted = 1,
    BasicFetchCompleted = 2,
    PerformanceFetchStarted = 3,
    PerformanceFetchCompleted = 4,
    SessionFetchStarted = 5,
    SessionFetchCompleted = 6
}

export enum AppState {
    Landing = 0,
    Performances = 1,
    PerformanceDetail = 2,
    Scene = 3,
    PersonalData = 4,
    Confirmation = 5,
    Success = 6
}
