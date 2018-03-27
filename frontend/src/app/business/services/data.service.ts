import {Injectable} from '@angular/core';
import {Performance} from '../domain/performance';
import {Session} from '../domain/session';
import {SeatStatus} from '../domain/seatStatus.enum';

@Injectable()
export class DataService {
  // todo: move to correct location
  public pages = ['home', 'confirmation', 'contacts', 'performance', 'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];

  public performances: Performance[] = [];
  public selectedPerformance: Performance;
  public selectedSession: Session;
  public selectedSeats: {row: number, seat: number }[];
  public bookingCode: string;
  public loggedIn: boolean;
  public maximumSeats: number;

  public checkoutOptions = ['delivery', 'self', 'payBefore'];
  public selectedCheckout: string;

  public user: { name: string, contact: string };

  // TODO: test data - erase later
  private static generatePerformances() {
    const performances: Performance[] = [];
    for (let i = 0; i < 3; i++) {
      performances.push({
        title: 'Performance name',
        bg: 'assets/images/bg.png',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam consequat odio id libero bibendum, et pharetra elit tincidunt. Suspendisse at nunc sit amet arcu varius sodales. Morbi convallis turpis maximus libero lobortis, ac efficitur orci aliquam. Donec vulputate mattis ante quis posuere. Nullam elementum ex in felis tempus hendrerit. Vivamus eu gravida ipsum. Sed orci diam, tempor eget scelerisque id, venenatis at libero. In ac auctor odio.',
        // description: null,
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
        for (let s = 0; s < 42 - r; s++) {
          seats[r].push({status: s % 5 === 1 ? SeatStatus.HIDDEN : s % 2 ? SeatStatus.FREE : SeatStatus.BOOKED});
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
    this.selectedSeats = [];
    this.loggedIn = false;
    this.maximumSeats = 5;
  }

}
