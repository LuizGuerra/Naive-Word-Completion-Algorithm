import java.net.Inet4Address;
import java.util.LinkedList;

public class SearchTree {

    private class Node {

        private Node father;
        private Integer element;
        private LinkedList subtrees;

        Node(Integer e){
            this.father = null;
            this.element = e;
            this.subtrees = null;
        }

        void addSubtree(Node n) {}
        boolean removeSubtree(Node n) {return false;}
        Node getSubtree(int i){return null;}
        int getSubtreesSize(){return 0;}

    }

    
}
