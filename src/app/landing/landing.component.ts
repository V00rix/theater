import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../services/data-management.service';
import {DatePipe} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  private imagesToLoad: number;
  private loadingStartedTime: number;
  private datePipe = new DatePipe('En');

  constructor(private dms: DataManagementService, private router: Router, private route: ActivatedRoute) {
    this.imagesToLoad = dms.performances.length;
  }

  imageLoaded() {
    console.log('Image loaded in ' + this.datePipe.transform(Date.now() - this.loadingStartedTime, 'ss:SSS'));
    console.log(--this.imagesToLoad + ' images left to load');
    if (this.imagesToLoad === 0) {
      const loadingFinishedTime = Date.now();
      console.log('Loading finished at: ' + this.datePipe.transform(loadingFinishedTime, 'HH:mm:ss:ss:SSS'));
      console.log('Loading time: ' + this.datePipe.transform(loadingFinishedTime - this.loadingStartedTime, 'ss:SSS'));
      this.router.navigate(['../performances'], { relativeTo: this.route });
    }
  }

  ngOnInit() {
    this.loadingStartedTime = Date.now();
    console.log('Started loading at ' + this.datePipe.transform(this.loadingStartedTime, 'HH:mm:ss:ss:SSS'));
  }
}
