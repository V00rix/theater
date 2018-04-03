import {Component, forwardRef, Input, OnInit} from '@angular/core';
import {AbstractValueAccessor} from '../abstract-value-accessor';
import {NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  providers:   [{
    provide: NG_VALUE_ACCESSOR,
    multi: true,
    useExisting: forwardRef(() => InputComponent),
  }]
})
export class InputComponent extends AbstractValueAccessor implements OnInit{
  @Input() public name: String;
  @Input() public class: String;
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

}
