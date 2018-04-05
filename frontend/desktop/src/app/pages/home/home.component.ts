import {Component, OnInit,  OnDestroy} from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Router} from '@angular/router';
import {Animations} from '../../../../../shared/animations/animations';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  animations: Animations.animations
})
export class HomeComponent implements OnInit, OnDestroy {
  private timeOut;

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
    if (this.data.performances.length < 2) {
      this.timeOut = setTimeout(() => {
        this.showPerformance();
      }, 1500);
    }
  }

  ngOnDestroy() {
    if (this.timeOut) {
      clearTimeout(this.timeOut);
    }
  }

  showPerformance() {
    this.data.updateSelection({performance: this.data.applicationStatus.selectedPerformance});
    this.router.navigate(['/performance']);
  }
}
