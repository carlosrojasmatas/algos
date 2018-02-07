package algo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StopWatch;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    @Test
    @DisplayName("Test linear search")
    void linearSearch() {
        StopWatch watch  = new StopWatch();
        Searcher<Integer> searcher = new Searcher<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(11);
        list.add(29);
        watch.start();
        int pos = searcher.linearSearch(list,22);
        long time = watch.stop();
        System.out.println(String.format("Linear search processed in %d millis",time));

        assertEquals(-1,pos);
        watch.start();
        pos = searcher.linearSearch(list,11);
        time = watch.stop();
        System.out.println(String.format("Linear search processed in %d millis",time));

        assertEquals(3,pos);
    }

    @Test
    @DisplayName("Test rec linear search")
    void recLinearSearch() {
        StopWatch watch  = new StopWatch();
        Searcher<Integer> searcher = new Searcher<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(11);
        list.add(29);
        list.add(33);
        list.add(40);
        list.add(44);
        list.add(45);
        list.add(100);

        for(int e : list){
            watch.start();
            int pos = searcher.recLinearSearch(list,0, e);
            long time = watch.stop();
            System.out.println(String.format("Linear search processed in %d millis",time));
            assertEquals(list.indexOf(e),pos);
        }
        watch.start();
        int pos = searcher.recLinearSearch(list,0,55);
        long time = watch.stop();
        System.out.println(String.format("Linear search processed in %d millis",time));
        assertEquals(-1,pos);
    }

    @Test
    @DisplayName("Test binarySearch search")
    void binarySearch() {
        StopWatch watch  = new StopWatch();
        Searcher<Integer> searcher = new Searcher<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(11);
        list.add(29);
        list.add(33);
        list.add(40);
        list.add(44);
        list.add(45);
        list.add(100);

        for(int e : list){
            watch.start();
            int pos = searcher.binarySearch(list,e);
            long time = watch.stop();
            System.out.println(String.format("Linear search processed in %d millis",time));
            assertEquals(list.indexOf(e),pos);
        }

        watch.start();
        int pos = searcher.binarySearch(list,55);
        long time = watch.stop();
        System.out.println(String.format("Linear search processed in %d millis",time));
        assertEquals(-1,pos);

        list.clear();
        list.add(1);

        watch.start();
        pos = searcher.binarySearch(list,1);
        time = watch.stop();
        System.out.println(String.format("Linear search processed in %d millis",time));
        assertEquals(0,pos);



    }
}
