import {AfterViewInit, Component, ContentChild, Input, OnInit} from '@angular/core';
import {NavigationComponent} from '../navigation/navigation.component';
import {SlidePanelContentComponent} from './slidePanelContent/slide-panel-content.component';

@Component({
  selector: 'app-slide-panel',
  templateUrl: './slide-panel.component.html',
  styleUrls: ['./slide-panel.component.scss']
})
export class SlidePanelComponent implements OnInit, AfterViewInit {
  @Input() showOverlay: boolean;
  @ContentChild(NavigationComponent) navigationComponent: NavigationComponent;

  public showPanel: boolean;

  constructor() {
  }

  ngOnInit() {
    this.showPanel = false;
  }

  ngAfterViewInit() {
    this.navigationComponent.close.subscribe((event) => {
      this.showPanel = false;
    });
  }

  onShowPanel() {
    this.showPanel = true;
  }

  onHidePanel(clicked: boolean) {
    if (clicked && this.showOverlay !== false || !clicked && this.showOverlay === false) {
      this.showPanel = false;
    }
  }
}
