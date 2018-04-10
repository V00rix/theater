import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Order} from '../domain/order';
import {OrderResponse} from '../domain/responces/order-response';
import {Subject} from 'rxjs/Subject';
import {Session} from '../domain/session';
import {SessionResponse} from '../domain/responces/sessionResponse';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Error} from '../domain/error';
import {forkJoin} from 'rxjs/observable/forkJoin';

@Injectable()
export class DataService {
  public loadingFinished = new Subject<void>();
  public errorOccurred = new Subject<Error>();

  public orders: Order[] = null;
  public sessions: Session[] = null;
  public dataLoaded = false;

  constructor(private http: HttpClient, private router: Router) {
  }

  /**
   * GET orders
   */
  public getOrders(propagate = true) {
    return this.http.get('http://localhost/backend/php/requests/admin/orders.admin.php', {withCredentials: true}).map(
      (response: OrderResponse) => {
        console.log('Orders loaded');
        console.log(response);
        this.orders = OrderResponse.map(response);
        console.log(this.orders);
      })
      .catch((e: any) => Observable.throw(this.httpErrorHandler(e, propagate)));
  }


  /**
   * GET sessions
   */
  public getSessions(propagate = true) {
    return this.http.get('http://localhost/backend/php/requests/admin/sessions.admin.php', {withCredentials: true}).map(
      (response: SessionResponse) => {
        console.log('Sessions loaded');
        this.sessions = SessionResponse.map(response);
      })
      .catch((e: any) => Observable.throw(this.httpErrorHandler(e, propagate)));
  }

  /**
   * POST authorization
   * @param {string} login
   * @param {string} password
   * @param {boolean} propagate
   * @returns {Promise<T | ErrorObservable>}
   */
  postSubmit(login: string, password: string, propagate = true) {
    return this.http.post('http://localhost/backend/php/requests/admin/authorization.admin.php', {
      login: login,
      password: password
    }, {headers: {'Content-Type': ['text/plain']}}).map(
      (res) => {
        console.log('logged in', res);
        return this.getApplicationData();
      })
      .catch((e: any) => {
        return Observable.throw(this.httpErrorHandler(e, propagate));
      });
  }

  /**
   * POST save resolved/rejected requests
   */
  saveRequests(propagate = true) {
    return this.http.post('http://localhost/backend/php/requests/admin/saveRequests.admin.php',
      this.orders, {headers: {'Content-Type': ['text/plain']}}).subscribe(
      (res) => {
        console.log(res);
        this.orders = null;
        return this.getOrders().subscribe();
      }, error => {
        this.httpErrorHandler(error, propagate);
      });
  }

  public httpErrorHandler(e, propagate = true) {
    console.warn('Error in Http!');
    console.log(e);
    if (e.status === 401) {
      console.warn('Unauthorized');
      this.router.navigate(['/authorization']);
    }
    if (propagate) {
      this.errorOccurred.next(Error.map(e));
    }
    return e;
  }

  public getApplicationData() {
    console.log('getting application data');

    const ordersRequest = this.getOrders();
    const sessionsRequest = this.getSessions();

    forkJoin([sessionsRequest, ordersRequest]).subscribe(() => {
      console.log('Data loaded');
      this.dataLoaded = true;
      this.loadingFinished.next();
    });
  }
}
