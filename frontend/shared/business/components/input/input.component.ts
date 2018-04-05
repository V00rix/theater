import {Component, forwardRef, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {AbstractValueAccessor} from '../abstract-value-accessor';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {Animations} from '../../../animations/animations';

@Component({
    selector: 'app-input',
    templateUrl: './input.component.html',
    styleUrls: ['./input.component.scss'],
    providers: [{
        provide: NG_VALUE_ACCESSOR,
        multi: true,
        useExisting: forwardRef(() => InputComponent),
    }],
    animations: Animations.animations
})
export class InputComponent extends AbstractValueAccessor implements OnInit {
    @Input() public name: string;
    @Input() public class: string;
    @Input() public style: string;
    @Input() public pattern: string;
    @Output() public validityChanged = new EventEmitter<boolean>();
    public isSelected: boolean;

    constructor() {
        super();
    }

    ngOnInit() {
        if (this.name == null) {
            throw new Error('Attribute \'name\' is missing on InputComponent');
        }
        this.isSelected = false;
    }

    public selected(value: boolean) {
        this.isSelected = value;
    }

    public getSelection() {
        return this.isSelected || this.value;
    }

    public checkValidity()  {
        if (this.value) {
            if (this.value.match(this.pattern)) {
                this.validityChanged.emit(true);
                return 'valid';
            }
            this.validityChanged.emit(false);
            return 'invalid';
        }
    }
}
