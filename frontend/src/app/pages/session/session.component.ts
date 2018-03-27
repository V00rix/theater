import {Component, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {SeatStatus} from '../../business/domain/seatStatus.enum';
import {Router} from '@angular/router';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})
export class SessionComponent implements OnInit {
  private selectedSeats: number;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
    this.selectedSeats = this.data.selectedSeats.length;
  }

  switchSeatSelection(seat) {
    if (seat.status === SeatStatus.FREE) {
      if (this.selectedSeats < this.data.maximumSeats) {
        seat.status = SeatStatus.SELECTED;
        this.selectedSeats++;
      }
    } else {
      seat.status = SeatStatus.FREE;
      this.selectedSeats--;
    }
  }

  onConfirm() {
    console.log(this.data.selectedSession.seats);
    this.data.selectedSeats = [];
    for (let r = 0; r < this.data.selectedSession.seats.length; r++) {
      for (let s = 0; s < this.data.selectedSession.seats[r].length; s++) {
        const seat = this.data.selectedSession.seats[r][s];
        if (seat.status === SeatStatus.SELECTED) {
          this.data.selectedSeats.push({row: this.data.selectedSession.seats.length - r, seat: s + 1});
        }
      }
    }

    if (this.data.selectedSeats.length) {
      this.router.navigate(['/viewer']);
    }
  }
}
