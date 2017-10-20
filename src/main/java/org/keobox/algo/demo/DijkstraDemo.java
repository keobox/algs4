package org.keobox.algo.demo;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraDemo {

    private static EdgeWeightedDigraph loadFromEdgeWeightedData(String arg) {
        In in = new In(arg);
        EdgeWeightedDigraph G;
        try {
            int V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            G = new EdgeWeightedDigraph(V);
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                G.addEdge(new DirectedEdge(v, w, weight));
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
        return G;
    }

    /* Use algs4-data/largeEWD.txt from https://algs4.cs.princeton.edu/code/algs4-data.zip
    *  and 4 as vertex.
    *
    *  java Dijkstra algs4-data/largeEWD.txt 4
    *
    *  */

    public static void main(String[] args) {
        StdOut.println("Start load data");
        EdgeWeightedDigraph G = loadFromEdgeWeightedData(args[0]);
        int s = Integer.parseInt(args[1]);
        StdOut.println("Start compute shortest paths");
        long startTime = System.nanoTime();
        DijkstraSP sp = new DijkstraSP(G, s);
        StdOut.printf("Computing elapsed time: %d ms\n", (System.nanoTime() - startTime) / 1000000);
        StdOut.printf("Find farthest vertex from %d\n", s);
        int t1 = s;
        double dist = 0.0;
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                if (sp.distTo(t) > dist) {
                    dist = sp.distTo(t);
                    t1 = t;
                }
            }
        }
        StdOut.printf("Farthest vertex is %d dist %f\n", t1, dist);
    }
}
