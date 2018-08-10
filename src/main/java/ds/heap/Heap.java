package ds.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T> {

    private List<T> innerList= new ArrayList<>();
    private final Comparator<T> c;

    public Heap(Comparator<T> c) {
        this.c = c;
    }

    public void insert(T value){
        if(innerList.isEmpty()){
            innerList.add(value);
        }


    }


}
