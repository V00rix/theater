import {Component, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss']
})
export class SessionsComponent implements OnInit {
  constructor(public data: DataService) {
  }

  ngOnInit() {
    console.log('sessions init');
  }
}
