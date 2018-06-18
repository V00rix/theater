import {Checkout} from '../../../../../shared/business/domain/enumeration/checkout';

export class Order {
  constructor(public code: number,
              public checkout: Checkout.Code,
              public client: { contact: string, name: string },
              public performance: { title: string },
              public session: { date: Date },
              public seats: { id: number, row: number, seat: number }[],
              public confirmed: boolean) {
  }
}
