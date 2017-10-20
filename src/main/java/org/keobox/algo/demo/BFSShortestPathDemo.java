package org.keobox.algo.demo;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BFSShortestPathDemo {

    public static Digraph loadFromEdgeWeightedData(String arg) {
        In in = new In(arg);
        Digraph G;
        try {
            int V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            G = new Digraph(V);
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                // consume weight but not use it.
                in.readDouble();
                G.addEdge(v, w);
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
        Digraph G = loadFromEdgeWeightedData(args[0]);
        int s = Integer.parseInt(args[1]);
        StdOut.println("Start compute shortest paths");
        long startTime = System.nanoTime();
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);
        StdOut.printf("Computing elapsed time: %d ms\n", (System.nanoTime() - startTime) / 1000000);
        StdOut.printf("Find farthest vertex from %d\n", s);
        int t1 = s;
        int dist = 0;
        for (int t = 0; t < G.V(); t++) {
            if (bfs.hasPathTo(t)) {
                if (bfs.distTo(t) > dist) {
                    dist = bfs.distTo(t);
                    t1 = t;
                }
            }
        }
        StdOut.printf("Farthest vertex is %d dist %d\n", t1, dist);
    }
}
