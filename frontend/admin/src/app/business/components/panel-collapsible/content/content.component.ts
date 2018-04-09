import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  template: `<div class="pc-content"><ng-content></ng-content></div>`,
  styleUrls: ['../panel-collapsible.component.scss']
})
export class ContentComponent implements OnInit {
  constructor() { }

  ngOnInit() {
  }
}
