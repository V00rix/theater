"use strict";
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
var SlidePanelContentComponent = /** @class */ (function () {
    function SlidePanelContentComponent() {
        this.close = new core_1.EventEmitter();
    }
    SlidePanelContentComponent.prototype.ngOnInit = function () {
    };
    SlidePanelContentComponent.prototype.onClose = function () {
        this.close.emit();
    };
    __decorate([
        core_1.Output(),
        __metadata("design:type", Object)
    ], SlidePanelContentComponent.prototype, "close", void 0);
    SlidePanelContentComponent = __decorate([
        core_1.Component({
            selector: 'app-slide-panel-content',
            templateUrl: './slide-panel-content.component.html',
            styleUrls: ['./slide-panel-content.component.scss']
        }),
        __metadata("design:paramtypes", [])
    ], SlidePanelContentComponent);
    return SlidePanelContentComponent;
}());
exports.SlidePanelContentComponent = SlidePanelContentComponent;
//# sourceMappingURL=slide-panel-content.component.js.map