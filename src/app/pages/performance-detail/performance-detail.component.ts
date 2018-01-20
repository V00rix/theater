import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataManagementService} from '../../services/data-management.service';
import {Performance} from '../../models/performance';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-performance-detail',
  templateUrl: './performance-detail.component.html',
  styleUrls: ['./performance-detail.component.scss']
})
export class PerformanceDetailComponent implements OnInit, OnDestroy {
  public performance: Performance;

  constructor(private dms: DataManagementService,
              private route: ActivatedRoute,
              private http: HttpClient) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.performance = this.dms.performances[+params['performanceId']];
      console.warn('Seems like I got some performance data from params!', this.performance);
      console.log('Trying to make an http request...');
      this.http.get('../src/php/test.php').subscribe(data => {
        console.warn('Seems like I got some performance data from Http Request!', data);
      });
    });
  }

  ngOnDestroy() {

  }

}
