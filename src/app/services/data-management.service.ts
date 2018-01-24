import {Injectable} from '@angular/core';
import {Performance} from '../models/performance';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Session} from '../models/session';
import {Subject} from 'rxjs/Subject';
import {Seat} from '../models/seat';
import {Observable} from 'rxjs/Observable';
import {forEach} from '@angular/router/src/utils/collection';

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
    public getPerformanceDetail(performanceId: number): Observable<{
        title: string, imageUrl: string, description: string, sessions: Date[]
    }> {
        console.log(`Getting ${performanceId}.\'s performance data!`);
        return this.http.get(`${this.baseUrl}/requests/pages/performanceDetail/getPerformanceDetail.php?performanceId=${performanceId}`)
            .map((performanceDetail: { title: string, imageUrl: string, description: string, sessions: string[] }) => {
                return {
                    title: performanceDetail.title,
                    imageUrl: performanceDetail.imageUrl,
                    description: performanceDetail.description,
                    sessions: performanceDetail.sessions.map(s => new Date(parseInt(s)))
                };
            }, error => {
                console.error('Http request failed!', error);
                return error;
            });
    }

    /**
     * Get scene info (seats, time, title)
     * @param {number} performanceId
     * @param {number} sessionTime
     * @returns {Observable<{performanceTitle: String; sessionDateTime: number; seats: Seat[][]}>}
     */
    public getScene(performanceId: number, sessionTime: number): Observable<{
        performanceTitle: String, sessionDateTime: number, seats: Seat[][]
    }> {
        console.log('Getting scene data');
        return this.http.get(`${this.baseUrl}/requests/pages/scene/getScene.php?performanceId=${performanceId}&sessionTime=${sessionTime}`)
            .map((scene: { performanceTitle: String, sessionDateTime: number, seats: Seat[][] }) => {
                    return scene;
                },
                error => {
                    console.warn(error);
                    return error;
                }
            );
    }

    /**
     * Save current selection
     * @param seats
     */
    public saveSeats(seats: { row: number; seat: number }[]) {
        this.selectedSeats = seats;
        console.log('saving seats!');
        this.http.post(`${this.baseUrl}/requests/pages/scene/saveTemporarySeats.php`, seats).subscribe(
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
