import { Component, OnInit } from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  constructor(public data: DataService, private router: Router) { }

  ngOnInit() {
  }

  onCheckoutSelected(option: string) {
    this.data.selectedCheckout = option;
    this.router.navigate(['/confirmation']);
  }
}
