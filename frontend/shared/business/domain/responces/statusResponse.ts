import {Session} from '../session';
import {Performance} from '../performance';
import {SeatStatus} from '../enumeration/seatStatus.enum';
import {User} from '../user';
import {SelectedSeat} from '../selectedSeat';
import {ApplicationStatus} from '../applicationStatus';
import {Checkout} from '../enumeration/checkout';

export class StatusResponse {

  public selected_performance: number;
  public selected_session: number;
  public selected_seats: SelectedSeat[];
  public selected_checkout: string;
  public user: User;

  public static map(statusResponse: StatusResponse, performances: Performance[]): ApplicationStatus {
    let selPerformance: Performance;
    let selSession: Session;

    if (performances && performances.length) {
      selPerformance = performances[statusResponse.selected_performance] || performances[0];
      selSession = selPerformance.sessions[statusResponse.selected_session] || selPerformance.sessions[0];

      if (statusResponse.selected_seats && statusResponse.selected_seats.length) {
        for (const seat of statusResponse.selected_seats) {
          selSession.seats[selSession.seats.length - seat.row][seat.seat - 1].status = SeatStatus.SELECTED;
          selSession.seats[selSession.seats.length - seat.row][seat.seat - 1].status = SeatStatus.SELECTED;
        }
      }
    }

    return new ApplicationStatus(
      selPerformance,
      selSession,
      statusResponse.selected_seats || [],
      Checkout.map(statusResponse.selected_checkout),
      statusResponse.user || {name: null, contact: null});
  }
}
