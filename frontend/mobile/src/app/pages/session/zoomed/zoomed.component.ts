import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {Availability} from '../../../../../../shared/business/domain/enumeration/seatStatus.enum';
import {DataService} from '../../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../../shared/animations/animations';


@Component({
  selector: 'app-zoomed',
  templateUrl: './zoomed.component.html',
  styleUrls: ['./zoomed.component.scss'],
  animations: Animations.animations
})
export class ZoomedComponent implements OnInit {
  @Output() onHide = new EventEmitter<void>();
  private selectedSeatsCount: number;
  public containerWidth = 500;

  constructor(public data: DataService) { }

  ngOnInit() {
    let seatsNumber = 0;
    for (const row of this.data.applicationStatus.selectedSession.seats) {
      seatsNumber = seatsNumber < row.length ? row.length : seatsNumber;
    }
    this.containerWidth = seatsNumber * 29 + 50;

    this.selectedSeatsCount = this.data.applicationStatus.selectedSeats.length;
  }

  switchSeatSelection(seat) {
    if (seat.status === Availability.FREE) {
      if (this.selectedSeatsCount < this.data.maximumSeats) {
        seat.status = Availability.SELECTED;
        this.selectedSeatsCount++;
      }
    } else {
      seat.status = Availability.FREE;
      this.selectedSeatsCount--;
    }
  }
}
