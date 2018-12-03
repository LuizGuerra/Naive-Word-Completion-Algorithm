import java.util.LinkedList;
import java.util.List;

public class SearchTree {

    private Node root;
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
            this.subtrees = null;
            this.level = null;
        }

        Node(char e){
            this.father = null;
            this.element = e;
            this.name = null;
            this.value = null;
            this.subtrees = null;
            this.level = null;
        }

        public void addSubtree(Node n) {
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

    // implementar: caso não encontre o nodo, criar um nodo e continuar o percurso
    public Node findFirstNode (char c) {
        for (int i = 0; i < root.subtrees.size(); i++) {
            if (root.subtrees.get(i).name.charAt(i) == c) {
                return root.subtrees.get(i);
            }
        }
        return null;
    }

    /**
     * Search for a node with the asked string, if it doesn't, it gives back the closest path for the name
     */
    public Node findNode (String ref, Node target) {
        Node aux = target.findChild(ref.charAt(target.level));
        if (aux == null) {
            return aux.father;
        } else if (aux.name.equals(ref)) {
            return aux;
        } else if (aux.subtrees != null){
            aux = findNode(ref, aux);
        }
        return aux;
    }

    // implementar: criar o caminho restante a partir do nodo entregue
    public void createPath (String nam, String mean,  Node starter) {
        Node aux = starter;
        if (starter == root) {
            for (int i = 0; i < nam.length(); i++) {
                if (i == nam.length()-1) {
                    Node node = new Node(nam, mean);
                    aux.addSubtree(node);
                    break;
                } else {
                    Node node = new Node (nam.charAt(i));
                    aux.addSubtree(node);
                    aux = node;
                    aux.setLevel();
                }
            }
        } else {
            Node n = findNode(nam, starter);
        }
    }

}
