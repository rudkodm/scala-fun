package rank.tree;

import rank.tree.model.Node;

public class HeightPrint {

    int height(Node root) {
        if(root == null) return 0;
        Acc acc = new Acc();
        traverse(root, acc, 1);
        console.print(acc.max);
        return acc.max;
    }

    class Acc {
        int max = 0;
    }

    void traverse(Node node, Acc acc, int level){
        if(node == null) return;
        if(acc.max < level) acc.max = level;
        traverse(node.left, acc, level + 1);
        traverse(node.right, acc, level + 1);
    }


    public interface Console {
        void print(Object o);
    }

    public Console console = new Console() {
        public void print(Object o) {System.out.print(o);}
    };
}
