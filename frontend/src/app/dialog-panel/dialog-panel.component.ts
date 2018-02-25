import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'app-dialog-panel',
    templateUrl: './dialog-panel.component.html',
    styleUrls: ['./dialog-panel.component.scss']
})
export class DialogPanelComponent implements OnInit {
    @Input() buttons: { type: string, text: string, callback: (...args: any[]) => any }[];
    @Input('position') position = 'fixed';
    @Input('align') align = 'bottom';
    public displayClass = '';

    constructor() {
    }

    ngOnInit() {
        this.displayClass = 'dialog-panel-' + this.position + ' dialog-panel-' + this.align;
    }
}
