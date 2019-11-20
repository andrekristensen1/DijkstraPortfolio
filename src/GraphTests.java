import javafx.util.Pair;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {

        GraphTests TestGraph = new GraphTests();                                                                        //Laver et objekt af klassen GraphTests

        Graph g = TestGraph.MakeSmallGraph();                                                                           //Laver en graf ud fra vores MakeSmallGraph metode med navnet g
        Vertex source = g.getvertex("A");                                                                            //Giver g en source vertex
        Vertex end = g.getvertex("F");                                                                               //Giver g en end vertex


        Graph h = TestGraph.MakeBigGraph();                                                                             //Laver en graf ud fra vores MakeBigGraph metode med navnet h
        Vertex endBigGraph = h.getvertex("6");                                                                       //giver h en source vertex
        Vertex sourceBigGraph = h.getvertex("10");                                                                   //giver h en end vertex

        Pair<Integer, Map<Vertex, Vertex>> resultsD = g.ShortestDistance(source, end);                                  //Laver et pair kaldet resultsD som er lig med shortestDistance i g
        Pair<Integer, Map<Vertex, Vertex>> resultsT = g.ShortestTime(source, end);                                      //Laver et pair kaldet resultsT som er lig med shortestTime i g
        Pair<Integer, Map<Vertex, Vertex>> resultsDBigGraph = h.ShortestDistance(sourceBigGraph,endBigGraph);           //Laver et pair kaldet resultsDBigGraph som er lig med  shortestDistance i h

        Vertex currentD = end;                                                                                          //Laver en vertex som skal være slutningen på vores shortestpath for distancen
        Vertex currentT = end;                                                                                          //Laver en vertex som skal være slutningen på vores shortestpath for tid
        Vertex currentDBigGraph = endBigGraph;                                                                          //Laver en vertex som skal være slutningen på vores shortestpath for bigGraph med distance

        ArrayList<Vertex> PathD = new ArrayList<>();                                                                    //Laver en arraylist som skal indeholde vores shortestpath for distance
        PathD.add(end);                                                                                                 //Tilføjer den valgte slut vertex

        ArrayList<Vertex> PathT = new ArrayList<>();                                                                    //Laver en arraylist som skal indeholde vores shortestpath for tid
        PathT.add(end);                                                                                                 //Tilføjer den valgte slut vertex

        ArrayList<Vertex> PathDBigGraph = new ArrayList<>();                                                            //Laver en arraylist som skal indeholde vores shortestpath for distance i h
        PathDBigGraph.add(endBigGraph);                                                                                 //Tilføjer den valgte slut vertex



        System.out.println("This is the shortest path measured in distance: ");
        while ((currentD != source) && (resultsD.getValue().get(currentD) != null)) {                                   //Så længe vores currentD ikke er lig med source vertex og at vores shortestpath ikke er nul.

            currentD = resultsD.getValue().get(currentD);                                                               //Sætter vores currentD til at være lig med dem nuværende værdi fra fra vores shortestpath

            PathD.add(0, currentD);                                                                              // Tilføjer denne værdi til vores arraylist

        }
        for (Vertex v : PathD) {                                                                                        //printer vores shortest path i konsollen
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

    /**
     * En metode der kreerer en graf ud fra givne edges og vertices
     *
     * @return smallGraph
     */

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

    /**
     * En metode der kreerer en graf ud fra givne edges og vertices
     *
     * @return bigGraph
     */
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
