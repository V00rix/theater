import { Component, OnInit } from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Animations} from '../../../../../shared/animations/animations';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss'],
  animations: Animations.animations
})
export class SuccessComponent implements OnInit {

  constructor(public data: DataService) { }

  ngOnInit() {
  }

  updateStatus() {
    this.data.getStatus().subscribe();
  }

}
