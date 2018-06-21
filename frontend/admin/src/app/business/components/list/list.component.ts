import {AfterContentInit, Component, ContentChildren, OnDestroy, QueryList} from '@angular/core';
import {PanelCollapsibleComponent} from '../panel-collapsible/panel-collapsible.component';
import {Animations} from '../../../../../../shared/animations/animations';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
  animations: Animations.animations
})
export class ListComponent implements AfterContentInit, OnDestroy {
  @ContentChildren(PanelCollapsibleComponent) public panels: QueryList<PanelCollapsibleComponent>;
  public drawerHeight: { top: number, bottom: number } = {top: 0, bottom: 0};
  public showFaders = false;

  private scrollingFinished = false;
  private subscriptionLoaded;
  private subscriptionUpdate;

  constructor(private data: DataService) {
  }

  ngAfterContentInit() {
    if (this.data.dataLoaded) {
      this.initHandler();
    }
    this.subscriptionLoaded = this.data.loadingFinished.subscribe(() => {
      this.initHandler();
    });
    this.subscriptionUpdate = this.data.dataUpdated.subscribe(() => {
      this.initHandler();
    });
  }

  ngOnDestroy() {
    this.subscriptionLoaded.unsubscribe();
    this.subscriptionUpdate.unsubscribe();
  }

  /**
   * Add handler for panel collapsing/expanding
   */
  public initHandler() {

    console.log(this.panels.length);

    if (this.panels.length) {
      this.panels.forEach(p => {

        const f = p.drawer.hideContent;
        p.drawer.hideContent = async () => {
          // await p.waitFor();
          console.log("hide ciontent");
          this.drawerHeight = {top: 0, bottom: 0};
          f();
          // this.drawerHeight.top = p.self.nativeElement.offsetTop + 40;
          // this.drawerHeight.bottom = p.self.nativeElement.offsetHeight + p.self.nativeElement.offsetTop;
          // this.showFaders = !p.collapsed;
          console.log(this.showFaders);
        };
        //
        // // const f2 = p.ngAfterViewChecked;
        // p.ngAfterViewChecked = async () => {
        //   console.log("after view chacheekc");
        // //   // await p.waitFor();
        // //   if (!p.collapsed) {
        // //     // if (!this.scrollingFinished) {
        //     this.drawerHeight.top = p.self.nativeElement.offsetTop + 40;
        //     this.drawerHeight.bottom = p.self.nativeElement.offsetHeight + p.self.nativeElement.offsetTop;
        // //     // setTimeout(() => {
        // //     window.scroll({left: 0, top: this.drawerHeight.top - 50});
        // //     // this.scrollingFinished = true;
        // //     // }, 300);
        // //     // } else {
        // //     //   this.scrollingFinished = false;
        // //     // }
        // //     f2();
        // //   }
        // };
      });
    }
  }

  onPanelClosed() {
    this.panels.forEach(p => {
      if (!p.collapsed) {
        p.drawer.hideContent();
        p.drawer.collapsed.emit();
      }
    });
  }
}

