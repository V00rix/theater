import {Component, OnInit, Input, Output, EventEmitter, Inject} from '@angular/core';
import {Availability} from '../../../../../../shared/business/domain/enumeration/availability';
import {DataService} from '../../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../../shared/animations/animations';
import {Square} from '../../../../../../shared/business/domain/square';
import {SessionComponent} from "../session.component";

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

  constructor(@Inject(SessionComponent) public parent: SessionComponent , public data: DataService) {
  }

  ngOnInit() {
    let seatsNumber = 0;
    console.log(this.parent.session);
    for (const row of this.parent.session.seats) {
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

  switchSeatSelection(seat: Availability) {
    if (seat == Availability.FREE) {
      if (this.selectedSeatsCount < this.data.maximumSeats) {
        seat = Availability.SELECTED;
        this.selectedSeatsCount++;
      }
    } else {
      seat = Availability.FREE;
      this.selectedSeatsCount--;
    }
  }
}
