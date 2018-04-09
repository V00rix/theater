import {Checkout} from '../../../../../shared/business/domain/enumeration/checkout';

export class Order {
  constructor(public code: number,
              public checkout: Checkout.Code,
              public user: { name: string, contact: string },
              public performance: { title: string },
              public session: { date: Date },
              public seats: { row: number, seat: number }[],
              public isConfirmed: boolean) {
  }
}
