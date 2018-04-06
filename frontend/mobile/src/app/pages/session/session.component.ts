import {Component, OnInit} from '@angular/core';
import {SeatStatus} from '../../../../../shared/business/domain/enumeration/seatStatus.enum';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Square} from '../../../../../shared/business/domain/square';
import {Animations} from '../../../../../shared/animations/animations';
import {Router} from '@angular/router';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss'],
  animations: Animations.animations
})
export class SessionComponent implements OnInit {
  private selectedSeatsCount: number;
  public isZoomed: boolean;
  public containerWidth: number;
  public containerHeight: number;
  public scaleValue = 1;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {

    this.data.applicationStatus.selectedSession.forSeats((seat) => {
      if (seat.status === SeatStatus.SELECTED) {
        seat.status = SeatStatus.FREE;
      }
    });

    this.selectedSeatsCount = this.data.applicationStatus.selectedSeats.length;
    this.data.applicationStatus.selectedSeats.forEach(s => {
      this.data.applicationStatus.selectedSession.seats[this.data.applicationStatus.selectedSession.seats.length - s.row][s.seat - 1].status
        = SeatStatus.SELECTED;
    });

    let seatsNumber = 0;
    for (const row of this.data.applicationStatus.selectedSession.seats) {
      seatsNumber = seatsNumber < row.length ? row.length : seatsNumber;
    }
    this.containerWidth = seatsNumber * 29 + 50;
    this.containerHeight = this.data.applicationStatus.selectedSession.seats.length * 29;

    if (this.data.viewport) {
      this.calculateDisplayParams(this.data.viewport, this.containerWidth, this.containerHeight);
    }
    this.data.windowResized.subscribe((vp: Square) => {
      this.calculateDisplayParams(vp, this.containerWidth, this.containerHeight);
    });
  }

  /**
   * Calculates display values of session container
   * @param {Square} vp Viewport
   * @param {number} w Container's width
   * @param {number} h Container's height
   */
  private calculateDisplayParams(vp: Square,
                                 w: number,
                                 h: number) {
    if (vp) {

      const x = (.8 * vp.width) / w;
      const y = (.5 * vp.height) / h;

      this.scaleValue = x < y ? x : y;
    }
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
    console.log('confirming');
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

  showZoomed() {
    console.log('showing');
    this.isZoomed = true;
  }

  hideZoomed() {
    console.log('hiding');
    this.isZoomed = false;
  }
}
