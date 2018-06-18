import {Availability} from './enumeration/availability';

export class Session {
    constructor(public id: number,
                public performance: number,
                public hall: number,
                public date: Date,
                public seats: {status: Availability}[][]) { // object of to make SELECTION work dont change!
    }

    public static forSeats(session: Session, f: (seat, rowIndex, seatIndex) => void) {
        for (let r = 0; r < session.seats.length; r++) {
            for (let s = 0; s < session.seats[r].length; s++) {
                f(session.seats[r][s], r, s);
            }
        }
    }
}
