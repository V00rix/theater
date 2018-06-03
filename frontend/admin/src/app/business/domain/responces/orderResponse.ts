import {Order} from '../order';
import {Checkout} from '../../../../../../shared/business/domain/enumeration/checkout';

export class OrderResponse {
  public orders: {
    code: number,
    checkout: string,
    client: {
      name: string,
      email: string,
    }
    session: {
      performance: string,
      date: Date
    },
    seats: { id: number, row: number, seat: number }[]
  }[];

  public static map(response: OrderResponse): Order[] {
    console.log(response);
    return response.orders.map(o => new Order(o.code, Checkout.map(o.checkout), {
      name: o.client.name,
      contact: o.client.email
    }, {title: o.session.performance}, {date: new Date(o.session.date)}, o.seats, null));
  }
}
