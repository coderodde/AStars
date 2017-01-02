package net.coderodde.graph.weightmonoid;

import net.coderodde.graph.OrderedMonoid;

public final class IntegerMonoid implements OrderedMonoid<Integer> {

    @Override
    public Integer identity() {
        return 0;
    }

    @Override
    public Integer inverse(Integer element) {
        return -element;
    }

    @Override
    public Integer apply(Integer element1, Integer element2) {
        return element1 + element2;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1, o2);
    }
}
