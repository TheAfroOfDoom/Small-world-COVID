package cc;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.*;

import java.util.*;
import java.util.function.*;
import java.util.ArrayList;

public class GraphGenerator {

    public static int[][] generate(int size) {
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

        return convertEdgeSet(completeGraph.edgeSet());
    }

    public static int[][] convertEdgeSet(Set<DefaultEdge> edges) {
        ArrayList<int[]> edgeList = new ArrayList<>();
        for (DefaultEdge edge : edges) {
            int[] vNums = new int[2];
            String edgeStr = edge.toString();
            edgeStr = edgeStr.replace("(", "");
            edgeStr = edgeStr.replace(")", "");
            String[] vStrings = edgeStr.split(" : ");
            vNums[0] = Integer.valueOf(vStrings[0]);
            vNums[1] = Integer.valueOf(vStrings[1]);
            edgeList.add(vNums);
        }
        return edgeList.toArray(new int[edgeList.size()][]);
    }
}
