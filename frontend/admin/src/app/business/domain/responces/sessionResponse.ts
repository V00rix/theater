import {Session} from '../session';
import {Checkout} from '../../../../../../shared/business/domain/enumeration/checkout';

export class SessionResponse {
  public sessions: {
    id: number,
    performance_title: string,
    date: Date,
    orders: {
      id: number,
      user_name: string,
      user_contact: string,
      checkout: string,
      seats: { id: number, row: number, seat: number }[]
    }[]
  }[];

  public static map(response: SessionResponse): Session[] {
    return response.sessions.map(s => new Session(s.id, {title: s.performance_title}, s.date,
      s.orders.map(o => {
        return {
          id: o.id,
          checkout: Checkout.map(o.checkout),
          user: {name: o.user_name, contact: o.user_contact},
          seats: o.seats
        };
      })));
  }
}
