package rank.tree;

import rank.tree.model.Node;

public class LevelOrderPrint {

    java.util.LinkedList<java.util.LinkedList<Node>> layers = new java.util.LinkedList();

    void LevelOrder(Node root) {
        if(root == null) return;
        traverce(root, 0);
        for (java.util.LinkedList<Node> nodes : layers) {
            for (Node node : nodes) print(node);
        }
    }

    void traverce(Node node, int level){
        if(node == null) return;
        push(node, level);
        traverce(node.right, level + 1);
        traverce(node.left, level + 1);
    }

    private void push(Node node, int level) {
        if(layers.size() <= level){
            layers.add(level, new java.util.LinkedList<Node>());
        }
        layers.get(level).push(node);
    }


    public interface Console {
        void print(String str);
    }

    public Console console = new Console() {
        public void print(String str) {System.out.print(str);}
    };

    void print(Node node) {
        if(node != null) console.print(node.data + " ");
    }
}
