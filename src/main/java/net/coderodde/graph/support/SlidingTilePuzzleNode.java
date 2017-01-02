package net.coderodde.graph.support;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class implements a sliding tile puzzle node. Each node is a square 
 * matrix with values {@code 1, 2, ..., n^2 - 1}, and a special value 
 * {@code 0}, which denotes an empty tile to which any neighbor tile may be 
 * moved.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 2, 2017)
 */
public final class SlidingTilePuzzleNode {
    
    /**
     * The minimum number of columns and rows.
     */
    private static final int MINIMUM_WIDTH = 2;

    /**
     * The maximum number of columns and rows.
     */
    private static final int MAXIMUM_WIDTH = 11;
    
    /**
     * The actual underlying matrix.
     */
    private final byte[][] matrix;
    
    /**
     * The <tt>x</tt>-coordinate of the zero tile.
     */
    private byte emptyTileX;
    
    /**
     * The <tt>y</tt>-coordinate of the zero tile.
     */
    private byte emptyTileY;
    
    /**
     * Constructs a solved puzzle node.
     * 
     * @param width the number of both rows and columns in the matrix.
     */
    public SlidingTilePuzzleNode(int width) {
        checkWidth(width);
        matrix = new byte[width][width];
        
        byte value = 1;
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x, ++value) {
                if (value != width * width) {
                    matrix[y][x] = value;
                }
            }
        }
        
        emptyTileX = (byte)(width - 1);
        emptyTileY = (byte)(width - 1);
    }
    
    public SlidingTilePuzzleNode(SlidingTilePuzzleNode other) {
        int width = other.matrix.length;
        this.matrix = new byte[width][width];
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x) {
                this.matrix[y][x] = other.matrix[y][x];
            }
        }
        
        this.emptyTileX = other.emptyTileX;
        this.emptyTileY = other.emptyTileY;
    }
    
    public SlidingTilePuzzleNode slideUp() {
        if (emptyTileY == 0) {
            return null;
        }
        
        SlidingTilePuzzleNode newNode = new SlidingTilePuzzleNode(this);
        newNode.swap(emptyTileX, emptyTileY, emptyTileX, emptyTileY - 1);
        newNode.emptyTileY--;
        return newNode;
    }
    
    public SlidingTilePuzzleNode slideDown() {
        if (emptyTileY == matrix.length - 1) {
            return null;
        }
        
        SlidingTilePuzzleNode newNode = new SlidingTilePuzzleNode(this);
        newNode.swap(emptyTileX, emptyTileY, emptyTileX, emptyTileY + 1);
        newNode.emptyTileY++;
        return newNode;
    }
    
    public SlidingTilePuzzleNode slideLeft() {
        if (emptyTileX == 0) {
            return null;
        }
        
        SlidingTilePuzzleNode newNode = new SlidingTilePuzzleNode(this);
        newNode.swap(emptyTileX, emptyTileY, emptyTileX - 1, emptyTileY);
        newNode.emptyTileX--;
        return newNode;
    }
    
    public SlidingTilePuzzleNode slideRight() {
        if (emptyTileX == matrix.length - 1) {
            return null;
        }
        
        SlidingTilePuzzleNode newNode = new SlidingTilePuzzleNode(this);
        newNode.swap(emptyTileX, emptyTileY, emptyTileX + 1, emptyTileY);
        newNode.emptyTileX++;
        return newNode;
    }
    
    @Override
    public int hashCode() {
        int width = matrix.length;
        int i = 1; 
        int hash = 0;
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x, ++i) {
                hash += matrix[y][x] * i;
            }
        }
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        SlidingTilePuzzleNode other = (SlidingTilePuzzleNode) obj;
        return Arrays.deepEquals(this.matrix, other.matrix);
    }
    
    @Override
    public String toString() {
        int width = matrix.length;
        StringBuilder sb = new StringBuilder();
        
        for (int y = 0; y != width; ++y) {
            for (int x = 0; x != width; ++x) {
                sb.append(String.format("%2d ", matrix[y][x]));
            }
            
            sb.append('\n');
        }
        
        return sb.toString();
    }
    
    private void swap(int xFrom, int yFrom, int xTo, int yTo) {
        byte tmp = matrix[yFrom][xFrom];
        matrix[yFrom][xFrom] = matrix[yTo][xTo];
        matrix[yTo][xTo] = tmp;
    }
    
    private void checkWidth(int width) {
        if (width < MINIMUM_WIDTH) {
            throw new IllegalArgumentException(
                    "Width is too small (" + width + "). Must be at least " +
                    MINIMUM_WIDTH + ".");
        }
        
        if (width > MAXIMUM_WIDTH) {
            throw new IllegalArgumentException(
                    "Dimension is too large (" + width + "). Must be at most " +
                    MAXIMUM_WIDTH + ".");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SlidingTilePuzzleNode node = new SlidingTilePuzzleNode(4);
        SlidingTilePuzzleNode node2;
        
        while (true) {
            System.out.println(node);
            System.out.print("> ");
            String input = scanner.next().toLowerCase().trim();
            
            switch (input) {
                case "up":
                    node2 = node.slideUp();
                    
                    if (node2 == null) {
                        System.out.println("null");
                    } else {
                        node = node2;
                    }
                    break;
                    
                case "down":
                    node2 = node.slideDown();
                    
                    if (node2 == null) {
                        System.out.println("null");
                    } else {
                        node = node2;
                    }
                    break;
                    
                case "left":
                    node2 = node.slideLeft();
                    
                    if (node2 == null) {
                        System.out.println("null");
                    } else {
                        node = node2;
                    }
                    break;
                    
                case "right":
                    node2 = node.slideRight();
                    
                    if (node2 == null) {
                        System.out.println("null");
                    } else {
                        node = node2;
                    }
                    break;
            }
        }
    }
}
