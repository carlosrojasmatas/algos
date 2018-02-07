package algo;

import java.util.List;

public class Searcher<T extends Comparable> {

    public int linearSearch(List<T> seq, T el) {
        int pos = -1;
        boolean found = false;
        for (int i = 0; (!found && i < seq.size()); i++) {
            if (seq.get(i).equals(el)) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    public int recLinearSearch(List<T> seq, int pos, T el){
        if(pos > seq.size() - 1) return -1;
        if(seq.get(pos).equals(el)) return pos;
        else return recLinearSearch(seq, ++pos ,el);
    }

    public int binarySearch(List<T> seq, T el) {
        if(seq.isEmpty()) throw new IllegalArgumentException("List is empty");
        if (seq.size() == 1 && seq.get(0).equals(el)) return 0;
        else {
            return diveInto(seq, el, 0, seq.size() -1);
        }
    }

    private int diveInto(List<T> seq, T el, int start,int end) {
        if(start > end) return -1;
        int p = (start + end) / 2;

        T currEle = seq.get(p);
        if(currEle.equals(el)) return p;

        if(el.compareTo(currEle) > 0)
            return diveInto(seq,el, p + 1, end);
        else
            return diveInto(seq,el,start, p - 1);

    }

}
