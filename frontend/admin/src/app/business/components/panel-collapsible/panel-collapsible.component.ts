import {Component, OnInit, ViewChild, ContentChild, Input, Optional, ElementRef, AfterViewChecked} from '@angular/core';
import {ContentComponent} from './content/content.component';
import {DrawerComponent} from './drawer/drawer.component';
import {ListComponent} from '../list/list.component';
import {Animations} from '../../../../../../shared/animations/animations';


@Component({
  selector: 'app-panel-collapsible',
  template: `<div class="panel-collapsible" #panel><ng-content select="app-drawer"></ng-content>
                <div class="pc-content" *ngIf="!collapsed" [@fadeIn] [@slideTop]> <ng-content select="app-content" ></ng-content></div>
             </div>`,
  styleUrls: ['./panel-collapsible.component.scss'],
  animations: Animations.animations
})
export class PanelCollapsibleComponent implements OnInit, AfterViewChecked {
  @ContentChild(DrawerComponent) public drawer: DrawerComponent;
  @ContentChild(ContentComponent) public content: ContentComponent;
  @ViewChild('panel') public self: ElementRef;
  @Input() public collapsed = false;

  constructor() {
  }

  ngOnInit() {
    const f = this.drawer.hideContent;
    this.drawer.hideContent = () => {
      f();
      this.collapsed = !this.collapsed;
    };
  }

  ngAfterViewChecked() {
  }

  async loaded() {
    return this.waitFor();
  }

  @Input()
  public waitFor: () => Promise<void>;
}
