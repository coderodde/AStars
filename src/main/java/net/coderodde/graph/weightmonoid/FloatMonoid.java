package net.coderodde.graph.weightmonoid;

import net.coderodde.graph.OrderedMonoid;

public final class FloatMonoid implements OrderedMonoid<Float> {

    @Override
    public Float identity() {
        return 0.0f;
    }

    @Override
    public Float inverse(Float element) {
        return -element;
    }

    @Override
    public Float apply(Float element1, Float element2) {
        return element1 + element2;
    }

    @Override
    public int compare(Float o1, Float o2) {
        return Float.compare(o1, o2);
    }
}
