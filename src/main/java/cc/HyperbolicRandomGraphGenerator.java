/*
  Jordan Williams
*/
package cc;

import java.lang.Math;
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.util.*;
import org.jgrapht.generate.*;

public final class HyperbolicRandomGraphGenerator
    //implements
    //GraphGenerator<V, E, V>
{
    //private static final boolean DEFAULT_ALLOW_LOOPS = false;
    //private static final boolean DEFAULT_ALLOW_MULTIPLE_EDGES = false;

    private final Random rng;
    private final int n;
    private final double k;
    //private final boolean loops;
    //private final boolean multipleEdges;

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     */
    public HyperbolicRandomGraphGenerator(int n, double k)
    {
        this(n, k, new Random());
    }

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param seed seed for the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, long seed)
    {
        this(n, k, new Random(seed));
    }

    /**
     * Create a new $G(n, M)$ random graph generator
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param rng the random number generator
     */
    public HyperbolicRandomGraphGenerator(int n, double k, Random rng)
    {
        if (n < 0) {
            throw new IllegalArgumentException("number of vertices must be non-negative");
        }
        this.n = n;
        if (k < 0) {
            throw new IllegalArgumentException("average vertex degree must be non-negative");
        }
        this.k = k;
        this.rng = Objects.requireNonNull(rng);
    }

    @Override
    public void 
}