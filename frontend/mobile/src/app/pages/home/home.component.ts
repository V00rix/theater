import {Component, OnInit} from '@angular/core';
import {Performance} from '../../../../../shared/business/domain/performance';
import {DataService} from '../../../../../shared/business/services/data.service';
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
