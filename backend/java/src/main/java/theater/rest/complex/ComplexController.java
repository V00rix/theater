package theater.rest.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Order;
import theater.domain.enumeration.Availability;
import theater.domain.http.OrderResponse;
import theater.domain.http.PerformanceResponse;
import theater.domain.http.SessionResponse;
import theater.domain.http.Status;
import theater.repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complex")
public class ComplexController {

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

    @RequestMapping(value = "/performances", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    PerformanceResponse getBasicData() {
        var maximumSeats = theaterRepository.findAll().get(0).maximumSeats;
        var response = new PerformanceResponse(new HashMap<>(), maximumSeats);

        var performances = performanceRepository.findAll();
        performances.forEach(p -> response.performances.put(p.id.intValue(), p));

        return response;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<OrderResponse> getOrders() {
        var res = new ArrayList<OrderResponse>();

        var orders = orderRepository.findAll().stream().filter(x -> !x.confirmed).collect(Collectors.toList());

        for (var order : orders) {
            var session = order.session;
            var performance = session.performance.stringValue();

            res.add(new OrderResponse(order.getId(), Long.toHexString(order.getId()),
                    order.checkout, order.client, performance, session.date, order.getSeats()));
        }

        return res;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public @ResponseBody
    void resolveOrders(@RequestBody Map<Long, Boolean> orders) {
        orders.forEach((id, confirmed) -> {
            var actual = orderRepository.findById(id);
            if (actual.isPresent()) {
                var order = actual.get();
                if (confirmed) {
                    order.confirmed = true;
                    orderRepository.save(order);
                } else {
                    orderRepository.delete(order);
                }
            }
        });
    }

    @RequestMapping(value = "/session/{sessionId}", method = RequestMethod.GET, produces = "application/json")
    @Transactional
    public @ResponseBody
    SessionResponse getSession(@PathVariable Long sessionId) {
        var response = new SessionResponse(sessionRepository.findById(sessionId).orElseThrow());
        var seats = response.session.hall.getSeats();

        var maxX = 0;
        var maxY = 0;

        for (var seat : seats) {
            if (seat.seat > maxX) {
                maxX = seat.seat;
            }
            if (seat.row > maxY) {
                maxY = seat.row;
            }
        }

        response.seats = new ArrayList<>();
        for (int i = 0; i <= maxY; i++) {
            var row = new ArrayList<Availability>();
            for (int j = 0; j <= maxX; j++) {
                row.add(Availability.HIDDEN);
            }
            response.seats.add(row);
        }

        seats.forEach(s1 -> response.seats.get(s1.row).set(s1.seat, Availability.FREE));

        var orders = orderRepository.findAll().stream().filter(order -> order.session.getId().equals(sessionId));
        orders.forEach(o -> o.getSeats().forEach(s1 -> response.seats.get(s1.row).set(s1.seat, Availability.BOOKED)));

        Collections.reverse(response.seats);
        var status = Status.status;
        status.selectedSeats.forEach(seat -> response.seats.get(seat.row - 1).set(seat.seat - 1, Availability.SELECTED));

        return response;
    }

    @RequestMapping(value = "/order/new", method = RequestMethod.POST, produces = "application/json")
    @Transactional
    public @ResponseBody
    String createOrder() {
        var status = Status.status;

        status.client.setId(null);

        var client = clientRepository.findOptionalByContact(status.client.contact);
        if (!client.isPresent()) {
            clientRepository.save(status.client);
        } else {
            var name = status.client.name;
            status.client.update(client.get());
            status.client.name = name;
            clientRepository.save(status.client);
        }

        status.selectedSeats.forEach(s -> {
            s.row -= 1;
            s.seat -= 1;
        });

        var o = new Order(status.checkout,
                sessionRepository.findById(status.selectedSession).orElseThrow(),
                status.client,
                status.selectedSeats);
        orderRepository.saveAndFlush(o);

        status.selectedSeats = new ArrayList<>();

        return Long.toHexString(o.getId());
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Status getStatus() {
        var status = Status.status;
        if (status.selectedPerformance == null) {
            status.selectedPerformance = this.performanceRepository.findAll().get(0).id;
        }
        return status;
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void setStatus(@RequestBody Status status) {
        status.selectedSeats = status.selectedSeats == null ? new ArrayList<>() : status.selectedSeats;
        Status.status = status;
    }

    @Autowired
    public ComplexController(TheaterRepository theaterRepository, SessionRepository sessionRepository, PerformanceRepository performanceRepository, ClientRepository clientRepository, OrderRepository orderRepository) {
        this.theaterRepository = theaterRepository;
        this.sessionRepository = sessionRepository;
        this.performanceRepository = performanceRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }
}
