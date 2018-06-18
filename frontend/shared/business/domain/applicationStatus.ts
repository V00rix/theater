import {Client} from './client';
import {SelectedSeat} from './selectedSeat';
import {Checkout} from './enumeration/checkout';
import {Performance} from './performance';
import {Session} from './session';

export class ApplicationStatus {
  constructor(public selectedPerformance: number,
              public selectedSession: number,
              public selectedSeats: SelectedSeat[],
              public checkout: Checkout.Code,
              public client: Client) {
  }
}
