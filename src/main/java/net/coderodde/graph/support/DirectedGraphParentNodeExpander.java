package net.coderodde.graph.support;

import java.util.Objects;
import net.coderodde.graph.DirectedGraph;
import net.coderodde.graph.NodeExpander;

public class DirectedGraphParentNodeExpander implements NodeExpander<Integer> {

    private final DirectedGraph directedGraph;
    
    public DirectedGraphParentNodeExpander(DirectedGraph directedGraph) {
        this.directedGraph =
                Objects.requireNonNull(directedGraph, 
                        "The input directed graph is null.");
    }
    
    @Override
    public Iterable<Integer> expand(Integer node) {
        return directedGraph.getParentsOf(node);
    }
}
