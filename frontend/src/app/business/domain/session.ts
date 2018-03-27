import {SeatStatus} from './seatStatus.enum';

export class Session {
  constructor(public date: Date, public seats: {status: SeatStatus}[][]) {}
}
