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
            this.subtrees.add(n);
        }

        public void setLevel () {
            Node aux = father;
            int val = 0;
            while (aux != null) {
                aux = aux.father;
                val++;
            }
            this.level = val;
        }

        public Node findChild (char c) {
            if (subtrees.isEmpty()) {
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
        this.size = 0;
        root.level = 0;
    }

    /**
     * After creating a path for the node, the method will add a name and value to the node
     */
    public boolean add (String name, String meaning) {
        name = name.toLowerCase(); // hey you
        if (size == 0 || findFirstNode(name.charAt(0)) == null) {
            createPath(name, meaning, root);
            return true;
        }
        Node aux = findNode(name, root);
        if (aux == null) {
            return false;
        }
        createPath(name, meaning, aux);
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
        if (aux == null) {
            return aux.father;
        }
        for (int i = 1; i < ref.length(); i++) {
            if (aux.findChild(ref.charAt(i)) == null) {
                return aux;
            }
            aux = aux.findChild(ref.charAt(i));
        }
        return aux;
    }

    /**
     * Creates a path with the name given and sets a final node
     */
    private void createPath (String nam, String mean,  Node starter) {
        Node aux = starter;
        aux.setLevel();
        for (int i = aux.level; i < nam.length(); i++) {
            if (i == nam.length()-1) {
                Node node = new Node(nam, mean);
                node.father = aux;
                aux.addSubtree(node);
                aux.setLevel();
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

    public int findSubtreeSize (Node n) {
        int val = 0;
        if (n != null) {
            val++;
            for (Node i : n.subtrees) {
                val += findSubtreeSize(i);
            }
        }
        return val;
    }

    private List<List<String>> lista = new ArrayList<>();
    public List<List<String>> findSubtreeWords (Node n) {
        if (n != null) {
            if (n.name != null) {
                List<String> ls = new ArrayList<>();
                ls.add(n.name);
                ls.add(n.value);
                lista.add(ls);
            }
            for (Node i : n.subtrees) {
                findSubtreeWords(i);
            }
        } else {
            return null;
        }
        return this.lista;
    }

    public void cleanList() {
        lista.clear();
    }

    public List<List<String>> searchName (String word) {
        Node aux = findNode(word, root);
        if (aux == null) {
            return null;
        }
        // int val = findSubtreeSize(aux);
        return findSubtreeWords(aux);
    }

    public int getSize () {
        return this.size;
    }
}
