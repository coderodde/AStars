package net.coderodde.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GraphPath<N> {

    private final List<N> pathData = new ArrayList<>();
    
    public GraphPath(List<N> path) {
        pathData.addAll(path);
    }
    
    public int size() {
        return pathData.size();
    }
    
    public List<N> getNodeList() {
        return Collections.<N>unmodifiableList(pathData);
    }
}
