package rank.tree;

import rank.tree.model.Node;

public class PostorderPrint {

    void Postorder(Node root) {
        if(root == null) return;
        if(root.left != null) Postorder(root.left);
        if(root.right != null) Postorder(root.right);
        print(root);
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
