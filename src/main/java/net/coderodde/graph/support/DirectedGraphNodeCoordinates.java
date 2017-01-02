package net.coderodde.graph.support;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public final class DirectedGraphNodeCoordinates {

    private final Map<Integer, Point2D.Float> map = new HashMap<>();
    
    public void putCoordinate(Integer node, Point2D.Float coordinate) {
        map.put(node, coordinate);
    }
    
    public Point2D.Float getCoordinate(Integer node) {
        return  map.get(node);
    }
}
