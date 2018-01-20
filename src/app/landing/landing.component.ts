import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DataManagementService} from '../services/data-management.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit, AfterViewInit {
  private imagesToLoad: number;
  private loadingStartedTime: number;
  private datePipe = new DatePipe('En');
  @Output() dataLoaded = new EventEmitter<void>();


  constructor(private dms: DataManagementService) {
    this.imagesToLoad = -1;
  }

  ngOnInit() {
  }

  /* After view init we want to load basic performance data */
  ngAfterViewInit() {
    console.log('Basic layout has loaded at ' + this.datePipe.transform(Date.now(), 'HH:mm:ss:SSS'));
    /* Loading basic performance data */
    this.dms.getBasicData().subscribe(() => {
      this.loadingStartedTime = Date.now();
      // Then we change the length of images to load
      // which affects the DOM and causes images to load
      // making the browser cache them
      this.imagesToLoad = this.dms.performances.length;
      console.log('Loading has started at ' + this.datePipe.transform(this.loadingStartedTime, 'HH:mm:ss:ss:SSS'));
    });
  }

  /* After some image has loaded (has gotten cached) we update load progress */
  imageLoaded() {
    console.log('Image has loaded in ' + this.datePipe.transform(Date.now() - this.loadingStartedTime, 'ss:SSS'));
    this.imagesToLoad--;
    console.log(this.imagesToLoad + ' images left to load');
    if (this.imagesToLoad === 0) {
      const loadingFinishedTime = Date.now();
      console.log('Loading finished at: ' + this.datePipe.transform(loadingFinishedTime, 'HH:mm:ss:ss:SSS'));
      console.log('Loading took: ' + this.datePipe.transform(loadingFinishedTime - this.loadingStartedTime, 'ss:SSS'));
      // After the loading has finished we emit an event
      this.dataLoaded.emit();
    }
  }
}
