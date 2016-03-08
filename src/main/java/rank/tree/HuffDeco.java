package rank.tree;

import rank.tree.model.huff.Node;


public class HuffDeco {

    Node topNode = null;

    void decode(String S ,Node root) {
        if(topNode == null) topNode = root;

        if(root.left == null && root.right == null){
            print(root);
            decode(S, topNode);
        } else if( S.length() != 0) {
            String head = String.valueOf(S.charAt(0));
            String tail = S.substring(1);
            switch (head) {
                case "0": { decode(tail, root.left); break;}
                case "1": { decode(tail, root.right); break;}
            }
        }

    }

    public interface Console {
        void print(String str);
    }

    public Console console = new Console() {
        public void print(String str) {System.out.print(str);}
    };

    void print(Node node) {
        if(node != null) console.print(node.data+"");
    }

}
