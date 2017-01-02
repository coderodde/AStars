package net.coderodde.graph.support;

import java.util.Objects;
import net.coderodde.graph.NodeExpander;

public class DirectedGraphChildNodeExpander implements NodeExpander<Integer> {

    private final DirectedGraph directedGraph;
    
    public DirectedGraphChildNodeExpander(DirectedGraph directedGraph) {
        this.directedGraph = 
                Objects.requireNonNull(directedGraph, 
                        "The input directed graph is null.");
    }
    @Override
    public Iterable<Integer> expand(Integer node) {
        return directedGraph.getChildrenOf(node);
    }
}
