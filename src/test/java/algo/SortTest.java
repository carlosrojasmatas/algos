package algo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    @Test
    @DisplayName("Sort list with selection sorter")
    void testSelectionSorter(){
        Sorter<Integer> sorter = new Sorter<>();

        List<Integer> list  = new ArrayList<>();
        list.add(20);
        list.add(2);
        list.add(1);
        list.add(11);
        list.add(22);
        list.add(3);
        list.add(8);

        sorter.selectionSorter(list);

        assertTrue(list.get(0)== 1);
        assertTrue(list.get(1)== 2);
        assertTrue(list.get(2)== 3);
        assertTrue(list.get(3)== 8);
        assertTrue(list.get(4)== 11);
        assertTrue(list.get(5)== 20);
        assertTrue(list.get(6)== 22);

    }

    @Test
    @DisplayName("Sort list with insertion sorter")
    void testInsertionSort(){
        Sorter<Integer> sorter = new Sorter<>();

        List<Integer> list  = new ArrayList<>();
        list.add(20);
        list.add(2);
        list.add(1);
        list.add(11);
        list.add(22);
        list.add(3);
        list.add(8);

        sorter.insertionSort(list);

        assertTrue(list.get(0)== 1);
        assertTrue(list.get(1)== 2);
        assertTrue(list.get(2)== 3);
        assertTrue(list.get(3)== 8);
        assertTrue(list.get(4)== 11);
        assertTrue(list.get(5)== 20);
        assertTrue(list.get(6)== 22);

    }

    @Test
    @DisplayName("Sort list with merge sorter")
    void testMergeSort(){
        Sorter<Integer> sorter = new Sorter<>();

        List<Integer> list  = new ArrayList<>();
        list.add(20);
        list.add(2);
        list.add(1);
        list.add(11);
        list.add(22);
        list.add(3);
        list.add(8);

        List<Integer> r = sorter.mergeSort(list);

        assertTrue(r.get(0)== 1);
        assertTrue(r.get(1)== 2);
        assertTrue(r.get(2)== 3);
        assertTrue(r.get(3)== 8);
        assertTrue(r.get(4)== 11);
        assertTrue(r.get(5)== 20);
        assertTrue(r.get(6)== 22);

    }
}
