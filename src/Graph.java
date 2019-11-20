import java.util.*;
import java.util.Map.Entry;

import javafx.util.Pair;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    /**
     * Metode der kreerer en vertex med et givent id og tilføjer den til vores arrayliste med vertices
     * @param id navn på vores vertex
     * @return ny vertex
     */
    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

 /*public void addvertex(Vertex v) {

    Vertices.add(v);
    }*/

    /**
     * Metode der søger efter en vertex ud fra en string
     * @param s
     * @return vertex v hvis den findes, ellers returneres 0
     */

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    /**
     * Kreerer en ny edge melllem to vertices med de givne parametre
     * @param from vertex
     * @param to   vertex
     * @param dist distance
     * @param time tid
     */

    public void newedge(Vertex from, Vertex to, int dist, int time) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = time;
    }


    /**
     * Funktion der finder shortest path mellem to vertices
     * @param source vores start vertex
     * @param end    vores slut vertex
     * @return et pair med vores shortestpath for distance
     */
    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex end) {

        Map<Vertex, Integer> T = new HashMap<>();
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();

        PredecessorMap.put(source, null);


        for (Vertex v : Vertices) {


            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
            T.put(v, 1000);
        }

        DistanceMap.put(source, 0);                                                                                                                         // Edges
        T.put(source, 0);                                                                                                                                   // Verticies


        for (int i = 0; i < Vertices.size(); i++) {


            Vertex current = getmin(T);


            for (int j = 0; j < current.getOutEdges().size(); j++) {


                if (DistanceMap.get(current.getOutEdges().get(j).getTovertex()) > DistanceMap.get(current) + current.getOutEdges().get(j).distance) {       //Hvis current vertex + current outedge distance er mindre end næste vertex værdi
                    DistanceMap.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);          // Sæt vores næste vertex til at være lig med current vertex + current OutEdge i DistanceMap
                    T.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);                    // Sæt vores næste vertex til at være lig med current vertex + current OutEdge i T
                    PredecessorMap.put(current.getOutEdges().get(j).getTovertex(), current);                                                                // Opdater vores current til at være lig med vores "tidligere" næste vertex værdi

                }

            }


            T.remove(current);                                                                                                                              //Når vi fjerner den behøves vi ikke at se om den er håndteret
        }


        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(end), PredecessorMap));                                                              //Key = current vertex, Value = predecessor vertex
    }

    /**
     * Fungerer på samme måde som ovenstående metode med tid i stedet for distance. Se ovenstående kommentarer for yderligere info.
     * @param source vertex
     * @param end    vertex
     * @return et pair med vores shortesttime for tid
     */
    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex end) {

        Map<Vertex, Integer> T = new HashMap<>();
        Map<Vertex, Vertex> PredecessorMaptime = new HashMap<>();
        Map<Vertex, Integer> TimeMap = new HashMap<>();

        PredecessorMaptime.put(source, null);


        for (Vertex v : Vertices) {


            TimeMap.put(v, 1000);
            PredecessorMaptime.put(v, null);
            T.put(v, 1000);
        }

        TimeMap.put(source, 0);
        T.put(source, 0);


        for (int i = 0; i < Vertices.size(); i++) {

            Vertex currentT = getmin(T);

            for (int j = 0; j < currentT.getOutEdges().size(); j++) {


                if (TimeMap.get(currentT.getOutEdges().get(j).getTovertex()) > TimeMap.get(currentT) + currentT.getOutEdges().get(j).time) {
                    TimeMap.put(currentT.getOutEdges().get(j).getTovertex(), TimeMap.get(currentT) + currentT.getOutEdges().get(j).time);
                    T.put(currentT.getOutEdges().get(j).getTovertex(), TimeMap.get(currentT) + currentT.getOutEdges().get(j).time);
                    PredecessorMaptime.put(currentT.getOutEdges().get(j).getTovertex(), currentT);

                }

            }

            T.remove(currentT);
        }

        return (new Pair<Integer, Map<Vertex, Vertex>>(TimeMap.get(end), PredecessorMaptime));
    }


    /**
     * finder den mindste værdi mellem to vertices
     * @param qmap
     * @return den Vertex hvor vægten er mindst fra den nuværende vertex
     */
    public Vertex getmin(Map<Vertex, Integer> qmap) {

        Entry<Vertex, Integer> min = null;

        for (Entry<Vertex, Integer> entry : qmap.entrySet()) {                                                          //løber gennem forløkke så mange gange som der er elementer i vores map T

            if (min == null || min.getValue() > entry.getValue()) {                                                     //Hvis min er større end den værdi vi kigger på:

                min = entry;                                                                                            //Sæt min lig med entry

            }

        }

        return min.getKey();

    }

}


class Vertex {
    public String Name;
    public ArrayList<Edge> OutEdges = new ArrayList<>();

    public Vertex(String id) {

        Name = id;
    }

    public void addOutEdge(Edge outedge) {

        OutEdges.add(outedge);
    }

    public ArrayList<Edge> getOutEdges() {

        return OutEdges;

    }
}


class Edge {
    private Vertex fromvertex;
    private Vertex tovertex;

    //This is the cost
    public int distance = 0;
    public int time = 0;

    public Vertex getTovertex() {
        return tovertex;


    }

    public Edge(Vertex from, Vertex to) {
        fromvertex = from;
        tovertex = to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}