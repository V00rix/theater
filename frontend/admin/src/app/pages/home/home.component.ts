import {Component, OnInit, Optional} from '@angular/core';
import {Order} from '../../business/domain/order';
import {DataService} from '../../business/services/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  constructor(public data: DataService) {
  }

  ngOnInit() {
    console.log('home init');
  }

  public setOrder(order: Order, status: boolean) {
    order.confirmed = status;
  }
}
