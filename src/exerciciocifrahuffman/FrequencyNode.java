package exerciciocifrahuffman;
public class FrequencyNode implements Comparable<FrequencyNode> {

    public char c;
    public int priority;
    public FrequencyNode left, right;

    @Override
    public int compareTo(FrequencyNode o) {
        return Integer.compare(priority, o.priority);
    }

    public FrequencyNode(char c, int priority) {
        this.c = c;
        this.priority = priority;
        this.left = this.right = null;
    }

    public FrequencyNode(FrequencyNode left, FrequencyNode right) {
        this.c = (int) 0;
        this.left = left;
        this.right = right;
        this.priority = left.priority + right.priority;
    }

    public boolean isLeaf() {
        return left == null;
    }
}
