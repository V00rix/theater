import {Session} from '../session';
import {Performance} from '../performance';
import {SeatStatus} from '../enumeration/seatStatus.enum';

export class PerformancesResponse {
  public performances: {
    id: number,
    title: string,
    image_url: string,
    description: string,
    sessions: {
      id: number,
      date: Date,
      hall, number,
      seats: string[][]
    }[]
  }[];

  public maximum_seats: number;

  public static map(performancesResponse: PerformancesResponse): Performance[] {
    return performancesResponse.performances.map(
      p =>
        new Performance(p.id, p.title, p.image_url, p.description, p.sessions.map(
          s => {
            return new Session(s.id, new Date(s.date), s.seats.map(
              r => {
                return r.map(
                  seat => {
                    return {
                      status: seat === 'FREE' ? SeatStatus.FREE :
                        seat === 'HIDDEN' ? SeatStatus.HIDDEN : SeatStatus.BOOKED
                    };
                  });
              }).reverse());
          }))
    );
  }
}
