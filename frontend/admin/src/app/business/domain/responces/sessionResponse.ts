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
      code: number,
      client: {
        name: string,
        email: string,
      }
      checkout: string,
      seats: { id: number, row: number, seat: number }[]
    }[]
  }[];

  public static map(response: SessionResponse): Session[] {
    console.log(response);
    return response.sessions.map(s => new Session(s.id, {title: s.performance_title, id: s.performance_id}, DateMapper.mapDate(s.date), 1,
      s.orders.map(o => {
        return {
          id: o.code,
          checkout: Checkout.map(o.checkout),
          user: {name: o.client.name, contact: o.client.email},
          seats: o.seats
        };
      })));
  }
}
