package net.coderodde.graph.shortestpath;

import static java.util.Objects.requireNonNull;
import java.util.concurrent.TimeUnit;
import net.coderodde.graph.GraphPath;

public abstract class WeightedShortestPathFinder<N> {

    public enum SearchStatus {
        
        /**
         * The finder is ready to search.
         */
        IDLE,
        
        /**
         * Search in progress.
         */
        SEARCHING,
        
        /**
         * The finder has found the target node, and user can ask for the path.
         */
        SHORTEST_PATH_FOUND,
        
        /**
         * The search is complete, yet there is no path to target node.
         */
        TARGET_UNREACHABLE
    }
    
    protected SearchStatus searchStatus;
    
    public SearchStatus getSearchStatus() {
        return searchStatus;
    }
    
    /**
     * Resets this finder to the beginning of the search from {@code sourceNode}
     * to {@code targetNode}.
     * 
     * @param sourceNode the source node.
     * @param targetNode the target node.
     */
    public abstract void initializeSearch(N sourceNode, N targetNode);
    
    /**
     * Performs on small step in the search and returns.
     */
    public abstract void step();
    
    /**
     * Keeps searching for at most approximately time duration represented by
     * {@code timeUnit}.
     * 
     * @param duration the number of time units to wait.
     * @param timeUnit the search duration.
     */
    public abstract void searchFor(long duration, TimeUnit timeUnit);
    
    /**
     * Keeps searching for shortest path for as long as the target node is
     * reached, or the entire component starting from the source node is crawled
     * without reaching the target node.
     * 
     * @param sourceNode the source node.
     * @param targetNode the target node.
     */
    public void search(N sourceNode, N targetNode) {
        initializeSearch(
                requireNonNull(sourceNode, "The source node is null."),
                requireNonNull(targetNode, "The target node is null."));
        
        mainLoop:
        while (true) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            SearchStatus searchStatus = getSearchStatus();
            
            switch (searchStatus) {
                case SHORTEST_PATH_FOUND:
                case TARGET_UNREACHABLE:
                    break mainLoop;
            }
            
            step();
        }
    }
    
    /**
     * If this finder previously had reached the target node, this method will
     * return the shortest path. Otherwise an exception will be thrown.
     * Resetting the search
     * 
     * @return the shortest path if available.
     */
    public abstract GraphPath<N> getPath();
}
