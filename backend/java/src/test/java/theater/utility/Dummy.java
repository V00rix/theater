package theater.utility;

import javafx.util.Pair;
import theater.domain.entities.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Helper class for dummy entities
 */
public abstract class Dummy {

    //region Static fields
    private static final Client[] clients = new Client[] {
            new Client("123123123", "first"),
            new Client("777777777", "second"),
            new Client("my@mail.com", "third"),
            new Client("mail@example.com", "fourth")
    };

    private static final Hall[] halls = new Hall[] {
            new Hall("Dummy_Hall_1", 3, 4),
            new Hall("Dummy_Hall_2", 3, 6),
            new Hall("Dummy_Hall_3", 6, 4)
    };

    private static final String[] theaterAddresses = new String[] {
            "Dummy_Address_1",
            "Dummy_Address_2"
    };

    private static final String[] theaterNames = new String[] {
            "Dummy_Theater_1",
            "Dummy_Theater_2"
    };

    private static final String[] performanceAuthors = new String[] {
            "Dummy_Author_1", "Dummy_Author_2"
    };

    private static final String[] performanceTitles = new String[] {
            "Dummy_Title_1", "Dummy_Title_2"
    };

    private static final BigDecimal[] theaterPrices = new BigDecimal[] {
            new BigDecimal(100)
    };

    private static final String[] theaterOpen = new String[] {
            "Dummy_Open_Time_1"
    };

    private static final Integer[] theaterSeats = new Integer[] {
            4, 5
    };

    private static final Calendar calendar = Calendar.getInstance();

    private static final Timestamp[] sessionDates = new Timestamp[] {
            newDate(2010, 10, 3, 16, 0, 0),
            newDate(2016, 9, 3, 16, 0, 0),
            newDate(2016, 9, 3, 19, 30, 0)
    };
    //endregion

    //region Helpers
    public static Timestamp newDate(int y, int m, int d, int h, int mm, int ss) {
        calendar.set(y, m, d, h, mm, ss);
        return new Timestamp(calendar.getTime().getTime());
    }

    //region Single
    public static Theater theater() {
        return new Theater("Address", "Theater Name", new BigDecimal(100), "Every Tuesday, Wednesday and Friday from 10:00 to 16:00", 5);
    }

    public static Hall hall() {
        return new Hall("Hall Name", 10, 10);
    }

    public static Performance performance() {
        return new Performance("Author", "Title");
    }

    public static Session session(Hall hall, Performance performance) {
        return new Session(hall, performance, new Timestamp(System.currentTimeMillis()));
    }
    //endregion

    public static Order order(Session session, Client client) {
        return new Order(session, client, Order.Checkout.PAY_BEFORE);
    }

    public static Client client() {
        return new Client("emxail@example.com", "Client Dummy Name");
    }

    //region Multiple
    public static List<Client> clients() {
        return from(clients);
    }

    public static List<Hall> halls() {
        return from(halls);
    }

    public static List<Theater> theaters() {
        var addresses = from(theaterAddresses);
        var names = from(theaterNames);
        var prices = from(theaterPrices);
        var open = from(theaterOpen);
        var seats = from(theaterSeats);

        var combinations = line(cartesian(addresses, names), line(prices, line(open, seats)), LineMode.FIRST);

        var theaters = new ArrayList<Theater>();
        combinations.forEach(x -> {
            var x1 = x.getKey();
            var address = x1.getKey();
            var name = x1.getValue();
            var x2 = x.getValue();
            var price = x2.getKey();
            var x3 = x2.getValue();
            var o = x3.getKey();
            var seat = x3.getValue();
            theaters.add(new Theater(address, name, price, o, seat));
        });
        return theaters;
    }

    public static List<Performance> performances() {
        var authors = from(performanceAuthors);
        var titles = from(performanceTitles);
        var combinations = cartesian(authors, titles);
        var performances = new ArrayList<Performance>();
        combinations.forEach(x -> performances.add(new Performance(x.getKey(), x.getValue())));
        return performances;
    }
    //endregion

    public static List<Session> sessions(List<Hall> halls, List<Performance> performances) {
        var dates = from(sessionDates);
        var combinations = cartesian(halls, cartesian(performances, dates));
        var sessions = new ArrayList<Session>();
        combinations.forEach(x -> {
            var x1 = x.getValue();
            sessions.add(new Session(x.getKey(), x1.getKey(), x1.getValue()));
        });
        return sessions;
    }

    public static List<Order> orders(List<Session> sessions, List<Client> clients) {
        var combinations = cartesian(sessions, clients);
        var orders = new ArrayList<Order>();
        combinations.forEach(x -> orders.add(new Order(x.getKey(), x.getValue(), Order.Checkout.PAY_BEFORE)));
        return orders;
    }

    private static <E, R> List<Pair<E, R>> cartesian(List<E> first, List<R> second) {
        var res = new ArrayList<Pair<E, R>>();
        first.forEach(f -> second.forEach(s -> res.add(new Pair<>(f, s))));
        return res;
    }

    private static <E, R> List<Pair<E, R>> line(List<E> first, List<R> second) {
        return line(first, second, LineMode.MAX);
    }

    private static <E, R> List<Pair<E, R>> line(List<E> first, List<R> second, LineMode lineMode) {
        var s1 = first.size();
        var s2 = second.size();
        var size = 0;
        switch (lineMode) {
            case MAX:
                size = Integer.max(s1, s2);
                break;
            case MIN:
                size = Integer.min(s1, s2);
                break;
            case FIRST:
                size = s1;
                break;
            case SECOND:
                size = s2;
                break;
        }

        var res = new ArrayList<Pair<E, R>>();
        for (int i = 0; i < size; i++) {
            res.add(new Pair<>(first.get(i % s1), second.get(i % s2)));
        }

        return res;
    }

    @SafeVarargs
    private static <E> ArrayList<E> from(E... items) {
        return new ArrayList<>(Arrays.asList(items));
    }
    //endregion

    private enum LineMode {
        FIRST,
        SECOND,
        MAX,
        MIN
    }
}
