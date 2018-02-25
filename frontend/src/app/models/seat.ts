export class Seat {
  constructor(public availability: Availability) {
  }
}

export enum Availability {
  Hidden,
  Pending,
  Booked,
  Available
}
