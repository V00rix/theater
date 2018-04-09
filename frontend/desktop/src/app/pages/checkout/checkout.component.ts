import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Checkout} from '../../../../../shared/business/domain/enumeration/checkout';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../shared/animations/animations';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  animations: Animations.animations
})
export class CheckoutComponent implements OnInit {
  public checkoutOptions = Object.keys(Checkout.Code).map(k => Checkout.Code[k]);

  constructor(public data: DataService, private router: Router) { }

  ngOnInit() {
  }

  onCheckoutSelected(option: Checkout.Code) {
    this.data.updateSelection({checkout: this.data.applicationStatus.checkout});
    this.data.applicationStatus.checkout = option;
    this.router.navigate(['/confirmation']);
  }
}
