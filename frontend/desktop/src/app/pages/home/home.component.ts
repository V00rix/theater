import {Component, OnInit} from '@angular/core';
import {DataService} from '../../business/services/data.service';
import {Performance} from '../../business/domain/performance';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
  }

  showPerformance() {
    this.data.updateSelection({performance: this.data.applicationStatus.selectedPerformance});
    this.router.navigate(['/performance']);
  }
}
