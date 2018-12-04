import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchTree {

    public Node root;
    private Integer size;

    private class Node {

        private Node father;
        private char element;
        private String name, value;
        private List <Node> subtrees;
        private Integer level;

        Node(String n, String v){
            this.father = null;
            this.element = n.charAt(n.length()-1);
            this.name = n;
            this.value = v;
            this.subtrees = new ArrayList<Node>();
            this.level = null;
        }

        Node(char e){
            this.father = null;
            this.element = e;
            this.name = null;
            this.value = null;
            this.subtrees = new ArrayList<Node>();
            this.level = null;
        }

        public void addSubtree(Node n) {
            //System.out.println(n.toString());
            this.subtrees.add(n);
        }

        public void setLevel () {
            if (this == root) {
                this.level = 0;
            } else {
                Node aux = father;
                int val = 0;
                while (aux != null) {
                    aux = aux.father;
                    val++;
                }
                this.level = val;
            }

        }

        public Node findChild (char c) {
            if (subtrees == null) {
                return null;
            }
            for (int i = 0; i < subtrees.size(); i++) {
                if (subtrees.get(i).element == c) {
                    return subtrees.get(i);
                }
            }
            return null;
        }

    }

    public SearchTree () {
        this.root = new Node (' ');
        root.setLevel();
        this.size = 0;
    }

    /**
     * After creating a path for the node, the method will add a name and value to the node
     */
    public boolean add (String name, String meaning) {
        if (size == 0 || findFirstNode(name.charAt(0)) == null) {
            createPath(name, meaning, root);
            return true;
        }
        Node aux = findNode(name, root);
        aux.addSubtree(aux);
        return true;
    }

    /**
     * Returns the a root subtree node with the element asked. If there's none, it returns null.
     */
    public Node findFirstNode (char c) {
        return root.findChild(c);
    }

    /**
     * Search for a node with the asked string, if it doesn't, it gives back the closest path for the name
     */
    public Node findNode (String ref, Node target) {
        Node aux = target.findChild(ref.charAt(target.level));
        System.out.println(aux.name + " - " + "ref");
        if (aux == null || aux.name == null) {
            return aux.father;
        } else if ((aux.name).contains(ref)) {
            return aux;
        } else if (aux.subtrees != null){
            aux = findNode(ref, aux);
        }
        return aux;
    }

    /**
     * Creates a path with the name given and sets a final node
     */
    private void createPath (String nam, String mean,  Node starter) {
        Node aux;
        if (starter == root) {
            aux = starter;
        } else {
            aux = findNode(nam, starter);
        }
        for (int i = starter.level; i < nam.length(); i++) {
            if (i == nam.length()-1) {
                Node node = new Node(nam, mean);
                node.father = aux;
                aux.addSubtree(node);
                size++;
                break;
            } else {
                Node node = new Node (nam.charAt(i));
                node.father = aux;
                aux.addSubtree(node);
                aux = node;
                aux.setLevel();
                size++;
            }
        }
    }

    public Node searchWord(String name, Node target) {
        Node res = null;
        if (target != null) {
            if (name.equals(target.name)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < size)) {
                    aux = searchWord(name, target);
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }

    public int getSize () {
        return this.size;
    }
   /* @Override
    public String toString() {



        return "";
    }*/
}
