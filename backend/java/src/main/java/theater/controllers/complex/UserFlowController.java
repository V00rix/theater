package theater.controllers.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Order;
import theater.domain.http.PerformanceResponse;
import theater.domain.http.SessionResponse;
import theater.domain.http.Status;
import theater.repositories.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/api/complex")
public class UserFlowController {

    private final
    TheaterRepository theaterRepository;

    private final
    SessionRepository sessionRepository;

    private final
    PerformanceRepository performanceRepository;

    private final
    ClientRepository clientRepository;

    private final
    OrderRepository orderRepository;

    @Autowired
    public UserFlowController(TheaterRepository theaterRepository, SessionRepository sessionRepository, PerformanceRepository performanceRepository, ClientRepository clientRepository, OrderRepository orderRepository) {
        this.theaterRepository = theaterRepository;
        this.sessionRepository = sessionRepository;
        this.performanceRepository = performanceRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/performances", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    PerformanceResponse getBasicData() {
        var maximumSeats = theaterRepository.findAll().get(0).maximumSeats;
        var response = new PerformanceResponse(new HashMap<>(), maximumSeats);

        var performances = performanceRepository.findAll();
        performances.forEach(p -> response.performances.put(p.id.intValue(), p));

        return response;
    }

    // TODO: 17-Jun-18  
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    void getOrders() {
        //        var maximumSeats = theaterRepository.findAll().get(0).maximumSeats;
        //        var response = new PerformanceResponse(new HashMap<>(), maximumSeats);
        //
        //        var performances = performanceRepository.findAll();
        //        performances.forEach(p -> response.performances.put(p.id.intValue(), p));
        //
        //        return response;
    }

    @RequestMapping(value = "/session/{sessionId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    SessionResponse getSession(@PathVariable Integer sessionId) {
        // Int to Long.. hmm...............
        var response = new SessionResponse(sessionRepository.findById(sessionId.longValue()).orElseThrow());
        var seats = response.session.hall.getSeats();

        var maxX = 0;
        var maxY = 0;

        for (var seat : seats) {
            if (seat.x > maxX) {
                maxX = seat.x;
            }
            if (seat.y > maxY) {
                maxY = seat.y;
            }
        }

        response.seats = new ArrayList<>();
        for (int i = 0; i <= maxY; i++) {
            var row = new ArrayList<Order.Seat.Availability>();
            for (int j = 0; j <= maxX; j++) {
                row.add(Order.Seat.Availability.HIDDEN);
            }
            response.seats.add(row);
        }

        //        response.seats = new ArrayList<>(Collections.nCopies(maxY + 1, new ArrayList<>(Collections.nCopies(maxX + 1, Order.Seat.Availability.HIDDEN))));
        seats.forEach(s1 -> response.seats.get(s1.y).set(s1.x, Order.Seat.Availability.FREE));

        var orders = orderRepository.findAll().stream().filter(order -> order.session.getId().intValue() == sessionId);
        orders.forEach(o -> o.seats.forEach(s1 -> response.seats.get(s1.row).set(s1.seat, Order.Seat.Availability.BOOKED)));

        Collections.reverse(response.seats);
        //        var status = Status.status;
        //        status.selectedSeats.forEach(seat -> response.seats.get(seat.row).set(seat.seat, Order.Seat.Availability.SELECTED));

        return response;
    }

    @RequestMapping(value = "/order/new", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String createOrder() {
        var status = Status.status;

        var client = clientRepository.findOptionalByContact(status.client.contact);
        if (!client.isPresent()) {
            clientRepository.save(status.client);
        } else {
            status.client = client.get();
        }

        status.selectedSeats.forEach(s -> {
            s.row -= 1;
            s.seat -= 1;
        });

        var o = new Order(status.checkout,
                sessionRepository.findById(status.selectedSession.longValue()).orElseThrow(),
                status.client,
                status.selectedSeats);

        orderRepository.save(o);

        status.selectedSeats = new ArrayList<>();

        return Long.toHexString(o.getId());
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Status getStatus() {
        var status = Status.status;
        if (status.selectedPerformance == null) {
            status.selectedPerformance = this.performanceRepository.findAll().get(0).id.intValue();
        }
        return status;
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void setStatus(@RequestBody Status status) {
        status.selectedSeats = status.selectedSeats == null ? new ArrayList<>() : status.selectedSeats;
        Status.status = status;
    }
}
