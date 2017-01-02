package net.coderodde.graph;

import java.util.Comparator;

/**
 * This interface defines the minimum API a weight type must have so that it can
 * be applied to pathfinding.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 2, 2017)
 * @param <M> the actual element type.
 */
public interface OrderedMonoid<M> extends Comparator<M> {

    /**
     * Returns the identity element of this monoid.
     * 
     * @return the identity element.
     */
    public M identity();
    
    /**
     * Returns the inverse of the input element.
     * 
     * @param element the element to inverse.
     * @return the inversion of the input element.
     */
    public M inverse(M element);
    
    /**
     * Applies this monoid's operation to the two input elements and returns the
     * result.
     * 
     * @param element1 the first element.
     * @param element2 another element.
     * @return the result of applying the operation.
     */
    public M apply(M element1, M element2);
}
