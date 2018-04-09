import {Component, OnInit, HostListener, Input} from '@angular/core';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['../panel-collapsible.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() active = false;

  constructor() {
  }

  ngOnInit() {
  }

  @HostListener('click')
  hideContent() {}
}
