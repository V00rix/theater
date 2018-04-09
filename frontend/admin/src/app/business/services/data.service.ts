import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import {Order} from '../domain/order';
import {OrderResponse} from '../domain/responces/order-response';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class DataService {
  public loadingFinished = new Subject<void>();

  public orders: Order[];
  public dataLoaded = false;
  public currentErrors = [];
  public sessions: Session;

  constructor(private http: HttpClient) {
  }

  /**
   * Http GET
   */
  public getOrders() {
    return this.http.get('http://localhost/backend/php/requests/admin/orders.admin.php', {withCredentials: true}).map(
      (response: OrderResponse) => {
        console.log(response);
        this.orders = OrderResponse.map(response);
      }, (error) => {
        this.currentErrors = [];
        this.currentErrors.push(error.error.errors);
      });
  }


  /**
   * Http GET
   */
  public getSessions() {
    return this.http.get('http://localhost/backend/php/requests/admin/sessions.admin.php', {withCredentials: true}).map(
      (response: SessionResponse) => {
        console.log(response);
        this.sessions = SessionResponse.map(response);
      }, (error) => {
        this.currentErrors = [];
        this.currentErrors.push(error.error.errors);
      });
  }


  postSubmit(login: string, password: string) {
    return this.http.post('http://localhost/backend/php/requests/admin/authorization.admin.php', {
      login: login,
      password: password
    }, {headers: {'Content-Type': ['text/plain']}}).do(
      (res) => {
        console.log('success', res);
      },
      (error) => {
        this.currentErrors = [];
        this.currentErrors.push(error.error.errors);
      });
  }
}
