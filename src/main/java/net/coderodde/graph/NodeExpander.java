package net.coderodde.graph;

import java.util.Iterator;

/**
 * This interface defines the application programming interface for generating
 * neighbors of graph nodes.
 * 
 * @author Rodion "rodde" Efremov
 * @param <N> the actual node type.
 * @version 1.6 (Jan 1, 2017)
 */
public interface NodeExpander<N> {

    /**
     * Returns an iterator over the argument node's neighbors. In an undirected
     * graph, all neighbor nodes of the argument node should be returned. If the 
     * graph is directed, only parent or child nodes are to be returned 
     * depending on the direction of the search.
     * 
     * @param node the node whose neighbors to return.
     * @return an iterator over neighbor nodes.
     */
    public Iterable<N> expand(N node);
}
