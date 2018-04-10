import {Checkout} from '../../../../../shared/business/domain/enumeration/checkout';

export class Session {
  constructor(public id: number,
              public performance: { title: string },
              public date: Date,
              public orders: {
                id: number,
                checkout: Checkout.Code,
                user: { name: string, contact: string },
                seats: { id: number, row: number, seat: number }[],
              }[]) {
  }
}