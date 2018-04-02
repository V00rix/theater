import {User} from './user';
import {SelectedSeat} from './selectedSeat';
import {Checkout} from './enumeration/checkout';
import {Performance} from './performance';
import {Session} from './session';

export class ApplicationStatus {
  constructor(public selectedPerformance: Performance,
              public selectedSession: Session,
              public selectedSeats: SelectedSeat[],
              public checkout: Checkout,
              public user: User) {
  }

  public transform(performances: Performance[]) {
    return {
      selected_performance: performances.indexOf(this.selectedPerformance),
      selected_session: this.selectedPerformance.sessions.indexOf(this.selectedSession),
      selected_seats: this.selectedSeats,
      selected_checkout: this.checkout,
      user: this.user
    };
  }
}
