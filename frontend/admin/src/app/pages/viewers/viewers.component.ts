import {Component, OnInit, ContentChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataService} from '../../business/services/data.service';
import {Session} from '../../business/domain/session';
import {BasicPageComponent} from '../../business/components/basic-page/basic-page.component';

@Component({
  selector: 'app-viewers',
  templateUrl: './viewers.component.html',
  styleUrls: ['./viewers.component.scss']
})
export class ViewersComponent implements OnInit {
  @ContentChild(BasicPageComponent) public page: BasicPageComponent;
  public session: Session;
  public paramsRead = false;
  public headers = [{label: 'Viewers', type: 'h2'}];
  public selectedSeat: {id: number, row: number, seat: number};
  public newSeat: {id: number, row: number, seat: number};
  public conflict: string = 'dsa';

  constructor(public data: DataService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    let id: number = null;
    this.route.params.subscribe(params => {
      id = +params['id'];
      this.session = this.data.sessions[id];
      this.paramsRead = true;
      this.headers.push({label: this.data.performances[this.session.performance].title, type: 'h4'},
        {label: this.session.date.toLocaleString(), type: 'h4'});
      console.log('can display', this.session);
    });
  }


  deleteViewer(order: number) {
    this.data.deleteViewer(order);
  }

  changeSeat(seat: {id: number, row: number, seat: number}) {
    this.selectedSeat = seat;
    this.newSeat = { ...this.selectedSeat };
    console.log(this.selectedSeat);
  }

  checkConflict() {
    console.log('checking');
  }

  changeSeatConfirmed() {

  }

  changeSeatRejected() {

  }

  deleteSeat() {
  }
}
