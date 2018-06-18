import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Router} from '@angular/router';
import {Animations} from '../../../../../shared/animations/animations';
import {Session} from "../../../../../shared/business/domain/session";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss'],
  animations: Animations.animations
})
export class ConfirmationComponent implements OnInit {
  public session: Session = new Session(0,0,0, null, []);

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
    this.data.getSession(this.data.applicationStatus.selectedSession).subscribe((session) => {
      this.session = session;
    });
  }

  onConfirm() {
    // todo: validation
    // http, get order code
    // this.entities.bookingCode = 'F74';
    this.router.navigate(['/success']);
    this.data.createOrder();
  }
}
