import { Component, OnInit } from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.scss']
})
export class ViewerComponent implements OnInit {


  constructor(public data: DataService, private router: Router) { }

  ngOnInit() {
    // get http data if data is null
    if (!this.data.user) {
      this.data.user = {name: null, contact: null};
    }
  }

  onSubmit() {
    this.router.navigate(['/checkout']);
  }
}
