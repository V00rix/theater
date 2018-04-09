import {Order} from '../order';
import {Checkout} from '../../../../../../shared/business/domain/enumeration/checkout';

export class OrderResponse {
  public orders: {
    code: number,
    checkout: string,
    user_name: string,
    user_contact: string,
    performance_title: string,
    session_date: Date,
    seats: { row: number, seat: number }[]
  }[];

  public static map(response: OrderResponse): Order[] {
    return response.orders.map(o => new Order(o.code, Checkout.map(o.checkout), {
      name: o.user_name,
      contact: o.user_contact
    }, {title: o.performance_title}, {date: o.session_date}, o.seats, null));
  }
}
