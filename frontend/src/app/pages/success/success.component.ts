import { Component, OnInit } from '@angular/core';
import {DataService} from '../../business/services/data.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {

  constructor(public data: DataService) { }

  ngOnInit() {
  }

}
