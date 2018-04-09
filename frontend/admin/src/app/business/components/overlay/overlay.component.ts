import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-overlay',
  template: `<div class="container overlay">
              <div class="content">
                <ng-content></ng-content>
              </div>
              <label class="footer accent" *ngIf="noClose !== true" (click)="close.emit()">
                {{'close' | translate}}
              </label>
            </div>`,
  styleUrls: ['./overlay.component.scss']
})
export class OverlayComponent implements OnInit {
  @Output() close = new EventEmitter<void>();

  constructor() {
  }

  ngOnInit() {
  }
}
