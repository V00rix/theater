import { Component, OnInit } from '@angular/core';
import {DataService} from '../../business/services/data.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  constructor(public data: DataService) { }

  ngOnInit() {
  }

}
