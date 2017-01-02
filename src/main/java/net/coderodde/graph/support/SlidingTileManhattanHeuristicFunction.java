package net.coderodde.graph.support;

import net.coderodde.graph.shortestpath.HeuristicFunction;

public final class SlidingTileManhattanHeuristicFunction 
implements HeuristicFunction<SlidingTilePuzzleNode, Integer> {

    private int[] xArray;
    private int[] yArray;
    
    public SlidingTileManhattanHeuristicFunction(int totalTiles) {
        this.xArray = new int[totalTiles];
        this.yArray = new int[totalTiles];
    }

    @Override
    public void setTargetNode(SlidingTilePuzzleNode targetNode) {
        int width = targetNode.getWidth();
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x) {
                byte tile = targetNode.get(x, y);
                xArray[tile] = x;
                yArray[tile] = y;
            }
        }
    }

    @Override
    public Integer estimateDistance(SlidingTilePuzzleNode node) {
        int width = node.getWidth();
        int heuristicEstimate = 0;
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x) {
                byte tile = node.get(x, y);
                heuristicEstimate += 
                        Math.abs(xArray[tile] - x) + Math.abs(yArray[tile] - y);
            }
        }
        
        return heuristicEstimate;
    }

    @Override
    public HeuristicFunction<SlidingTilePuzzleNode, Integer>
        copyImplementation() {
        return new SlidingTileManhattanHeuristicFunction(xArray.length);
    }
}
