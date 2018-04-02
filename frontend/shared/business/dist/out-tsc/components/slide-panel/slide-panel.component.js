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
var navigation_component_1 = require("../navigation/navigation.component");
var SlidePanelComponent = /** @class */ (function () {
    function SlidePanelComponent() {
    }
    SlidePanelComponent.prototype.ngOnInit = function () {
        this.showPanel = false;
    };
    SlidePanelComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        this.navigationComponent.close.subscribe(function (event) {
            _this.showPanel = false;
        });
    };
    SlidePanelComponent.prototype.onShowPanel = function () {
        this.showPanel = true;
    };
    SlidePanelComponent.prototype.onHidePanel = function (clicked) {
        if (clicked && this.showOverlay !== false || !clicked && this.showOverlay === false) {
            this.showPanel = false;
        }
    };
    __decorate([
        core_1.Input(),
        __metadata("design:type", Boolean)
    ], SlidePanelComponent.prototype, "showOverlay", void 0);
    __decorate([
        core_1.ContentChild(navigation_component_1.NavigationComponent),
        __metadata("design:type", navigation_component_1.NavigationComponent)
    ], SlidePanelComponent.prototype, "navigationComponent", void 0);
    SlidePanelComponent = __decorate([
        core_1.Component({
            selector: 'app-slide-panel',
            templateUrl: './slide-panel.component.html',
            styleUrls: ['./slide-panel.component.scss']
        }),
        __metadata("design:paramtypes", [])
    ], SlidePanelComponent);
    return SlidePanelComponent;
}());
exports.SlidePanelComponent = SlidePanelComponent;
//# sourceMappingURL=slide-panel.component.js.map