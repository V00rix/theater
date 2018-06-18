import {Component, OnInit} from '@angular/core';
import {Availability} from '../../../../../shared/business/domain/enumeration/availability';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Square} from '../../../../../shared/business/domain/square';
import {Animations} from '../../../../../shared/animations/animations';
import {Router} from '@angular/router';
import {Session} from "../../../../../shared/business/domain/session";

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss'],
  animations: Animations.animations
})
export class SessionComponent implements OnInit {
  public session: Session = new Session(0,0,0,null, []);
  public selectedSeatsCount: number;
  public isWide: boolean;
  public containerWidth: number;
  public containerHeight: number;
  public constraints = new Square(1280, 680);
  public isZoomed = false;
  public scaleValue = 1;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
    this.data.getSession(this.data.applicationStatus.selectedSession).subscribe((session) => {
      this.session = session;

      Session.forSeats(this.session, (seat) => {
        if (seat.status === Availability.SELECTED) {
          seat.status = Availability.FREE;
        }
      });

      this.selectedSeatsCount = this.data.applicationStatus.selectedSeats.length;
      this.data.applicationStatus.selectedSeats.forEach(s => {
        this.session.seats[this.session.seats.length - s.row][s.seat - 1].status
          = Availability.SELECTED;
      });

      let seatsNumber = 0;
      for (const row of this.session.seats) {
        seatsNumber = seatsNumber < row.length ? row.length : seatsNumber;
      }
      this.containerWidth = seatsNumber * 29 + 50;
      this.containerHeight = this.session.seats.length * 29;

      if (this.data.viewport) {
        this.calculateDisplayParams(this.data.viewport, this.containerWidth, this.containerHeight);
      }
      this.data.windowResized.subscribe((vp: Square) => {
        this.calculateDisplayParams(vp, this.containerWidth, this.containerHeight);
      });
    });
  }

  switchSeatSelection(seat) {
    console.log(seat, this.data.maximumSeats, this.selectedSeatsCount);
    if (this.isWide) {
      console.log(seat);
      console.log(seat.status == Availability.FREE);
      if (seat.status == Availability.FREE) {
        if (this.selectedSeatsCount < this.data.maximumSeats) {
          seat.status = Availability.SELECTED;
          this.selectedSeatsCount++;
        }
      } else {
        seat.status = Availability.FREE;
        this.selectedSeatsCount--;
      }
    }
      console.log(seat);
  }

  onConfirm() {
    this.data.applicationStatus.selectedSeats = [];
    Session.forSeats(this.session, (seat, rowIndex, seatIndex) => {
      if (seat.status === Availability.SELECTED) {
        this.data.applicationStatus.selectedSeats.push(
          {row: this.session.seats.length - rowIndex, seat: seatIndex + 1});
        seat.status = Availability.FREE;
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
    if (!this.isWide) {
      this.isZoomed = true;
    }
  }

  hideZoomed() {
    this.isZoomed = false;
  }

  /**
   * Calculates display values of session container
   * @param {Square} vp Viewport
   * @param {number} w Container's width
   * @param {number} h Container's height
   * @param {number} wc Width viewport constraint
   * @param {number} hc Height viewport constraint
   */
  private calculateDisplayParams(vp: Square,
                                 w: number,
                                 h: number,
                                 wc: number = this.constraints.width,
                                 hc: number = this.constraints.height) {
    if (vp) {
      const isWide = vp.isBig(wc, hc);
      this.isWide = isWide;

      const x = ((isWide ? .8 : .4) * vp.width) / w;
      const y = ((isWide ? .5 : .25) * vp.height) / h;

      this.scaleValue = x < y ? x : y;
    }
  }
}
