import {Component, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {SeatStatus} from '../../business/domain/enumeration/seatStatus.enum';
import {Router} from '@angular/router';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})
export class SessionComponent implements OnInit {
  private selectedSeatsCount: number;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {

    this.data.applicationStatus.selectedSession.forSeats((seat) => {
      if (seat.status === SeatStatus.SELECTED) {
        seat.status = SeatStatus.FREE;
      }
    });

    console.log(this.data.applicationStatus.selectedSeats);

    this.selectedSeatsCount = this.data.applicationStatus.selectedSeats.length;
    this.data.applicationStatus.selectedSeats.forEach(s => {
      this.data.applicationStatus.selectedSession.seats[this.data.applicationStatus.selectedSession.seats.length - s.row][s.seat - 1].status
        = SeatStatus.SELECTED;
    });
  }

  switchSeatSelection(seat) {
    if (seat.status === SeatStatus.FREE) {
      if (this.selectedSeatsCount < this.data.maximumSeats) {
        seat.status = SeatStatus.SELECTED;
        this.selectedSeatsCount++;
      }
    } else {
      seat.status = SeatStatus.FREE;
      this.selectedSeatsCount--;
    }
  }

  onConfirm() {
    this.data.applicationStatus.selectedSeats = [];
    this.data.applicationStatus.selectedSession.forSeats((seat, rowIndex, seatIndex) => {
      if (seat.status === SeatStatus.SELECTED) {
        this.data.applicationStatus.selectedSeats.push(
          {row: this.data.applicationStatus.selectedSession.seats.length - rowIndex, seat: seatIndex + 1});
        seat.status = SeatStatus.FREE;
      }
    });

    if (this.data.applicationStatus.selectedSeats.length) {
      this.data.updateSelection({seats: this.data.applicationStatus.selectedSeats});
      this.router.navigate(['/viewer']);
    }
  }

  resetSeats() {
    this.data.updateSelection({seats: this.data.applicationStatus.selectedSeats = []});
  }
}
