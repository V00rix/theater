import {
  Component,
  ContentChildren,
  QueryList,
  AfterContentInit,
  ViewChild,
  ViewChildren,
  ElementRef
} from '@angular/core';
import {PanelCollapsibleComponent} from '../panel-collapsible/panel-collapsible.component';
import {DrawerComponent} from '../panel-collapsible/drawer/drawer.component';
import {DialogComponent} from '../dialog/dialog.component';
import {Animations} from '../../../../../../shared/animations/animations';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
  animations: Animations.animations
})
export class ListComponent implements AfterContentInit {
  @ContentChildren(PanelCollapsibleComponent) public panels: QueryList<PanelCollapsibleComponent>;
  public drawerHeight: { top: number, bottom: number } = {top: 0, bottom: 0};
  public showFaders = false;

  constructor() {
  }


  ngAfterContentInit() {

    /**
     * Add handler for panel collapsing/expanding
     */
    if (this.panels.length) {
      this.panels.forEach(p => {

        const f = p.drawer.hideContent;
        p.drawer.hideContent = () => {
          this.drawerHeight = {top: 0, bottom: 0};
          f();
          this.showFaders = !p.collapsed;
        };

        const f2 = p.ngAfterViewChecked;
        p.ngAfterViewChecked = () => {
          if (!p.collapsed) {
            this.drawerHeight.top = p.self.nativeElement.offsetTop - 5;
            this.drawerHeight.bottom = p.self.nativeElement.offsetHeight + p.self.nativeElement.offsetTop;
            window.scroll({left: 0, top: this.drawerHeight.top - 50, behavior: 'smooth'});
            f2();
          }
        };
      });
    }
  }

  onPanelClosed() {
    this.panels.forEach(p => {
      if (!p.collapsed) {
        p.drawer.hideContent();
      }
    });
  }
}
