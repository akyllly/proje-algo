public class Class {
}package Graphs;
        import java.util.Arrays;
        import java.util.Scanner;
public class kruskals {

    public static class edge implements Comparable<edge> {
        int u;
        int v;
        int weight;
        public edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public String toString() {
            return this.u + " " + this.v + " " + this.weight;
        }

        @Override
        public int compareTo(edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int nodes = scn.nextInt();
        int[][] graph = new int[nodes + 1][nodes + 1];
        int numEdges = scn.nextInt();
        edge[] edges = new edge[numEdges];
        for (int edge = 0; edge < numEdges; edge++) {
            int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();

            graph[u][v] = graph[v][u] = w;
            edges[edge] = new edge(u, v, w);
        }

        kruskalsAlgo(nodes, numEdges, edges, graph);

    }

    public static void kruskalsAlgo(int numVertices, int numEdges, edge[] edges, int[][] graph) {


        int[][] mst = new int[graph.length][graph.length];

        Arrays.sort(edges);


        int[] parents = new int[numVertices + 1];
        int[] size = new int[numVertices + 1];
        for (int vertex = 1; vertex < graph.length; vertex++) {

            parents[vertex] = vertex;
            size[vertex] = 1;
        }

        int edgeCounter = 0;
        int edgedTaken = 1;

        while (edgedTaken <= numVertices - 1) {
            edge e = edges[edgeCounter];
            edgeCounter++;

            if (isCyclic(e.u, e.v, parents))
                continue;

            union(findParent(e.u, parents), findParent(e.v, parents), parents, size);
            mst[e.u][e.v] = e.weight;
            edgedTaken++;
        }


        for (int u = 1; u < mst.length; u++) {
            for (int v = 0; v < mst.length; v++) {
                if (mst[u][v] != 0) {
                    System.out.println(u + " " + v + " " + mst[u][v]);
                }
            }
        }

    }

    public static boolean isCyclic(int u, int v, int[] parents) {

        return findParent(u, parents) == findParent(v, parents);
    }

    public static void union(int u, int v, int[] parents, int[] size) {

        u = findParent(u, parents);
        v = findParent(v, parents);
        if (size[u] > size[v]) {
            parents[v] = u;
            size[u] += size[v];
        } else {
            parents[u] = v;
            size[v] += size[u];
        }
    }

    public static int findParent(int u, int[] parents) {

        if (parents[u] == u) {
            return u;
        } else {

            parents[u] = findParent(parents[u], parents);
            return parents[u];
        }
    }

}