import {Component, OnInit, Output, HostListener, EventEmitter, Input} from '@angular/core';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['../panel-collapsible.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() active = false;
  @Output() collapsed = new EventEmitter<void>();

  constructor() {
  }

  ngOnInit() {
  }

  @HostListener('click')
  collapsing() {
    this.collapsed.emit();
  }

  @HostListener('click')
  hideContent() {
  }
}
