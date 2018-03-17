import {Availability} from './availability.enum';

export class Session {
  constructor(public date: Date, public seats: Availability[][]) {}
}
