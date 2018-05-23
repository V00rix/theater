import {Component, OnInit, Output, Input, EventEmitter, Optional} from '@angular/core';
import {PanelCollapsibleComponent} from '../panel-collapsible/panel-collapsible.component';
import {OverlayComponent} from '../overlay/overlay.component';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {
  @Output() public reject = new EventEmitter<void>();
  @Output() public resolve = new EventEmitter<void>();
  @Input() public class: string;

  constructor(@Optional() private panel: PanelCollapsibleComponent, @Optional() private overlay: OverlayComponent) {
    this.panel = panel;
  }

  ngOnInit() {
  }

  onResolve(state: boolean) {
    if (state) {
      this.resolve.emit();
    } else {
      this.reject.emit();
    }
    if (this.panel) {
      this.panel.drawer.hideContent();
    }
    if (this.overlay) {
      if (state) {
        this.overlay.close.emit();
      } else {
        this.overlay.reject.emit();
      }
    }
  }
}
