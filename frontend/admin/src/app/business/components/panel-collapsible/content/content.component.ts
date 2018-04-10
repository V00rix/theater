import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  template: `<ng-content></ng-content>`,
  styleUrls: ['../panel-collapsible.component.scss']
})
export class ContentComponent implements OnInit {
  constructor() { }

  ngOnInit() {
  }
}
