import {Component, OnInit} from '@angular/core';
import {DataManagementService} from './services/data-management.service';
import {Subscription} from 'rxjs/Subscription';
import {Location} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  private sectionsShown = false;

  constructor(private dms: DataManagementService, private location: Location) {
  }

  ngOnInit() {

  }

  onDataLoaded() {
    this.sectionsShown = true;
  }

  navigateBack() {
    this.location.back();
  }
}
