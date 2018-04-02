"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var seatStatus_enum_1 = require("../enumeration/seatStatus.enum");
var applicationStatus_1 = require("../applicationStatus");
var checkout_1 = require("../enumeration/checkout");
var StatusResponse = /** @class */ (function () {
    function StatusResponse() {
    }
    StatusResponse.map = function (statusResponse, performances) {
        var selPerformance;
        var selSession;
        if (performances && performances.length) {
            selPerformance = performances[statusResponse.selected_performance] || performances[0];
            selSession = selPerformance.sessions[statusResponse.selected_session] || selPerformance.sessions[0];
            if (statusResponse.selected_seats && statusResponse.selected_seats.length) {
                for (var _i = 0, _a = statusResponse.selected_seats; _i < _a.length; _i++) {
                    var seat = _a[_i];
                    selSession.seats[selSession.seats.length - seat.row][seat.seat - 1].status = seatStatus_enum_1.SeatStatus.SELECTED;
                    selSession.seats[selSession.seats.length - seat.row][seat.seat - 1].status = seatStatus_enum_1.SeatStatus.SELECTED;
                }
            }
        }
        return new applicationStatus_1.ApplicationStatus(selPerformance, selSession, statusResponse.selected_seats || [], statusResponse.selected_checkout || checkout_1.Checkout.PAY_BEFORE, statusResponse.user || { name: null, contact: null });
    };
    return StatusResponse;
}());
exports.StatusResponse = StatusResponse;
//# sourceMappingURL=statusResponse.js.map