package algo;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);

        System.out.println(a.subList(0,2));
    }
}
