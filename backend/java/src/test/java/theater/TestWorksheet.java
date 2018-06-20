package theater;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestWorksheet {

    @Test
    public void dummy() {
        //        Dummy.theters().forEach(seat -> seat.print());
        var f = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        var f2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

//        Dummy.line(f, f2).forEach(p -> System.out.println(p.getKey() + " " + p.getValue()));
    }

    private static void myMethod(int... items) {
        var length = items.length;
        if (length > 0) {
            for (int item : items) {
                System.out.print(item + " ");
            }
            var subset = Arrays.copyOfRange(items, 1, length);
            myMethod(subset);
        }
    }
}
