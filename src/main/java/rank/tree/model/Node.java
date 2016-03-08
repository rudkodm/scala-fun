package rank.tree.model;

public class Node {
    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int data;
    public Node left;
    public Node right;

    public Node() {
    }

    @Override
    public String toString() {
        String s = String.valueOf(data);
        s += " ";
        s += (left == null) ? "" : left.toString();
        s += (right == null) ? "" : right.toString();
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (data != node.data) return false;
        if (left == null && left != node.left) return false;
        if (left != null && !left.equals(node.left)) return false;
        if (right == null && right != node.right) return false;
        if (right != null && !right.equals(node.right)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = data;
        if(left != null) result = 31 * result + left.hashCode();
        if(right != null) result = 31 * result + right.hashCode();
        return result;
    }
}
