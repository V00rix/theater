import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'app-bottom-panel',
    templateUrl: './bottom-panel.component.html',
    styleUrls: ['./bottom-panel.component.scss']
})
export class BottomPanelComponent implements OnInit {
    @Input() buttons: { type: string, text: string, callback: (...args: any[]) => any }[];
    @Input() panelPosition = 'fixed';
    constructor() {
    }

    ngOnInit() {
    }

}
