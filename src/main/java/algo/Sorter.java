package algo;

import java.util.ArrayList;
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
            T el = toSort.get(i);
            int j = i - 1;
            while (j >= 0 && toSort.get(j).compareTo(el) > 0) {
                toSort.set(j + 1, toSort.get(j));
                --j;
            }
            toSort.set(j + 1, el);
        }

        return toSort;
    }


    public List<T> mergeSort(List<T> subject) {
        return doMerge(subject);
    }

    private List<T> doMerge(List<T> subject) {
        if (subject.size() == 1) return subject;
        int q = subject.size() / 2;
        return merge(doMerge(subject.subList(0, q)), doMerge(subject.subList(q, subject.size())));
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> merged = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < left.size() || j < right.size()) {
            if (i == left.size()) {
                merged.add(right.get(j));
                j++;
            } else if (j == right.size()) {
                merged.add(left.get(i));
                i++;
            } else {
                T l = left.get(i);
                T r = right.get(j);

                if (l.compareTo(r) < 0) {
                    merged.add(l);
                    i++;
                } else {
                    merged.add(r);
                    j++;
                }
            }

        }

        return merged;
    }

    public void quickSort(List<T> subject){
        qSort(subject,0,subject.size()-1);
    }

    private void qSort(List<T> subject,int s,int e){
        if(s < e){
            int p = partition(subject,s,e);
            qSort(subject,s,p-1);
            qSort(subject,p + 1,e);
        }
    }

    private int partition(List<T> subject,int s,int e){

        int i = s-1;
        int j = s;
        T el = subject.get(e);
        while(j <= e){
            T curr = subject.get(j);
            if(curr.compareTo(el) <= 0){
                i++;
                if(i != j){
                    T aux = subject.get(i);
                    subject.set(i,curr);
                    subject.set(j,aux);
                }
            }
            j++;
        }
        return i;
    }
}
