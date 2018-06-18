import {Component, OnInit, ElementRef, Inject} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {FormControl, Validators} from '@angular/forms';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {Session} from '../../business/domain/session';
import {AppComponent} from '../../app.component';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss']
})
export class SessionsComponent implements OnInit {
  public timeInput = new FormControl('');

  public temporarySession = null;

  constructor(@Inject(AppComponent) private base: AppComponent, public data: DataService) {
  }

  ngOnInit() {
    console.log('sessions init');
  }

  dateChange(newDate: string, session: Session) {
    console.log('date change');

    this.temporarySession = this.temporarySession ? this.temporarySession : {...session};

    const split = newDate.split(/[./,]+/);
    const day = parseInt(split[0], 10);
    const month = parseInt(split[1], 10) - 1;
    const year = parseInt(split[2], 10);

    const d = this.temporarySession.date;
    const nd = new Date(year, month, day, d.getHours(), d.getMinutes(), d.getSeconds());
    if (!isNaN(nd.getMinutes())) {
      this.temporarySession.date = nd;
    }
    console.log(this.temporarySession.date);
  }

  timeChange(newTime, session: Session) {
    console.log('time change');

    this.temporarySession = this.temporarySession ? this.temporarySession : {...session};

    const split = newTime.split(/[./,:]+/);
    const hours = split[0];
    const minutes = split[1];

    const d = this.temporarySession.date;
    const nd = new Date(d.getFullYear(), d.getMonth(), d.getDate(), hours, minutes);
    if (!isNaN(nd.getMinutes())) {
      this.temporarySession.date = nd;
    }
    console.log(this.temporarySession.date);
  }

  performanceChange(performance, session) {
    this.temporarySession = this.temporarySession ? this.temporarySession : {...session};

    console.log('performance change', performance, session);

    this.temporarySession.performance = performance;
  }

  updateSession(session: Session) {
    console.log('saving session', this.temporarySession);

    if (this.temporarySession) {
      session.date = this.temporarySession.date;
      session.performance = {...this.temporarySession.performance};
      session.hall = this.temporarySession.hall;
    }

    this.temporarySession = null;
    console.log(session);

    this.data.updateSession(session);
  }

  resetSession(session: Session, performanceElement, dateElement, timeElement, hallElement) {
    console.log('resetting session');

    if (session) {

      const getDate = (date: Date) => {
        return date.getDate() + '.' + date.getMonth() + '.' + date.getFullYear();
      };
      const getTime = (date: Date) => {
        return date.getHours() + ':' + date.getMinutes();
      };

      if (performanceElement) {
        performanceElement.value = session.performance;
      }
      dateElement.value = getDate(session.date);
      timeElement.value = getTime(session.date);

      if (hallElement) {
        hallElement.value = session.hall;
      }

      this.temporarySession = null;
    }
  }

  deleteSession(session: number) {
    this.base.confirm(() => this.data.deleteSession(session),
      'You are about to delete session. This action is not reversible. Confirm?');
  }

  newSession() {
    this.data.createSession();
  }
}
