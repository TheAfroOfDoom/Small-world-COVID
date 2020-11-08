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
// implements
// GraphGenerator<V, E, V>
{
    // private static final boolean DEFAULT_ALLOW_LOOPS = false;
    // private static final boolean DEFAULT_ALLOW_MULTIPLE_EDGES = false;

    private final Random rng;
    private final int n;
    private final double k;
    private final double zeta;
    public Vertex[] vertices;

    private final double maxRadius;
    // private final boolean loops;
    // private final boolean multipleEdges;

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create
     * self-loops or multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature) {
        this(n, k, curvature, new Random());
    }

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create
     * self-loops or multiple (parallel) edges between the same two vertices.
     * 
     * @param n    the number of vertices
     * @param k    the average vertex degree
     * @param seed seed for the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature, long seed) {
        this(n, k, curvature, new Random(seed));
    }

    /**
     * Create a new $G(n, M)$ random graph generator
     * 
     * @param n   the number of vertices
     * @param k   the average vertex degree
     * @param rng the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, double curvature, Random rng) {
        if (n < 0) {
            throw new IllegalArgumentException("number of vertices must be non-negative");
        }
        this.n = n;
        if (k < 0) {
            throw new IllegalArgumentException("average vertex degree must be non-negative");
        }
        this.k = k;
        assert (curvature < 0); // Hyperbolic curvature is negative
        this.zeta = Math.sqrt(-curvature);
        this.rng = Objects.requireNonNull(rng);

        // v = Math.PI * this.k / 8;
        // n = v * Math.exp(maxRadius / 2) => n / v = Math.exp(...) => 2 * Math.log(n /
        // v) = maxRadius
        // this.maxRadius = 2 * Math.log(this.n / (Math.PI * this.k / 8));
        this.maxRadius = 8;

        GenerateVertices();
        GenerateEdges();
    }

    public double getMaxRadius() {
        return this.maxRadius;
    }

    public void GenerateVertices() {
        this.vertices = new Vertex[this.n];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(this.maxRadius);
        }
    }

    public void GenerateEdges() {
        for (int i = vertices.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (HyperbolicDistance(vertices[i], vertices[j]) <= maxRadius) {
                    vertices[i].connections.add(j);
                    vertices[j].connections.add(i);
                }
            }
        }
    }

    public Vertex[] getCopy() {
        Vertex[] vertCopies = new Vertex[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            Vertex vertex = vertices[i];
            Vertex vertCopy = new Vertex(vertex.radius, vertex.angle);
            for (Integer conn : vertex.connections) {
                vertCopy.connections.add(conn.intValue());
            }
            vertCopies[i] = vertCopy;
        }
        return vertCopies;
    }

    public double HyperbolicDistance(Vertex v1, Vertex v2) {
        double dAngle = Math.PI - Math.abs(Math.PI - Math.abs(v1.angle - v2.angle));
        // approximation
        return v1.radius + v2.radius + (2 * Math.log(dAngle / 2) / this.zeta);
    }
}