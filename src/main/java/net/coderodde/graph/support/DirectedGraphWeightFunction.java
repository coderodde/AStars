package net.coderodde.graph.support;

import java.util.HashMap;
import java.util.Map;

public final class DirectedGraphWeightFunction {

    private final Map<Integer, Map<Integer, Float>> weightMap = new HashMap<>();
    
    public void putWeight(Integer tail, Integer head, Float weight) {
        if (!weightMap.containsKey(tail)) {
            weightMap.put(tail, new HashMap<>());
        }
        
        weightMap.get(tail).put(head, weight);
    }
    
    public Float getWeight(Integer tail, Integer head) {
        return weightMap.get(tail).get(head);
    }
}
