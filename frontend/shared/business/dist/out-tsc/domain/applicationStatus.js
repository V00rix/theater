"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ApplicationStatus = /** @class */ (function () {
    function ApplicationStatus(selectedPerformance, selectedSession, selectedSeats, checkout, user) {
        this.selectedPerformance = selectedPerformance;
        this.selectedSession = selectedSession;
        this.selectedSeats = selectedSeats;
        this.checkout = checkout;
        this.user = user;
    }
    ApplicationStatus.prototype.transform = function (performances) {
        return {
            selected_performance: performances.indexOf(this.selectedPerformance),
            selected_session: this.selectedPerformance.sessions.indexOf(this.selectedSession),
            selected_seats: this.selectedSeats,
            selected_checkout: this.checkout,
            user: this.user
        };
    };
    return ApplicationStatus;
}());
exports.ApplicationStatus = ApplicationStatus;
//# sourceMappingURL=applicationStatus.js.map