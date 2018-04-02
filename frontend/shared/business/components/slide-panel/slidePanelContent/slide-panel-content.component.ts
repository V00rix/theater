import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-slide-panel-content',
  templateUrl: './slide-panel-content.component.html',
  styleUrls: ['./slide-panel-content.component.scss']
})
export class SlidePanelContentComponent implements OnInit {
  @Output() close = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  public onClose() {
    this.close.emit();
  }

}
