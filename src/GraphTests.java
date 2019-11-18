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


        Graph h = TestGraph.MakeBigGraph();

        Vertex endBigGraph = h.getvertex("6");
        Vertex sourceBigGraph = h.getvertex("10");

        Pair<Integer, Map<Vertex, Vertex>> resultsD = g.ShortestDistance(source, end);
        Pair<Integer, Map<Vertex, Vertex>> resultsT = g.ShortestTime(source, end);
        Pair<Integer, Map<Vertex, Vertex>> resultsDBigGraph = h.ShortestDistance(sourceBigGraph,endBigGraph);

        Vertex currentD = end;
        Vertex currentT = end;
        Vertex currentDBigGraph = endBigGraph;

        ArrayList<Vertex> PathD = new ArrayList<>();
        PathD.add(end);

        ArrayList<Vertex> PathT = new ArrayList<>();
        PathT.add(end);

        ArrayList<Vertex> PathDBigGraph = new ArrayList<>();
        PathDBigGraph.add(endBigGraph);



        System.out.println("This is the shortest path measured in distance: ");
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
        System.out.println("This is the shortest path measured in time: ");



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


        System.out.println("");
        System.out.println("");
        System.out.println("This is the shortest path for the big graph measured in distance: ");

        while((currentDBigGraph != sourceBigGraph) && (resultsDBigGraph.getValue().get(currentDBigGraph) != null)){

            currentDBigGraph = resultsDBigGraph.getValue().get(currentDBigGraph);
            PathDBigGraph.add(0, currentDBigGraph);

        }

        for (Vertex vBig : PathDBigGraph) {
            System.out.print(vBig.Name);

            {
            if (vBig != endBigGraph)
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
    public Graph MakeBigGraph() {

        Graph bigGraph = new Graph();

        final Vertex v1 = bigGraph.addvertex("1");
        final Vertex v2 = bigGraph.addvertex("2");
        final Vertex v3 = bigGraph.addvertex("3");
        final Vertex v4 = bigGraph.addvertex("4");
        final Vertex v5 = bigGraph.addvertex("5");
        final Vertex v6 = bigGraph.addvertex("6");
        final Vertex v7 = bigGraph.addvertex("7");
        final Vertex v8 = bigGraph.addvertex("8");
        final Vertex v9 = bigGraph.addvertex("9");
        final Vertex v10 = bigGraph.addvertex("10");

        bigGraph.newedge(v1,v5,20,0);
        bigGraph.newedge(v1,v7,15,0);
        bigGraph.newedge(v1,v6,5,0);
        bigGraph.newedge(v1,v2,10,0);
        bigGraph.newedge(v1,v4,20,0);
        bigGraph.newedge(v2,v3,5,0);
        bigGraph.newedge(v2,v4,10,0);
        bigGraph.newedge(v3,v4,5,0);
        bigGraph.newedge(v3,v2,15,0);
        bigGraph.newedge(v4,v5,10,0);
        bigGraph.newedge(v5,v6,5,0);
        bigGraph.newedge(v7,v6,10,0);
        bigGraph.newedge(v8,v7,5,0);
        bigGraph.newedge(v8,v1,5,0);
        bigGraph.newedge(v8,v2,20,0);
        bigGraph.newedge(v9,v8,20,0);
        bigGraph.newedge(v9,v2,15,0);
        bigGraph.newedge(v9,v10,10,0);
        bigGraph.newedge(v10,v2,5,0);
        bigGraph.newedge(v10,v3,15, 0);


        return bigGraph;

    }


}
