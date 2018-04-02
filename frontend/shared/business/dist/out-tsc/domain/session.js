"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Session = /** @class */ (function () {
    function Session(id, date, seats) {
        this.id = id;
        this.date = date;
        this.seats = seats;
    }
    Session.prototype.forSeats = function (f) {
        for (var r = 0; r < this.seats.length; r++) {
            for (var s = 0; s < this.seats[r].length; s++) {
                f(this.seats[r][s], r, s);
            }
        }
    };
    return Session;
}());
exports.Session = Session;
//# sourceMappingURL=session.js.map