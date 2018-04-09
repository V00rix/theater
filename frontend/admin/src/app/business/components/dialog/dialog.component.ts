import {Component, OnInit, Output, Input, EventEmitter, Optional} from '@angular/core';
import {PanelCollapsibleComponent} from '../panel-collapsible/panel-collapsible.component';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {
  @Output() public reject = new EventEmitter<void>();
  @Output() public resolve = new EventEmitter<void>();
  @Input() public class: string;

  constructor(@Optional() private parent: PanelCollapsibleComponent) {
    this.parent = parent;
  }

  ngOnInit() {
  }

  onResolve(state: boolean) {
    if (state) {
      this.resolve.emit();
    } else {
      this.reject.emit();
    }
    if (this.parent) {
      this.parent.drawer.hideContent();
    }
  }
}
