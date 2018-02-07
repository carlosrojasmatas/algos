package algo;

import java.util.Collections;
import java.util.List;

public class Sorter<T extends Comparable<T>> {

    public List<T> selectionSorter(List<T> toSort) {

        for (int i = 0; i < toSort.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < toSort.size(); j++) {
                if (toSort.get(min).compareTo(toSort.get(j)) > 0) {
                    min = j;
                }
            }
            T buff = toSort.get(i);
            toSort.set(i, toSort.get(min));
            toSort.set(min, buff);
        }
        return toSort;
    }

    public List<T> insertionSort(List<T> toSort) {
        for (int i = 1; i < toSort.size(); i++) {
            T el  = toSort.get(i);
            int j  =  i - 1;
            while(j >= 0 && toSort.get(j).compareTo(el) > 0 ){
                toSort.set(j + 1,toSort.get(j));
                --j;
            }
            toSort.set(j + 1,el);
        }

        return toSort;
    }


    private List<T> swap(List<T> elements, int left, int right) {
        List<T> safeEls = elements;
        T buff = elements.get(left);
        elements.set(left, elements.get(right));
        elements.set(right, buff);
        return safeEls;
    }

}
