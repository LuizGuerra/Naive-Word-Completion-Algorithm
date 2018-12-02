import java.util.LinkedList;
import java.util.List;

public class SearchTree {

    private Node root;
    private Integer size;

    private class Node {

        private Node father;
        private String name, value;
        private List <Node> subtrees;
        private Integer level;

        Node(String n, String v){
            this.father = null;
            this.name = n;
            this.value = v;
            this.subtrees = null;
            this.level = null;
        }

        public void addSubtree(Node n) {
            this.subtrees.add(n);
        }
    }

    public SearchTree () {
        this.root = null;
        this.size = 0;
    }

    // implementar: após encontrar o nodo, adicionar o nome.
    public boolean add (String name, String meaning) {
        if (size == 0) {
            createPath(name, root);
        }
        Node aux = findNode(name, root);
        aux.addSubtree(aux);
        return false;
    }

    // implementar: caso n encontre o nodo, criar um nodo e continuar o percurso
    public Node findFirstNode (char c) {
        for (int i = 0; i < root.subtrees.size(); i++) {
            if (root.subtrees.get(i).name.charAt(i) == c) {
                return root.subtrees.get(i);
            }
        }
        return null;
    }

    // implementar: se não encontrar o nodo, criar um novo path que leve ao nodo.
    public Node findNode (String ref, Node target) {
        Node aux = null;
        if(target.name.charAt(target.level) == ref.charAt(target.level)) {

        }

        return null;
    }

    // implementar: criar o caminho restante a partir do nodo entregue
    public void createPath (String name, Node starter) {
        
    }

}
