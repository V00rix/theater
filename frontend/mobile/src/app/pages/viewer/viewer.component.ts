import { Component, OnInit } from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
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
  }

  onSubmit() {
    this.data.updateSelection({user: this.data.applicationStatus.user});
    this.router.navigate(['/checkout']);
  }
}
