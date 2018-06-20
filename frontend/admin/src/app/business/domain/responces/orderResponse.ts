import {Checkout} from "../checkout";

export class OrderResponse {
  constructor(public id: number,
              public code: string,
              public checkout: Checkout.Code,
              public client: { name: string, contact: string },
              public performance: string,
              public confirmed: boolean,
              public seats: { row: number, seat: number }[]) {
  }
}
