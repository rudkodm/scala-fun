package rank.tree;

import rank.tree.model.Node;

public class InorderPrint {

    void Inorder(Node root) {
        if(root == null) return;
        if(root.left != null) Inorder(root.left);
        print(root);
        if(root.right != null) Inorder(root.right);
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
