import { Component, OnInit } from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  constructor(public data: DataService, private router: Router) { }

  ngOnInit() {
  }

  onConfirm() {
    // todo: validation
    // http, get order code
    // this.data.bookingCode = 'F74';
    this.router.navigate(['/success']);
    this.data.postBooking();
  }
}
