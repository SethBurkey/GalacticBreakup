package galacticbreakup;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Given the dimensions of a galaxy and the group of dominions that secede each month
 * return the number of total months that the loyal dominions in the galaxy are disconnected.
 * Expected input n, m, k (galaxy dimentions), and l (the number of months) in the first line.
 * Each number between 0 to nmk should be found in one of the following l lines exactly once.
 * ie ""
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int l = in.nextInt();
            in.nextLine();
            Node[][][] galaxy = new Node[k][m][n];  //initialize the 3d array of Nodes and
            int sets = 0; //keeping track of connectedness
            int monthsDisconnected = 0;
            ArrayList<int[]> monarchy = new ArrayList<>(); //Temp arraylist to store the monarchies from the input
            for(int i = 0; i < l; ++i){
                String line = in.nextLine(); //get monarchy
                String[] tokens = line.split("\\s+"); //split the monarchy into dominions
                int[] dominions = new int[tokens.length-1];
                for (int x = 0; x < tokens.length - 1; x++) { //string array to integer array
                    dominions[x] = Integer.parseInt(tokens[x+1]);
                }
                monarchy.add(dominions); //add the monarchy to the array of monarchies.
            }
            for(int i = monarchy.size()-1; i >= 0; --i){
                for(int dominion : monarchy.get(i)){
                    int h = (int) Math.floor((double) dominion/(n*m)); //dominion value to coordinates
                    int d = (int) Math.floor((double) (dominion%(n*m))/n);
                    int w = dominion%n;
                    Node[] adjacencies = new Node[6]; //initialize the array of adjacent nodes
                    galaxy[h][d][w] = new Node(); //makeSet
                    ++sets; //increment numOfSets
                    if(h < (k - 1)){ //fill adjacencies array if the adjacency exists
                        adjacencies[0] = galaxy[h+1][d][w];
                    }
                    if(h > 0){
                        adjacencies[1] = galaxy[h-1][d][w];
                    }
                    if(d < (m - 1)){
                        adjacencies[2] = galaxy[h][d+1][w];
                    }
                    if(d > 0){
                        adjacencies[3] = galaxy[h][d-1][w];
                    }
                    if(w < (n - 1)){
                        adjacencies[4] = galaxy[h][d][w+1];
                    }
                    if(w > 0){
                        adjacencies[5] = galaxy[h][d][w-1];
                    }
                    int numOfUnions = galaxy[h][d][w].connectAdjacencies(adjacencies); //union adjacencies
                    sets -= numOfUnions; //subtract the number of unions from the number of sets
                }
                if(sets > 1){ //Adjudicate
                    ++monthsDisconnected;
                }
            }
            System.out.println(monthsDisconnected);
        }
    }
}