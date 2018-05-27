import { Component, OnInit } from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../shared/animations/animations';
import {Router} from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss'],
  animations: Animations.animations
})
export class ConfirmationComponent implements OnInit {

  constructor(public data: DataService, private router: Router) { }

  ngOnInit() {
  }

  onConfirm() {
    // todo: validation
    // http, get order code
    // this.entities.bookingCode = 'F74';
    this.router.navigate(['/success']);
    this.data.postBooking();
  }
}
