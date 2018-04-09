import {Component, OnInit, Optional} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {Order} from '../../business/domain/order';
import {MessagesComponent} from '../../business/components/messages/messages.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  public menuShown = false;

  constructor(@Optional() private messages: MessagesComponent, public data: DataService, private router: Router) {
  }

  ngOnInit() {
  }

  setOrder(order: Order, status: boolean) {
    order.isConfirmed = status;
  }

  getSessions() {
    // this.data.sessions ...
    // todo http  get sessions
    // ...
    console.log(this.data.sessions);
  }
}
