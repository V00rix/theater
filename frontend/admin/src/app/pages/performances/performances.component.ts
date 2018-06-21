import {Component, Inject, OnInit} from '@angular/core';
import {DataService} from "../../business/services/data.service";
import {Session} from "../../business/domain/session";
import {Performance} from "../../business/domain/performance";
import {AppComponent} from "../../app.component";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-performances',
  templateUrl: './performances.component.html',
  styleUrls: ['./performances.component.scss']
})
export class PerformancesComponent implements OnInit {

  public titleInput = new FormControl('');
  public authorInput = new FormControl('');
  public temporaryPerformance: Performance = null;
  public temporaryPerformance$: Promise<Performance> = null;
  private resolve: Function | null = null;

  constructor(@Inject(AppComponent) private base: AppComponent, public data: DataService) { }

  ngOnInit() {
    console.log('performances init');
  }

  resetPerformance(performance: number) {
    this.temporaryPerformance = {...this.data.performances[performance]};
    console.log('resetting session');
  }

  createTemporary(performance: Performance) {

    this.temporaryPerformance$ = new Promise<Performance>((resolve, reject) => {
      this.resolve = resolve;
    });

    this.temporaryPerformance = {...performance};
    this.resolve !(this.temporaryPerformance);
  }
  deletePerformance(performance: number) {
    this.base.confirm(() => this.data.deletePerformance(performance),
      'You are about to delete a performance. This action is not reversible. Confirm?');
  }
  newPerformance() {
    this.data.createPerformance();
  }
  updatePerformance(performance: number) {

    this.data.performances[performance] = {...this.temporaryPerformance};
    this.temporaryPerformance = null;
    this.data.updatePerformance(this.data.performances[performance]);

  }
}
