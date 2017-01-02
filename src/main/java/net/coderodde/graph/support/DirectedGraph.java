package net.coderodde.graph.support;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a directed graph in which nodes are represented by 
 * unique integer values, and the directed arcs are ordered pairs of those 
 * integers.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 1, 2017)
 */
public final class DirectedGraph {

    private int numberOfArcs;
    
    private final Map<Integer, Set<Integer>> childrenMap =
            new LinkedHashMap<>();
    
    private final Map<Integer, Set<Integer>> parentMap = 
            new LinkedHashMap<>();
    
    public void addNode(int node) {
        if (!childrenMap.containsKey(node)) {
            childrenMap.put(node, new LinkedHashSet<>());
            parentMap.put  (node, new LinkedHashSet<>());
        }
    }
    
    public void addArc(int tailNode, int headNode) {
        if (hasArc(tailNode, headNode)) {
            return;
        }
        
        addNode(tailNode);
        addNode(headNode);
        
        childrenMap.get(tailNode).add(headNode);
        parentMap  .get(headNode).add(tailNode);
        
        numberOfArcs++;
    }
    
    public boolean hasNode(int node) {
        return childrenMap.containsKey(node);
    }
    
    public boolean hasArc(int tailNode, int headNode) {
        if (!childrenMap.containsKey(tailNode)) {
            return false;
        }
        
        return childrenMap.get(tailNode).contains(headNode);
    }
    
    public void removeNode(int node) {
        if (!childrenMap.containsKey(node)) {
            return;
        }
        
        Set<Integer> childNodes = new HashSet<>(childrenMap.get(node));
        Set<Integer> parentNodes = new HashSet<>(parentMap.get(node));
        
        numberOfArcs -= childNodes.size() + parentNodes.size();
        
        childNodes.stream().forEach((child) -> {
            parentMap.get(child).remove(node);
        });
        
        parentNodes.stream().forEach((parent) -> {
            childrenMap.get(parent).remove(node);
        });
        
        childrenMap.remove(node);
        parentMap.remove(node);
    }
    
    public void removeArc(int tailNode, int headNode) {
        if (hasArc(tailNode, headNode)) {
            childrenMap.get(tailNode).remove(headNode);
            parentMap.get(headNode).remove(tailNode);
            numberOfArcs--;
        }
    }
    
    public int size() {
        return childrenMap.size();
    }
    
    public int numberOfArcs() {
        return numberOfArcs;
    }
    
    public void clear() {
        childrenMap.clear();
        parentMap.clear();
        numberOfArcs = 0;
    }
    
    public Set<Integer> getParentsOf(int node) {
        return Collections.<Integer>unmodifiableSet(parentMap.get(node));
    }
    
    public Set<Integer> getChildrenOf(int node) {
        return Collections.<Integer>unmodifiableSet(childrenMap.get(node));
    }
}
