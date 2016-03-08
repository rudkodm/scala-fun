package rank.tree.util;

import rank.tree.model.huff.Node;

/**
 * Created by rudkodm on 3/6/16.
 */
public class NodeUtil {

    public static rank.tree.model.Node N(int data) {
        return N(data, null, null);
    }

    public static rank.tree.model.Node N(int data, rank.tree.model.Node left, rank.tree.model.Node right) {
        return new rank.tree.model.Node(data, left, right);
    }

    public static Node H(int freq, char data, Node left, Node right) {
        return new Node(freq, data, left, right);
    }

    public static Node H(int freq, char data) {
        return new Node(freq, data, null, null);
    }

    /**
     * <pre>
     * {@code
     *
     *       3
     *     /   \
     *    5     2
     *   / \   / \
     *  1   4 6   7
     *       \   /
     *        9 8
     * }
     * </pre>
     */
    public static rank.tree.model.Node root1 = N(3, N(5, N(1, null, N(9)), N(4)), N(2, N(6), N(7, N(8), null)));

    /**
     * <pre>
     * {@code
     *
     *       3
     *     /   \
     *    5     2
     *   / \   /
     *  1   4 6
     * }
     * </pre>
     */
    public static rank.tree.model.Node root2 = N(3, N(5, N(1), N(4)), N(2, N(6), null));

    /**
     * <pre>
     * {@code
     *
     *       3
     *     /   \
     *    5     2
     *   / \   / \
     *  1   4 6   7
     * }
     * </pre>
     */
    public static rank.tree.model.Node root3 = N(3, N(5, N(1), N(4)), N(2, N(6, N(7), null), null));

    /**
     * <pre>
     * {@code
     *
     *       4
     *     /   \
     *    2     7
     *   / \   /
     *  1   3 6
     * }
     * </pre>
     */
    public static rank.tree.model.Node root4 = N(4, N(2, N(1), N(3)), N(7, N(6), null));
}
