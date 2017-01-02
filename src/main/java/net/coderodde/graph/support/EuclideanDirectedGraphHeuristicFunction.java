package net.coderodde.graph.support;

import java.awt.geom.Point2D;
import java.util.Objects;
import net.coderodde.graph.shortestpath.HeuristicFunction;

public final class EuclideanDirectedGraphHeuristicFunction
        implements HeuristicFunction<Integer, Float>{

    private Point2D.Float targetNodeCoordinate;
    private final DirectedGraphNodeCoordinates directedGraphNodeCoordinates;
    
    public EuclideanDirectedGraphHeuristicFunction(
            DirectedGraphNodeCoordinates directedGraphNodeCoordinates) {
        this.directedGraphNodeCoordinates =
                Objects.requireNonNull(directedGraphNodeCoordinates,
                        "The directed graph node coordinates are null.");
    }
    
    @Override
    public Float estimateDistance(Integer node) {
        Point2D.Float nodeCoordinates = 
                directedGraphNodeCoordinates.getCoordinate(node);
        return (float) targetNodeCoordinate.distance(nodeCoordinates);
    }

    @Override
    public void setTargetNode(Integer targetNode) {
        targetNodeCoordinate = 
                directedGraphNodeCoordinates.getCoordinate(targetNode);
    }

    @Override
    public HeuristicFunction<Integer, Float> copyImplementation() {
        return new EuclideanDirectedGraphHeuristicFunction(
                directedGraphNodeCoordinates);
    }
}
