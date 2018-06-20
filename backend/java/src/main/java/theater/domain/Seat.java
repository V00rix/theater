package theater.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Seat implements Serializable, Cloneable {
    private static ByteArrayOutputStream bos;
    private static ObjectOutputStream oos;

    static {
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int seat;

    public int row;

    @Override
    public Seat clone() throws CloneNotSupportedException {
        super.clone();
        var s = new Seat();
        s.seat = seat;
        s.row = row;
        return s;
    }

    @Override
    public String toString() {
        return "Row " + this.row + ". Seat " + this.seat;
    }

    public void print() {
        System.out.println(toString());
    }

    public static List<Seat> fromBytes(byte[] seatsBytes) {
        var seats = new ArrayList<Seat>();
        try {
            var bis = new ByteArrayInputStream(seatsBytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            seats = (ArrayList<Seat>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seats;
    }

    public static byte[] toBytes(List<Seat> seats) {
        byte[] seatsBytes;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(seats);
            oos.flush();
            seatsBytes = bos.toByteArray();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return seatsBytes;
    }

    public Seat(int seat, int row) {
        this.seat = seat;
        this.row = row;
    }

    public Seat() {

    }
}
