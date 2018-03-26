import {Injectable} from '@angular/core';
import {Performance} from '../domain/performance';
import {Session} from '../domain/session';

@Injectable()
export class DataService {
  // todo: move to correct location
  public pages = ['home', 'confirmation', 'contacts', 'performance', 'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];

  public performances: Performance[] = [];
  public selectedPerformance: Performance;
  public selectedSession: Session;
  public selectedSeats: { rowIndex: number, seatIndex: number }[];
  public bookingCode: string;
  public loggedIn: boolean;

  public checkoutOptions = ['delivery', 'self', 'payBefore'];
  public selectedCheckout = this.checkoutOptions[0];

  public user: {name: string, contact: string};

  // TODO: test data - erase later
  private static generatePerformances() {
    const performances: Performance[] = [];
    for (let i = 0; i < 3; i++) {
      performances.push({
        title: 'Performance name',
        bg: 'assets/images/bg.png',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam consequat odio id libero bibendum, et pharetra elit tincidunt. Suspendisse at nunc sit amet arcu varius sodales. Morbi convallis turpis maximus libero lobortis, ac efficitur orci aliquam. Donec vulputate mattis ante quis posuere. Nullam elementum ex in felis tempus hendrerit. Vivamus eu gravida ipsum. Sed orci diam, tempor eget scelerisque id, venenatis at libero. In ac auctor odio.',
        sessions: DataService.generateSessions()
      });
    }
    return performances;
  }

  private static generateSessions() {
    const sessions = [];
    for (let i = 0; i < 3; i++) {
      const seats = [];
      for (let r = 0; r < 15; r++) {
        seats[r] = [];
        for (let s = 0; s < 42; s++) {
          seats[r].push({availability: 'free'});
        }
      }
      sessions.push({date: '2018-04-13T19:00:00+0100', seats: seats});
    }
    return sessions;
  }

  constructor() {
    this.performances = DataService.generatePerformances();
    this.selectedPerformance = this.performances[0];
    this.selectedSession = this.selectedPerformance.sessions[0];
    this.selectedSeats = [
      {rowIndex: 3, seatIndex: 5},
      {rowIndex: 3, seatIndex: 5},
      {rowIndex: 4, seatIndex: 5},
      {rowIndex: 7, seatIndex: 11}];
    this.loggedIn = false;
    this.user = {name: 'Vladyslav Yazykov', contact: 'vladogim97@gmail.com'};
    this.bookingCode = 'F74';
  }

}
