package theater.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Seat implements Serializable {
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

    public int x;

    public int y;

    @Override
    public Seat clone() throws CloneNotSupportedException {
        super.clone();
        var s = new Seat();
        s.x = x;
        s.y = y;
        return s;
    }

    @Override
    public String toString() {
        return "Row " + this.y + ". Seat " + this.x;
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

    public Seat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Seat() {

    }
}
