import {Component, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss']
})
export class PerformanceComponent implements OnInit {

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
  }

  showSession(id: number) {
    this.data.selectedSession = this.data.selectedPerformance.sessions[id];
    this.router.navigate(['/session']);
  }
}
