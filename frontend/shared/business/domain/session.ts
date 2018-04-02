import {SeatStatus} from './enumeration/seatStatus.enum';

export class Session {
  constructor(public id: number, public date: Date, public seats: {status: SeatStatus}[][]) {}

  public forSeats(f: (seat, rowIndex, seatIndex) => void) {
    for (let r = 0; r < this.seats.length; r++) {
      for (let s = 0; s < this.seats[r].length; s++) {
        f(this.seats[r][s], r, s);
      }
    }
  }
}
