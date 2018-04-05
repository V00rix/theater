import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {SeatStatus} from '../../../../../../shared/business/domain/enumeration/seatStatus.enum';
import {DataService} from '../../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../../shared/animations/animations';
import {Square} from '../../../../../../shared/business/domain/square';

@Component({
  selector: 'app-zoomed',
  templateUrl: './zoomed.component.html',
  styleUrls: ['./zoomed.component.scss'],
  animations: Animations.animations
})
export class ZoomedComponent implements OnInit {
  @Output() onHide = new EventEmitter<void>();
  @Input() constraints: Square;
  private selectedSeatsCount: number;
  public containerWidth = 500;

  constructor(public data: DataService) {
  }

  ngOnInit() {
    let seatsNumber = 0;
    for (const row of this.data.applicationStatus.selectedSession.seats) {
      seatsNumber = seatsNumber < row.length ? row.length : seatsNumber;
    }
    this.containerWidth = seatsNumber * 29 + 50;
    this.selectedSeatsCount = this.data.applicationStatus.selectedSeats.length;

    this.data.windowResized.subscribe((vp: Square) => {
      if (this.constraints && vp.isBigC(this.constraints)) {
        this.onHide.emit();
      }
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
}
