import javafx.util.Pair;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {
        // Create graph
        GraphTests TestGraph = new GraphTests();

        Graph g = TestGraph.MakeSmallGraph();

        Vertex source = g.getvertex("A");

        Vertex end = g.getvertex("F");

        Pair<Integer, Map<Vertex, Vertex>> resultsD = g.ShortestDistance(source, end);
        Pair<Integer, Map<Vertex, Vertex>> resultsT = g.ShortestTime(source, end);

        Vertex currentD = end;
        Vertex currentT = end;

        ArrayList<Vertex> PathD = new ArrayList<>();
        PathD.add(end);

        ArrayList<Vertex> PathT = new ArrayList<>();
        PathT.add(end);

        System.out.println("This is the shortest path for distance: ");
        while ((currentD != source) && (resultsD.getValue().get(currentD) != null)) {

            currentD = resultsD.getValue().get(currentD);

            PathD.add(0, currentD);

        }
        for (Vertex v : PathD) {
            System.out.print(v.Name);

            {
                if (v != end)
                    System.out.print(" -> ");
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("This is the shortest path for time: ");

        while ((currentT != source) && (resultsT.getValue().get(currentT) != null)) {

            currentT = resultsT.getValue().get(currentT);

            PathT.add(0, currentT);
        }

            for (Vertex vT : PathT) {
                System.out.print(vT.Name);
                {
                if (vT != end)
                    System.out.print(" -> ");

            }

        }

    }

    public Graph MakeSmallGraph() {
        Graph mygraph = new Graph();
        final Vertex A = mygraph.addvertex("A");
        final Vertex B = mygraph.addvertex("B");
        final Vertex C = mygraph.addvertex("C");
        final Vertex D = mygraph.addvertex("D");
        final Vertex E = mygraph.addvertex("E");
        final Vertex F = mygraph.addvertex("F");

        mygraph.newedge(A, B, 1, 2);
        mygraph.newedge(A, C, 5, 1);
        mygraph.newedge(A, D, 4, 6);
        mygraph.newedge(B, C, 3, 2);
        mygraph.newedge(B, D, 2, 3);
        mygraph.newedge(B, E, 2, 4);
        mygraph.newedge(C, F, 1, 8);
        mygraph.newedge(C, E, 2, 2);
        mygraph.newedge(D, F, 2, 7);
        mygraph.newedge(E, F, 3, 6);


        return mygraph;

    }
}
