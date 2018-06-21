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

  public selected = '123123';

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
    console.log('saving session', this.temporarySession);

    this.data.sessions[session] = {...this.temporarySession};
    this.temporarySession = null;
    // if (this.temporarySession) {
    //   session.date = this.temporarySession.date;
    //   session.performance = {...this.temporarySession.performance};
    //   session.hall = this.temporarySession.hall;
    // }
    //
    // this.temporarySession = null;
    console.log(session);
    this.data.updateSession(this.data.sessions[session]);
  }

  resetSession(session: number) {
    this.temporarySession = {...this.data.sessions[session]};
    console.log('resetting session');

    // if (session) {
    //
    //   const getDate = (date: Date) => {
    //     return date.getDate() + '.' + date.getMonth() + '.' + date.getFullYear();
    //   };
    //   const getTime = (date: Date) => {
    //     return date.getHours() + ':' + date.getMinutes();
    //   };
    //
    //   if (performanceElement) {
    //     performanceElement.value = session.performance;
    //   }
    //   dateElement.value = getDate(session.date);
    //   timeElement.value = getTime(session.date);
    //
    //   if (hallElement) {
    //     hallElement.value = session.hall;
    //   }
    //
    //   this.temporarySession = null;
    // }
  }

  public createTemporary(session: Session) {

    this.temporarySession$ = new Promise<Session>((resolve, reject) => {
      this.resolve = resolve;
    });

    this.temporarySession = {...session};
    console.log(this.temporarySession);
    console.log(this.data.performances[this.temporarySession.performance].title);
    this.resolve !(this.temporarySession);
  }

  deleteSession(session: number) {
    this.base.confirm(() => this.data.deleteSession(session),
      'You are about to delete session. This action is not reversible. Confirm?');
  }

  newSession() {
    this.data.createSession();
  }

  //
  waitForTemporary = async () => {
    // return this.temporarySession$;
    // this.resolve !(this.temporarySession);
    return (resolve => {
      // this.temporaryCreated.subscribe(() => {
      //   console.log("temporary created");
      //   resolve();
      // })
      //
      setTimeout(() => {
        resolve(4);
      }, 2000);
    });
  }
}
