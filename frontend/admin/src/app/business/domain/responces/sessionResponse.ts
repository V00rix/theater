import {Session} from '../session';
import {Checkout} from '../../../../../../shared/business/domain/enumeration/checkout';
import {DateMapper} from '../dateMapper';

export class SessionResponse extends DateMapper {
  public sessions: {
    id: number,
    performance_title: string,
    performance_id: number,
    date: string,
    hall: number,
    orders: {
      id: number,
      user_name: string,
      user_contact: string,
      checkout: string,
      seats: { id: number, row: number, seat: number }[]
    }[]
  }[];

  public static map(response: SessionResponse): Session[] {
    return response.sessions.map(s => new Session(s.id, {title: s.performance_title, id: s.performance_id}, DateMapper.mapDate(s.date), 1,
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
