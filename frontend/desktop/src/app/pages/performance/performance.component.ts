import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../../../shared/business/services/data.service';
import {Router} from '@angular/router';
import {Animations} from '../../../../../shared/animations/animations';
import {Session} from "../../../../../shared/business/domain/session";

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss'],
  animations: Animations.animations
})
export class PerformanceComponent implements OnInit {

  public sessions: Session[];

  constructor(public data: DataService, private router: Router) {
  }

  ngOnInit() {
    this.sessions = this.data.getSessionsByPerformance(this.data.applicationStatus.selectedPerformance);
    console.log(this.sessions);
  }

  showSession(sessionId: number) {
    this.data.updateSelection({
      session: sessionId
    });
    this.router.navigate(['/session']);
  }
}
