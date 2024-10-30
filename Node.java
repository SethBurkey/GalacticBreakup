package galacticbreakup;


public class Node {
    Node parent;
    int rank;

    /**
     * makeSet / constructor
     * set parent to itself and set rank to 0
     */
    Node(){
        this.parent = this;
        this.rank = 0;
    }

    /**
     * @return (representative) Node
     * this method also sets the parent pointer
     * of each node between this and the
     * representative to the representative
     */
    public Node findSet(){
        if(this != this.parent){
            this.rank = 0;
            this.parent = this.parent.findSet();
        }
        return this.parent;
    }

    /**
     * @param n
     * unconditional union by rank
     */
    public void link(Node n){
        if(this.rank > n.rank){
            n.parent = this;
        }
        else{
            this.parent = n;
            if(this.rank == n.rank){
                this.rank++;
            }
        }
    }

    /**
     * @param n
     * union of the representatives by rank
     */
    public void union(Node n){
        this.findSet().link(n.findSet());
    }

    /**
     * @param n
     * @return boolean
     * true if different component
     * false if same component
     */
    public boolean differentComponent(Node n){
        return this.findSet() != n.findSet();
    }

    /**
     * @param n
     * @return (numOfUnions) int
     * for each adjacency, union by rank
     * if adjacency is in a different component
     * and return the number of unions by rank.
     */
    public int connectAdjacencies(Node[] n){
        int numOfUnions = 0;
        for(Node node : n){
            if(node != null){
                if(this.differentComponent(node)){
                    this.union(node);
                    ++numOfUnions;
                }
            }
        }
        return numOfUnions;
    }
}