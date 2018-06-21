import {Component, Inject, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {FormControl} from '@angular/forms';
import {Session} from '../../business/domain/session';
import {AppComponent} from '../../app.component';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss']
})
export class SessionsComponent implements OnInit {

  public timeInput = new FormControl('');
  public temporarySession: Session = null;
  public temporarySession$: Promise<Session> = null;
  private resolve: Function | null = null;

  constructor(@Inject(AppComponent) private base: AppComponent, public data: DataService) {
  }

  ngOnInit() {
    console.log('sessions init');
  }

  dateChange(newDate: string) {
    const split = newDate.split(/[./,]+/);
    const day = parseInt(split[0], 10);
    const month = parseInt(split[1], 10) - 1;
    const year = parseInt(split[2], 10);

    const d = this.temporarySession.date;
    const nd = new Date(year, month, day, d.getHours(), d.getMinutes(), d.getSeconds());
    if (!isNaN(nd.getMinutes())) {
      this.temporarySession.date = nd;
    }
  }

  timeChange(newTime, session: Session) {
    console.log('time change');


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

  updateSession(session: number) {

    this.data.sessions[session] = {...this.temporarySession};
    this.temporarySession = null;
    this.data.updateSession(this.data.sessions[session]);
  }

  resetSession(session: number) {
    this.temporarySession = {...this.data.sessions[session]};
    console.log('resetting session');
  }

  public createTemporary(session: Session) {

    this.temporarySession$ = new Promise<Session>((resolve, reject) => {
      this.resolve = resolve;
    });

    this.temporarySession = {...session};
    this.resolve !(this.temporarySession);
  }

  deleteSession(session: number) {
    this.base.confirm(() => this.data.deleteSession(session),
      'You are about to delete a session. This action is not reversible. Confirm?');
  }

  newSession() {
    this.data.createSession();
  }
}
