import { Component, OnInit } from '@angular/core';
import {DataService} from "../../business/services/data.service";

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.scss']
})
export class ViewerComponent implements OnInit {

  constructor(public data: DataService) { }

  ngOnInit() {
  }

}
