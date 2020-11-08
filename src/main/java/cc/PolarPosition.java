/*
  Jordan Williams
*/
package cc;

import java.lang.Math;
import java.util.*;

public final class PolarPosition
{
    //private final Random rng;
    private double radius;
    private double angle;
    
    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     */
    public PolarPosition(double maxRadius)
    {
        this(maxRadius, new Random());
    }

    /**
     * Create a new $G(n, M)$ random graph generator. The generator does not create self-loops or
     * multiple (parallel) edges between the same two vertices.
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param seed seed for the random number generator
     */
    public PolarPosition(double maxRadius, long seed)
    {
        this(maxRadius, new Random(seed));
    }

    /**
     * Create a new $G(n, M)$ random graph generator
     * 
     * @param n the number of vertices
     * @param k the average vertex degree
     * @param rng the random number generator
     */
    public PolarPosition(double maxRadius, Random rng)
    {
        rng = Objects.requireNonNull(rng);
        double maxAngle = 2 * Math.PI;

        this.radius  = maxRadius * rng.nextDouble();
        this.angle   = maxAngle * rng.nextDouble();
    }

    public double getRadius()
    {
        return this.radius;
    }

    public double getAngle()
    {
        return this.angle;
    }
}