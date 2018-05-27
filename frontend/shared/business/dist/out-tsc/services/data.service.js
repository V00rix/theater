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
var http_1 = require("@angular/common/http");
var performancesResponse_1 = require("../domain/responces/performancesResponse");
var statusResponse_1 = require("../domain/responces/statusResponse");
require("rxjs/add/operator/map");
var DataService = /** @class */ (function () {
    // todo: implement in my next life
    // public loggedIn: boolean;
    /**
     * Constructor
     * @param {HttpClient} http
     */
    function DataService(http) {
        this.http = http;
        // todo: move to correct location
        this.pages = ['home', 'confirmation', 'contacts', 'performance',
            'performances', 'session', 'sessions', 'success', 'checkout', 'viewer'];
        this.baseUrl = 'http://localhost';
        this.dataLoaded = false;
        this.getPerformanceData();
    }
    DataService.prototype.getPerformanceData = function () {
        var _this = this;
        this.http.get(this.baseUrl + "/backend/php/requests/performances.php").subscribe(function (response) {
            _this.performances = performancesResponse_1.PerformancesResponse.map(response);
            _this.maximumSeats = response.maximum_seats;
            _this.getStatus().subscribe(function () {
                _this.dataLoaded = true;
            });
        });
    };
    /**
     * Post application status
     */
    DataService.prototype.postStatus = function () {
        this.http.post(this.baseUrl + "/backend/php/requests/status.php", this.applicationStatus.transform(this.performances), { withCredentials: true }).subscribe();
    };
    /**
     * Get application status
     * @returns {Observable<void>}
     */
    DataService.prototype.getStatus = function () {
        var _this = this;
        return this.http.get(this.baseUrl + "/backend/php/requests/status.php", { withCredentials: true }).map(function (response) {
            _this.applicationStatus = statusResponse_1.StatusResponse.map(response, _this.performances);
        });
    };
    /**
     * Update application status
     * @param param Update object
     */
    DataService.prototype.updateSelection = function (param) {
        this.applicationStatus.selectedPerformance = param.performance || this.applicationStatus.selectedPerformance;
        this.applicationStatus.selectedSession = param.session || this.applicationStatus.selectedSession;
        this.applicationStatus.selectedSeats = param.seats === null || param.seats === undefined ?
            this.applicationStatus.selectedSeats : param.seats;
        this.applicationStatus.user = param.user || this.applicationStatus.user;
        this.applicationStatus.checkout = param.checkout || this.applicationStatus.checkout;
        this.postStatus();
    };
    /**
     * Post booking request
     */
    DataService.prototype.postBooking = function () {
        var _this = this;
        this.http.post(this.baseUrl + "/backend/php/requests/reservation.php", null, { withCredentials: true }).subscribe(function (response) {
            _this.bookingCode = response;
            return _this.getPerformanceData();
        });
    };
    DataService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.HttpClient])
    ], DataService);
    return DataService;
}());
exports.DataService = DataService;
//# sourceMappingURL=entities.service.js.map