import { Component, OnInit } from '@angular/core';
import {DataService} from "../../business/services/data.service";

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss']
})
export class PerformanceComponent implements OnInit {

  constructor(public data: DataService) { }

  ngOnInit() {
  }

}
