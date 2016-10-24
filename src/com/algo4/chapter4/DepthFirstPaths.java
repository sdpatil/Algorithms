package com.algo4.chapter4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * Created by spatil30 on 10/24/2016.
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                edgeTo[w] =v;
                dfs(G,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x= v; x!=s ; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths search = new DepthFirstPaths(G,s);

        for(int v = 0; v < G.V(); v++){
            StdOut.print(v +" -> ");
            if(search.hasPathTo(v)){
                for(int x : search.pathTo(v))
                    if(x == s )
                        StdOut.print("_"+x);
                    else
                        StdOut.print("_" + x);
                StdOut.println();
            }
        }
    }
}