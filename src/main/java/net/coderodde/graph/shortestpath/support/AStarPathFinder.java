package net.coderodde.graph.shortestpath.support;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.concurrent.TimeUnit;
import net.coderodde.graph.GraphPath;
import net.coderodde.graph.WeightFunction;
import net.coderodde.graph.shortestpath.HeuristicFunction;
import net.coderodde.graph.shortestpath.WeightedShortestPathFinder;

public final class AStarPathFinder<N, W extends Comparable<W>> 
        extends WeightedShortestPathFinder<N> {

    private static final int ITERATIONS_PER_TIMED_STEP = 10;
    
    private final WeightFunction<N, W> weightFunction;
    private final HeuristicFunction<N, W> heuristicFunction;
    private N sourceNode;
    private N targetNode;
    
    private static final class 
            PriorityQueueNode<N, W extends Comparable<? super W>> 
            implements Comparable<PriorityQueueNode<N, W>> {

        final N node;
        final W priority;
        
        PriorityQueueNode(N node, W priority) {
            this.node = node;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityQueueNode<N, W> o) {
            return priority.compareTo(o.priority);
        }
    }
    
    public AStarPathFinder(WeightFunction<N, W> weightFunction,
                           HeuristicFunction<N, W> heuristicFunction) {
        this.weightFunction = 
                requireNonNull(weightFunction, "The weight function is null.");
        
        this.heuristicFunction = 
                requireNonNull(heuristicFunction,
                        "The heuristic function is null.");
        
    }
    
    @Override
    public void initializeSearch(N sourceNode, N targetNode) {
        searchStatus = SearchStatus.SEARCHING;
        this.sourceNode = requireNonNull(sourceNode, 
                          "The source node is null.");
        
        this.targetNode = requireNonNull(targetNode,
                          "The target node is null.");
    }

    @Override
    public void step() {
        checkStateIsSearching();
        
    }

    @Override
    public void searchFor(long duration, TimeUnit timeUnit) {
        long nanoseconds = timeUnit.toNanos(duration);
        System.out.println("Nanoseconds: " + nanoseconds);
        
        long startTime = System.nanoTime();
        
        while (true) {
            for (int iteration = 0; 
                    iteration != ITERATIONS_PER_TIMED_STEP; 
                    iteration++) {
                // Perform one expansion:
                step();
                
                if (searchStatus.equals(SearchStatus.SHORTEST_PATH_FOUND)) {
                    return;
                }
                
                if (searchStatus.equals(SearchStatus.TARGET_UNREACHABLE)) {
                    return;
                }
            }
            
            long endTime = System.nanoTime();
            long totalDuration = endTime - startTime;
            
            if (totalDuration >= nanoseconds) {
                return;
            }
        }
    }

    @Override
    public GraphPath<N> getPath() {
        switch (searchStatus) {
            case SEARCHING:
                throw new IllegalStateException(
                        "The search is not yet complete.");
                
            case IDLE:
                throw new IllegalStateException("There is no search going on.");
                
            case SHORTEST_PATH_FOUND:
                searchStatus = SearchStatus.IDLE;
                return tracebackPath();
                
            case TARGET_UNREACHABLE:
                return null;
                
            default:
                throw new IllegalStateException("Unknown path finder state.");
        }
    }
    
    private void checkStateIsSearching() {
        if (!searchStatus.equals(SearchStatus.SEARCHING)) {
            throw new IllegalStateException(
                    "The search status is not searching.");
        }
    }

    private GraphPath<N> tracebackPath() {
        List<N> path = new ArrayList<>();
        
        return new GraphPath<>(path);
    }
}
