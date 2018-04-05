import {Component, OnInit, Input} from '@angular/core';
import {Location} from '@angular/common';

@Component({
    selector: 'app-button-back',
    templateUrl: './button-back.component.html',
    styleUrls: ['./button-back.component.scss']
})
export class ButtonBackComponent implements OnInit {
    @Input() style;
    @Input() class;

    constructor(public location: Location) {
    }

    ngOnInit() {
    }

}
