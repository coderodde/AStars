package net.coderodde.graph.shortestpath;

public interface HeuristicFunction<N, W> {

    /**
     * Sets the target node for this heuristic function. This allows caching the
     * target node related data without recomputing it over and over again.
     * 
     * @param targetNode the target node.
     */
    public void setTargetNode(N targetNode); 
    
    /**
     * Returns an optimistic estimate (i.e., a lower bound) from {@code node} to
     * the current target node of this heuristic function.
     * 
     * @param node the node for which to compute the shortest path estimate.
     * @return an optimistic shortest path from {@code node} to the target.
     */
    public W estimateDistance(N node);
    
    /**
     * Returns another heuristic function that has the same implementation as 
     * this heuristic function. We need this for bidirectional search, where
     * the same heuristic function is shared among two search directions.
     * 
     * @return another heuristic function with the same implementation.
     */
    public HeuristicFunction<N, W> copyImplementation();
}
