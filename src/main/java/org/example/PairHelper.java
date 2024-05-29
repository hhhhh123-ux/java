package org.example;

public class PairHelper {
    static int addPair(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    public static void main(String[] args) {
        Pair<Integer> t=new Pair<>(1, 2);
        addPair(t);
    }
}
