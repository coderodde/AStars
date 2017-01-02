
import java.awt.geom.Point2D;
import java.util.Random;
import net.coderodde.graph.support.DirectedGraph;
import net.coderodde.graph.support.DirectedGraphNodeCoordinates;
import net.coderodde.graph.support.DirectedGraphWeightFunction;
import net.coderodde.graph.support.EuclideanDirectedGraphHeuristicFunction;

public class Demo {

    private static final int EXPLICIT_GRAPH_SIZE = 1000;
    private static final int EXPLICIT_GRAPH_ARCS = 10_000;
    private static final float EXPLICIT_GRAPH_AREA_WIDTH = 1e4f;
    private static final float EXPLICIT_GRAPH_AREA_HEIGHT = 6e3f;
    private static final float EXPLICIT_WEIGHT_COST_FACTOR = 1.2f;
    
    public static void main(String[] args) {
        long seed = System.nanoTime();
        Random random = new Random(seed);
        System.out.println("Seed = " + seed);
        benchmarkExplicitGraph(random,
                               EXPLICIT_GRAPH_SIZE,
                               EXPLICIT_GRAPH_ARCS,
                               EXPLICIT_GRAPH_AREA_WIDTH,
                               EXPLICIT_GRAPH_AREA_HEIGHT,
                               EXPLICIT_WEIGHT_COST_FACTOR);
    }
    
    private static void benchmarkExplicitGraph(Random random,
                                               int nodes,
                                               int arcs,
                                               float graphAreaWidth,
                                               float graphAreaHeight,
                                               float weightCostFactor) {
        Triple<DirectedGraph,
               DirectedGraphWeightFunction,
               EuclideanDirectedGraphHeuristicFunction> graphData 
               = createRandomDirectedGraph(nodes,
                                           arcs,
                                           random,
                                           graphAreaWidth,
                                           graphAreaHeight,
                                           weightCostFactor);
        
        int sourceNode = random.nextInt(nodes);
        int targetNode = random.nextInt(nodes);
        
        benchmarkAstar(graphData, sourceNode, targetNode);
        benchmarkNBAstar(graphData, sourceNode, targetNode);
    }
    
    private static void 
        benchmarkAstar(Triple<DirectedGraph,
                              DirectedGraphWeightFunction,
                              EuclideanDirectedGraphHeuristicFunction>
                       graphData,
                       Integer sourceNode,
                       Integer targetNode) {
            
    }
    
    private static void 
        benchmarkNBAstar(Triple<DirectedGraph,
                                DirectedGraphWeightFunction,
                                EuclideanDirectedGraphHeuristicFunction>
                         graphData,
                         Integer sourceNode,
                         Integer targetNode) {
            
    }
    
    private static final class Triple<F, S, T> {
        final F first;
        final S second;
        final T third;
        
        Triple(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
    
    private static Triple<DirectedGraph,
                          DirectedGraphWeightFunction,
                          EuclideanDirectedGraphHeuristicFunction>
        createRandomDirectedGraph(int nodes,
                                  int arcs, 
                                  Random random,
                                  float graphAreaWidth,
                                  float graphAreaHeight,
                                  float costFactor) {
        if (costFactor < 1.0f) {
            throw new IllegalArgumentException(
                    "The cost factor must be at least 1.0f; received " +
                    costFactor + ".");
        }
            
        DirectedGraph directedGraph = new DirectedGraph();
        DirectedGraphWeightFunction directedGraphWeightFunction = 
                new DirectedGraphWeightFunction();
        DirectedGraphNodeCoordinates directedGraphNodeCoordinates = 
                new DirectedGraphNodeCoordinates();
        
        // Create nodes:
        for (int node = 0; node < nodes; ++node) {
            directedGraph.addNode(node);
            Point2D.Float nodeCoordinates = getRandomPoint(graphAreaWidth,
                                                           graphAreaHeight,
                                                           random);
            directedGraphNodeCoordinates.putCoordinate(node, nodeCoordinates);
        }
        
        // Create arcs:
        while (arcs-- > 0) {
            int tailNode = random.nextInt(nodes);
            int headNode = random.nextInt(nodes);
            
            directedGraph.addArc(tailNode, headNode);
            
            Point2D.Float tailNodeCoordinates = 
                    directedGraphNodeCoordinates.getCoordinate(tailNode);
            
            Point2D.Float headNodeCoordinates =
                    directedGraphNodeCoordinates.getCoordinate(headNode);
            
            float distance = 
                    (float) tailNodeCoordinates.distance(headNodeCoordinates);
            
            float weight = distance * costFactor;
            directedGraphWeightFunction.putWeight(tailNode, headNode, weight);
        }
        
        EuclideanDirectedGraphHeuristicFunction 
                euclideanDirectedGraphHeuristicFunction =
                new EuclideanDirectedGraphHeuristicFunction(
                        directedGraphNodeCoordinates);
        
        return new Triple<>(directedGraph,
                            directedGraphWeightFunction,
                            euclideanDirectedGraphHeuristicFunction);
    }
        
    private static Point2D.Float getRandomPoint(float graphAreaWidth,
                                                float graphAreaHeight,
                                                Random random) {
        return new Point2D.Float(random.nextFloat() * graphAreaWidth,
                                 random.nextFloat() * graphAreaHeight);
    }
}
