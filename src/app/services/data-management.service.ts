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
    private baseUrl = './php';

    public performances: Performance[];
    public seatsSaved = true;
    public seatsSavedCompleted = new Subject<void>();
    public personalDataSaved = true;
    public personalDataSavedCompleted = new Subject<void>();

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

    /* Pages requests */

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
     * Get scene info (personalData, time, title)
     * @param {number} performanceId
     * @param {number} sessionTime
     * @returns {Observable<{performanceTitle: String; sessionDateTime: number; personalData: Seat[][]}>}
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
    public saveSeats(seats: { row: number; seat: number }[]): void {
        this.seatsSaved = false;
        console.log('saving personalData!');
        this.http.post(`${this.baseUrl}/requests/pages/scene/saveTemporarySeats.php`, seats).subscribe(
            success => {
                console.log(success);
                this.seatsSaved = true;
                this.seatsSavedCompleted.next();
            },
            error => {
                console.log(error);
            }
        );
    }

    /**
     * Get selected personal data
     * @returns {Observable<{row: number; seat: number}[]>}
     */
    public getSeats(): Observable<{ row: number, seat: number }[]> {
        console.log('fetching personal data!');
        return this.http.get(`${this.baseUrl}/requests/pages/getTemporarySeats.php`).map(
            (success: { row: number, seat: number }[]) => {
                return success;
            },
            error => {
                console.log(error);
            }
        );
    }

    /**
     * Save user personal information
     * @param {SeatWithUserData[]} pData
     */
    public savePersonalData(pData: {
        row: number, seat: number,
        userData?: {
            name?: string,
            phone?: string,
            email?: string,
            vk?: string,
            whatsApp?: string,
            viber?: string,
            telegram?: string
        }
    }[]) {
        this.personalDataSaved = false;
        console.log('Saving personal data!', pData);
        this.http.post(`${this.baseUrl}/requests/pages/personalData/savePersonalData.php`, pData).subscribe(
            success => {
                console.log(success);
                this.personalDataSaved = true;
                this.personalDataSavedCompleted.next();
            },
            error => {
                console.log(error);
            }
        );
    }

    /**
     * Get confirmation data (personal info per personalData)
     * @returns {Observable<{row: number; seat: number}[]>}
     */
    public getConfirmationData(): Observable<{
        row: number, seat: number,
        userData?: {
            name?: string,
            phone?: string,
            email?: string,
            vk?: string,
            whatsApp?: string,
            viber?: string,
            telegram?: string
        }
    }[]> {
        console.log('Getting confirmation data!');
        return this.http.get(`${this.baseUrl}/requests/pages/confirmation/getConfirmation.php`).map(
            (success: {
                row: number, seat: number,
                userData?: {
                    name?: string,
                    phone?: string,
                    email?: string,
                    vk?: string,
                    whatsApp?: string,
                    viber?: string,
                    telegram?: string
                }
            }[]) => {
                return success;
            },
            error => {
                console.log(error);
            }
        );
    }

    /**
     * Post final request on reservation confirmation
     */
    public postReservationRequest(): void {
        console.log('Getting confirmation data!');
        this.http.get(`${this.baseUrl}/requests/pages/confirmation/postReservation.php`).subscribe(
            success => {
                console.log(success);
            },
            error => {
                console.log(error);
            }
        );
    }
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
