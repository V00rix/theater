import {Injectable, Inject} from '@angular/core';

import {DOCUMENT} from '@angular/common';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Order} from '../domain/order';
import {OrderResponse} from '../domain/responces/orderResponse';
import {Subject} from 'rxjs/Subject';
import {Session} from '../domain/session';
import {Performance} from '../domain/performance';
import {SessionResponse} from '../domain/responces/sessionResponse';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Error} from '../domain/error';
import {forkJoin} from 'rxjs/observable/forkJoin';
import {DatePipe} from '@angular/common';

@Injectable()
export class DataService {
  // region Fields
  public loadingFinished = new Subject<void>();
  public dataUpdated = new Subject<void>();
  public errorOccurred = new Subject<Error>();
  public unauthorized = new Subject<string>();

  public orders: Order[] = null;
  public sessions: Session[] = null;
  public performances: Performance[] = null;
  public dataLoaded = false;
  // endregion

  // region Constructor
  constructor(@Inject(DOCUMENT) private document: any,
              private http: HttpClient, private router: Router, private datePipe: DatePipe) {
  }

  // endregion

  // private hostUrl = 'http://localhost:4200';
  private hostUrl = 'http://admin.grani.elumixor.com';
  // private baseUrl = `http://localhost/backend/php/requests/admin/`;
  private baseUrl = 'http://elumixor.com/grani/backend/requests/admin/';

  // region Get init data
  /**
   * Initialization function  to get all application data
   */
  public getApplicationData() {
    console.log('getting application data');

    const ordersRequest = this.getOrders();
    const sessionsRequest = this.getSessions();
    const performancesRequest = this.getPerformances();

    forkJoin([sessionsRequest, ordersRequest, performancesRequest]).subscribe(() => {
      console.log('Data loaded');
      this.dataLoaded = true;
      this.loadingFinished.next();
    });
  }

  /**
   * GET orders
   */
  public getOrders(propagate = true) {
    return this.http.get(`${this.baseUrl}orders.admin.php`, {withCredentials: true}).map(
      (response: OrderResponse) => {
        console.log('Orders loaded');
        console.log(response);
        this.orders = OrderResponse.map(response);
      })
      .catch((e: any) => Observable.throw(this.httpErrorHandler(e, propagate)));
  }

  /**
   * GET sessions
   */
  public getSessions(propagate = true) {
    return this.http.get(`${this.baseUrl}sessions.admin.php`, {withCredentials: true}).map(
      (response: SessionResponse) => {
        console.log('Sessions loaded');
        console.log(response);
        this.sessions = SessionResponse.map(response);
      })
      .catch((e: any) => Observable.throw(this.httpErrorHandler(e, propagate)));
  }

  /**
   * GET performances
   */
  public getPerformances(propagate = true) {
    return this.http.get(`${this.baseUrl}performances.admin.php`, {withCredentials: true}).map(
      (response: { performances: { id: number, title: string, author: string, description: string, imageUrl: string }[] }) => {
        console.log('Performances loaded');
        console.log(response);
        this.performances = response.performances;
      })
      .catch((e: any) => Observable.throw(this.httpErrorHandler(e, propagate)));
  }
  // endregion

  // region Post/update requests
  /**
   * POST authorization
   * @param {string} login
   * @param {string} password
   * @param {boolean} propagate
   * @returns {Promise<T | ErrorObservable>}
   */
  postSubmit(login: string, password: string, propagate = true) {
    return this.http.post(`${this.baseUrl}authorization.admin.php`, {
      login: login,
      password: password
    }, {headers: {'Content-Type': ['text/plain']}, withCredentials: true}).map(
      (res) => {
        console.log('logged in', res);
        return this.getApplicationData();
      })
      .catch((e: any) => {
        return Observable.throw(this.httpErrorHandler(e, propagate));
      });
  }

  /**
   * POST logout
   * @returns {Observable<Object>}
   */
  public logout() {
    return this.http.post(`${this.baseUrl}/logout.admin.php`, null,
      {headers: {'Content-Type': ['text/plain']}, withCredentials: true});
  }

  /**
   * POST save resolved/rejected requests
   */
  saveRequests(propagate = true) {
    return this.http.post(`${this.baseUrl}saveRequests.admin.php`,
      this.orders, {headers: {'Content-Type': ['text/plain']}, withCredentials: true}).subscribe(
      (res) => {
        console.log(res);
        this.orders = null;
        return this.getOrders().subscribe();
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }

  /**
   * POST request to delete user, seats etc.
   * @param {number} order
   * @param {boolean} propagate
   */
  deleteViewer(order: number, propagate = true) {
    this.http.post(`${this.baseUrl}deleteUser.admin.php`,
      {order: order}, {headers: {'Content-Type': ['text/plain']}, withCredentials: true}).subscribe(
      (res) => {
        console.log(res);
        this.sessions = null;
        return this.getSessions().subscribe();
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }

  /**
   * POST request to create new session
   */
  createSession(propagate = true) {
    return this.http.post(`${this.baseUrl}newSession.admin.php`, null, {
      headers: {'Content-Type': ['text/plain']},
      withCredentials: true
    }).subscribe(
      (res: SessionResponse) => {
        console.log('New session created', res);
        this.sessions = this.sessions.concat(SessionResponse.map(res));
        this.dataUpdated.next();
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }

  /**
   * POST request to delete session
   * @param {Session} session
   * @param {boolean} propagate
   */
  deleteSession(session: Session, propagate = true) {
    return this.http.post(`${this.baseUrl}deleteSession.admin.php`, {sessionId: session.id}, {
      headers: {'Content-Type': ['text/plain']},
      withCredentials: true
    }).subscribe(
      (res) => {
        console.log('Session deleted', res);
        this.getSessions().subscribe(() => {
          this.dataUpdated.next();
        });
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }

  /**
   * POST request to update session
   * @param {Session} session
   * @param {boolean} propagate
   */
  updateSession(session: Session, propagate = true) {
    console.log(session.date);
    console.log(this.transformDate(session.date));
    return this.http.post(`${this.baseUrl}updateSession.admin.php`,
      {
        id: session.id,
        performanceId: session.performance.id,
        date: this.transformDate(session.date),
        hall: session.hall
      }, {headers: {'Content-Type': ['text/plain']}, withCredentials: true}).subscribe(
      (res) => {
        console.log('Session updated', res);
        this.dataUpdated.next();
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }
  // endregion

  // region Helpers
  /**
   * Transform javascript date to sql
   * @param {Date} date
   * @returns {string}
   */
  private transformDate(date: Date) {
    console.log(date);
    return this.datePipe.transform(date, 'yyyy-LL-dd HH:mm:ss');
  }

  /**
   * Handler for http errors
   * @param e Error
   * @param {boolean} propagate flag if error should be rethrown
   * @returns {any} Error
   */
  private httpErrorHandler(e, propagate = true) {
    console.warn('Error in Http!');
    console.log(e);
    if (e.status === 401) {
      console.warn('Unauthorized');
      this.unauthorized.next('Unauthorized');
    }
    if (propagate) {
      this.errorOccurred.next(Error.map(e));
    }
    return e;
  }
  // endregion
}
