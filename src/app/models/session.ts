import {Seat} from './seat';

export class Session {
  constructor(public date: Date,
              public seats: Seat[][]) {
  }
}
