package rank.tree;

import rank.tree.model.Node;

public class InsertNode {

    static Node Insert(Node root,int value) {
        Node node = N(value);
        if(root == null) root = node;
        Insert(root, node);
        return root;
    }
    static void Insert(Node root, Node node) {
        if(node.data < root.data) {
            if(root.left == null) root.left = node;
            else Insert(root.left, node);
        }
        if(node.data > root.data) {
            if(root.right == null) root.right = node;
            else Insert(root.right, node);
        }
    }


    static Node N(int value) {
        Node n = new Node();
        n.data = value;
        return n;
    }
}
