package rank.tree;

import rank.tree.model.Node;

import static rank.tree.util.NodeUtil.*;

public class PreorderPrint {

    public static void main(String[] args) {
        PreorderPrint app =new PreorderPrint();
        app.Preorder(root1);
    }

    void Preorder(Node root) {
        if(root == null) return;
        print(root);
        if(root.left != null) Preorder(root.left);
        if(root.right != null) Preorder(root.right);
    }


    void print(Node node) {
        if(node != null) System.out.print(node.data + " ");
    }
}
