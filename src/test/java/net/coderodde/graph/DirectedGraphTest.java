package net.coderodde.graph;

import net.coderodde.graph.support.DirectedGraph;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectedGraphTest {
    
    private final DirectedGraph graph = new DirectedGraph();
    
    @Before
    public void setUp() {
        graph.clear();
    }
    
    @Test
    public void testAddNode() {
        for (int i = 0; i != 1000; ++i) {
            assertEquals(i, graph.size());
            graph.addNode(i);
            assertEquals(i + 1, graph.size());
        }
    }

    @Test
    public void testAddArc() {
        graph.addArc(0, 1);
        assertEquals(2, graph.size());
        assertEquals(1, graph.numberOfArcs());
        
        graph.addArc(1, 2);
        assertEquals(3, graph.size());
        assertEquals(2, graph.numberOfArcs());
        
        graph.addArc(2, 0);
        assertEquals(3, graph.size());
        assertEquals(3, graph.numberOfArcs());
        
        graph.addNode(3);
        graph.addArc(2, 3);
        assertEquals(4, graph.size());
        assertEquals(4, graph.numberOfArcs());
    }

    @Test
    public void testHasNode() {
        for (int i = 0; i != 100; ++i) {
            assertFalse(graph.hasNode(i));
            graph.addNode(i);
            assertTrue(graph.hasNode(i));
        }
    }

    @Test
    public void testHasArc() {
        assertFalse(graph.hasArc(0, 1));
        assertFalse(graph.hasArc(1, 2));
        
        graph.addArc(0, 1);
        
        assertTrue(graph.hasArc(0, 1));
        assertFalse(graph.hasArc(1, 2));
        
        graph.addArc(1, 2);
        
        assertTrue(graph.hasArc(0, 1));
        assertTrue(graph.hasArc(1, 2));
        
        assertFalse(graph.hasArc(1, 0));
        assertFalse(graph.hasArc(2, 1));
    }

    @Test
    public void testRemoveNode() {
        graph.addArc(0, 1);
        graph.addArc(0, 2);
        graph.addArc(0, 3);
        graph.addArc(1, 0);
        graph.addArc(4, 0);
        graph.addArc(4, 2);
        assertEquals(6, graph.numberOfArcs());
        
        graph.removeNode(0);
        
        assertEquals(1, graph.numberOfArcs());
        assertTrue(graph.hasNode(1));
        assertTrue(graph.hasNode(2));
        assertTrue(graph.hasNode(3));
        assertTrue(graph.hasNode(4));
        assertTrue(graph.hasArc(4, 2));
    }

    @Test
    public void testRemoveArc() {
        graph.addArc(0, 1);
        assertEquals(2, graph.size());
        assertEquals(1, graph.numberOfArcs());
        
        graph.addArc(0, 2);
        assertEquals(3, graph.size());
        assertEquals(2, graph.numberOfArcs());
        
        graph.removeArc(2, 0); // No such arc.
        assertEquals(3, graph.size());
        assertEquals(2, graph.numberOfArcs());
        
        graph.removeArc(0, 1);
        assertEquals(3, graph.size());
        assertEquals(1, graph.numberOfArcs());
        
        graph.removeArc(0, 2);
        assertEquals(3, graph.size());
        assertEquals(0, graph.numberOfArcs());
    }
    
    @Test
    public void testSize() {
        for (int i = 0; i != 100; ++i) {
            assertEquals(i, graph.size());
            graph.addNode(i);
            assertEquals(i + 1, graph.size());
        }
    }
    
    @Test
    public void testNumberOfArcs() {
        assertEquals(0, graph.numberOfArcs());
        graph.addArc(0, 1);
        assertEquals(1, graph.numberOfArcs());
        graph.addArc(0, 2);
        assertEquals(2, graph.numberOfArcs());
        graph.addArc(5, 5);
        assertEquals(3, graph.numberOfArcs());
    }

    @Test
    public void testGetParentsOf() {
        graph.addArc(1, 0);
        graph.addArc(2, 0);
        graph.addArc(0, 3);
        graph.addArc(0, 4);
        
        Set<Integer> parents = graph.getParentsOf(0);
        
        assertTrue(parents.contains(1));
        assertTrue(parents.contains(2));
    }

    @Test
    public void testGetChildrenOf() {
        graph.addArc(1, 0);
        graph.addArc(2, 0);
        graph.addArc(0, 3);
        graph.addArc(0, 4);
        
        Set<Integer> parents = graph.getChildrenOf(0);
        
        assertTrue(parents.contains(3));
        assertTrue(parents.contains(4));
    }
    
    @Test
    public void testClear() {
        graph.addArc(1, 2);
        graph.addArc(3, 4);
        
        assertEquals(4, graph.size());
        assertEquals(2, graph.numberOfArcs());
        
        graph.clear();
        
        assertEquals(0, graph.size());
        assertEquals(0, graph.numberOfArcs());
    }
}
