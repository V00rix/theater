"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var session_1 = require("../session");
var performance_1 = require("../performance");
var seatStatus_enum_1 = require("../enumeration/seatStatus.enum");
var PerformancesResponse = /** @class */ (function () {
    function PerformancesResponse() {
    }
    PerformancesResponse.map = function (performancesResponse) {
        return performancesResponse.performances.map(function (p) {
            return new performance_1.Performance(p.id, p.title, p.image_url, p.description, p.sessions.map(function (s) {
                return new session_1.Session(s.id, s.date, s.seats.map(function (r) {
                    return r.map(function (seat) {
                        return {
                            status: seat === 'FREE' ? seatStatus_enum_1.SeatStatus.FREE :
                                seat === 'HIDDEN' ? seatStatus_enum_1.SeatStatus.HIDDEN : seatStatus_enum_1.SeatStatus.BOOKED
                        };
                    });
                }).reverse());
            }));
        });
    };
    return PerformancesResponse;
}());
exports.PerformancesResponse = PerformancesResponse;
//# sourceMappingURL=performancesResponse.js.map