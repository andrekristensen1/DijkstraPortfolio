import java.util.*;
import java.util.Map.Entry;

import javafx.util.Pair;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

public void addvertex(Vertex v) {

    Vertices.add(v);
    }

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int time) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = time;
    }

    //Source er start, end er slut
    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex end) {

        Map<Vertex, Integer> T = new HashMap<>();
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();

        PredecessorMap.put(source, null);


        // initialize array O(n)
        for (Vertex v : Vertices) {

        //log v (map) tid hvis vi brugte tree map
            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
            T.put(v, 1000);
        }

        DistanceMap.put(source, 0);     // Edges
        T.put(source, 0);               // Verticies

        // O(n^2)
        for (int i = 0; i < Vertices.size(); i++) {


            Vertex current = getmin(T);


            for (int j = 0; j < current.getOutEdges().size(); j++) {


                //Hvis current vertex + current outedge distance er mindre end næste vertex værdi
                if (DistanceMap.get(current.getOutEdges().get(j).getTovertex()) > DistanceMap.get(current) + current.getOutEdges().get(j).distance) {

                    // Sæt vores næste vertex til at være lig med current vertex + current OutEdge i DistanceMap
                    DistanceMap.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);

                    // Sæt vores næste vertex til at være lig med current vertex + current OutEdge i T
                    T.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);

                    // Opdater vores current til at være lig med vores "tidligere" næste vertex værdi
                    PredecessorMap.put(current.getOutEdges().get(j).getTovertex(), current);

                }

            }

            //Når vi fjerner den behøves vi ikke at se om den er håndteret
            T.remove(current);
        }

        //Key = current vertex, Value = predecessor vertex
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(end), PredecessorMap));
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex end) {

        Map<Vertex, Integer> T = new HashMap<>();
        Map<Vertex, Vertex> PredecessorMaptime = new HashMap<>();
        Map<Vertex, Integer> TimeMap = new HashMap<>();

        PredecessorMaptime.put(source, null);


        // initialize array O(n)
        for (Vertex v : Vertices) {

            //log v (map) tid hvis vi brugte tree map
            TimeMap.put(v, 1000);
            PredecessorMaptime.put(v, null);
            T.put(v, 1000);
        }

        TimeMap.put(source, 0);         // Edges
        T.put(source, 0);               // Verticies


        // O(n^2)
        for (int i = 0; i < Vertices.size(); i++) {

            Vertex currentT = getmin(T);

            for (int j = 0; j < currentT.getOutEdges().size(); j++) {


                //Hvis current vertex + current outedge distance er mindre end næste vertex værdi
                if (TimeMap.get(currentT.getOutEdges().get(j).getTovertex()) > TimeMap.get(currentT) + currentT.getOutEdges().get(j).time) {

                    TimeMap.put(currentT.getOutEdges().get(j).getTovertex(), TimeMap.get(currentT) + currentT.getOutEdges().get(j).time);
                    T.put(currentT.getOutEdges().get(j).getTovertex(), TimeMap.get(currentT) + currentT.getOutEdges().get(j).time);
                    PredecessorMaptime.put(currentT.getOutEdges().get(j).getTovertex(), currentT);

                }

            }
            //Når vi fjerner den behøves vi ikke at se om den er håndteret
            T.remove(currentT);
        }


        //Key = current vertex, Value = predecessor vertex
        return (new Pair<Integer, Map<Vertex, Vertex>>(TimeMap.get(end), PredecessorMaptime));
    }


    public Vertex getmin(Map<Vertex, Integer> qmap) {
        // Your code - kør igennem alle outedges

        Entry<Vertex, Integer> min = null;

        for (Entry<Vertex, Integer> entry : qmap.entrySet()) {

            if (min == null || min.getValue() > entry.getValue()) {

                min = entry;

            }

        }

        return min.getKey();

    }

}

//Can't touch this
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

//Can't touch this
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