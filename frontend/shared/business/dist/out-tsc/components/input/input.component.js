"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var abstract_value_accessor_1 = require("../abstract-value-accessor");
var forms_1 = require("@angular/forms");
var InputComponent = /** @class */ (function (_super) {
    __extends(InputComponent, _super);
    function InputComponent() {
        return _super.call(this) || this;
    }
    InputComponent_1 = InputComponent;
    InputComponent.prototype.ngOnInit = function () {
        if (this.name == null) {
            throw new Error('Attribute \'name\' is missing on InputComponent');
        }
        this.isSelected = false;
    };
    InputComponent.prototype.selected = function (value) {
        this.isSelected = value;
    };
    __decorate([
        core_1.Input(),
        __metadata("design:type", String)
    ], InputComponent.prototype, "name", void 0);
    InputComponent = InputComponent_1 = __decorate([
        core_1.Component({
            selector: 'app-input',
            templateUrl: './input.component.html',
            styleUrls: ['./input.component.scss'],
            providers: [{
                    provide: forms_1.NG_VALUE_ACCESSOR,
                    multi: true,
                    useExisting: core_1.forwardRef(function () { return InputComponent_1; }),
                }]
        }),
        __metadata("design:paramtypes", [])
    ], InputComponent);
    return InputComponent;
    var InputComponent_1;
}(abstract_value_accessor_1.AbstractValueAccessor));
exports.InputComponent = InputComponent;
//# sourceMappingURL=input.component.js.map