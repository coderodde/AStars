package net.coderodde.graph.support;

import java.util.ArrayList;
import java.util.List;
import net.coderodde.graph.NodeExpander;

public class SlidingTilePuzzleNodeExpander 
        implements NodeExpander<SlidingTilePuzzleNode> {

    // Up, down, left, right.
    private static final int SLIDING_DIRECTIONS = 4;
    
    @Override
    public Iterable<SlidingTilePuzzleNode> expand(SlidingTilePuzzleNode node) {
        List<SlidingTilePuzzleNode> neighborList = 
                new ArrayList<>(SLIDING_DIRECTIONS);
        
        SlidingTilePuzzleNode neighbor = node.slideUp();
        
        if (neighbor != null) {
            neighborList.add(neighbor);
        }
        
        neighbor = node.slideDown();
        
        if (neighbor != null) {
            neighborList.add(neighbor);
        }
        
        neighbor = node.slideLeft();
        
        if (neighbor != null) {
            neighborList.add(neighbor);
        }
        
        neighbor = node.slideRight();
        
        if (neighbor != null) {
            neighborList.add(neighbor);
        }
        
        return neighborList;
    }
}
