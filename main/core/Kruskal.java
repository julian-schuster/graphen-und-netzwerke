package main.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;

public class Kruskal {
    /**
     * Performs Kruskal Algorithm.
     * 
     * @param g Graph to perform Kruskal onto.
     * @return List of Edges containing the lowest weights to reach every Node.
     */
    public static LinkedList<Edge> performKruskal(Graph g) {

        LinkedList<Edge> kruskalList = new LinkedList<>();

        HashMap<Integer, HashSet<Integer>> forest = new HashMap<>();

        for (Node v : g.NodeList) {
            // Add Tree
            forest.put(v.NodeIndex, makeSet(v));

        }

        // Sort List by weight ASC
        g.sortEdgeList();

        for (Edge e : g.EdgeList) {

            HashSet<Integer> tree1 = findSet(forest, e.a);
            HashSet<Integer> tree2 = findSet(forest, e.b);

            if (!findSet(forest, e.a).equals(findSet(forest, e.b))) {

                // Edge structure demands this
                if (tree1.size() >= tree2.size()) {
                    kruskalList.add(e);
                    union(forest, e.a, e.b);

                }

                else if (tree1.size() < tree2.size()) {

                    kruskalList.add(e);
                    union(forest, e.b, e.a);

                }

            }
        }

        return kruskalList;

    }

    /**
     * Finds tree in forest.
     * 
     * @param forest the tree.
     * @param n      Node which tree needs to contain.
     * @return tree containing node.
     */
    public static HashSet<Integer> findSet(HashMap<Integer, HashSet<Integer>> forest, Node n) {

        for (int representative : forest.keySet()) {

            if (forest.get(representative).contains(n.NodeIndex)) {

                return forest.get(representative);
            }
        }
        return null;
    }

    /**
     * Creates a tree containing the Nodeindex as values.
     * 
     * @param n Node to Start from
     * @return newly created Tree
     */
    public static HashSet<Integer> makeSet(Node n) {

        HashSet<Integer> nodeSet = new HashSet<Integer>(1);

        nodeSet.add(n.NodeIndex);

        return nodeSet;

    }

    /**
     * Unions two trees.
     * 
     * @param forest Forest containg all trees.
     * @param n1     Node1 which tree1 needs to have included
     * @param n2     Node2 which tree2 needs to have included
     */
    public static void union(HashMap<Integer, HashSet<Integer>> forest, Node n1, Node n2) {
        HashSet<Integer> tree1 = findSet(forest, n1);
        HashSet<Integer> tree2 = findSet(forest, n2);

        tree1.addAll(tree2);

        forest.remove(n2.NodeIndex);

    }

}
