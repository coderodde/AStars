package net.coderodde.graph;

public interface WeightFunction<N, W> {

    /**
     * Associates the weight {@code weight} with the arc
     * {@code (tailNode, headNode)}.
     * 
     * @param tailNode the tail (starting) node of the arc.
     * @param headNode the head (ending) node of the arc.
     * @param weight the arc weight.
     */
    public void putWeight(N tailNode, N headNode, W weight);
    
    /**
     * Returns the weight of the arc {@code (tailNode, headNode)}.
     * 
     * @param tailNode the tail node of the arc.
     * @param headNode the head node of the arc.
     * @return the weight of the arc.
     */
    public W getWeight(N tailNode, N headNode);
}
