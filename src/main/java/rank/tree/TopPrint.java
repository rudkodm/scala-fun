package rank.tree;

import rank.tree.model.Node;

import static rank.tree.util.NodeUtil.*;

public class TopPrint {

    public static void main(String[] args) {
        TopPrint app =new TopPrint();
        app.top_view(root1);
    }

    void top_view(Node root) {
        printLeft(root.left);
        print(root);
        printRight(root.right);
    }

    void printLeft(Node node){
        if(node == null) return;
        if(node.left != null) printLeft(node.left);
        print(node);
    }

    void print(Node node) {
        if(node != null) System.out.print(node.data + " ");
    }

    private void printRight(Node node) {
        if(node == null) return;
        print(node);
        if(node.right != null) printRight(node.right);
    }

}
