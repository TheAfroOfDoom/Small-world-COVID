/*
  Jordan Williams
*/
package cc;

import java.lang.Math;
import java.util.*;
import java.util.function.*;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.*;

public final class HyperbolicRandomGraphGenerator
    //implements
    //GraphGenerator<V, E, V>
{
    //private static final boolean DEFAULT_ALLOW_LOOPS = false;
    //private static final boolean DEFAULT_ALLOW_MULTIPLE_EDGES = false;

    private final Random rng;
    private final int n;
    private final double k;
    private final double zeta;
    public Vertex[] vertices;
    public int[][] edgeArray;

    private final double maxRadius;
    //private final boolean loops;
    //private final boolean multipleEdges;

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature)
    {
        this(n, k, curvature, new Random());
    }

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param seed seed for the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature, long seed)
    {
        this(n, k, curvature, new Random(seed));
    }

    /**
     * Create a new $G(n, M)$ random graph generator
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param rng the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature, Random rng)
    {
        if (n < 0) {
            throw new IllegalArgumentException("number of vertices must be non-negative");
        }
        this.n = n;
        if (k < 0) {
            throw new IllegalArgumentException("average vertex degree must be non-negative");
        }
        this.k = k;
        assert(curvature < 0);  // Hyperbolic curvature is negative
        this.zeta = Math.sqrt(-curvature);
        this.rng = Objects.requireNonNull(rng);

        // v = Math.PI * this.k / 8;
        // n = v * Math.exp(maxRadius / 2) => n / v = Math.exp(...) => 2 * Math.log(n / v) = maxRadius
        //this.maxRadius = 2 * Math.log(this.n / (Math.PI * this.k / 8));
        this.maxRadius = 400;

        GenerateVertices();
    }

    public double getMaxRadius()
    {
        return this.maxRadius;
    }

    public void GenerateVertices()
    {
        this.vertices = new Vertex[this.n];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(this.maxRadius);
        }
    }

    
    public static int[][] GenerateEdges() {
        // Create the VertexFactory so the generator can create vertices
        Supplier<String> vSupplier = new Supplier<String>() {
            private int id = 0;

            @Override
            public String get() {
                return String.valueOf(id++);
            }
        };

        // Create the graph object
        Graph<String, DefaultEdge> completeGraph = new SimpleGraph<>(vSupplier,
                SupplierUtil.createDefaultEdgeSupplier(), false);

        // Create the CompleteGraphGenerator object
        CompleteGraphGenerator<String, DefaultEdge> completeGenerator = new CompleteGraphGenerator<>(size);

        // Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(completeGraph);

        return GraphGenerator.convertEdgeSet(completeGraph.edgeSet());
    }

    public double HyperbolicDistance(Vertex v1, Vertex v2)
    {
        double dAngle = Math.PI - Math.abs(Math.PI - Math.abs(v1.angle - v2.angle));
        // approximation
        return v1.radius + v2.radius + (2 * Math.log(dAngle / 2) / this.zeta);
    }
}