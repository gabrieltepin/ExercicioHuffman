package exerciciocifrahuffman;

import java.util.PriorityQueue;
public class FrequencyTree {

    int[] bitcodes;
    int[] bitLengths;
    FrequencyNode root;

    public FrequencyTree(int[] frequencies) {
        bitcodes = new int[frequencies.length];
        bitLengths = new int[frequencies.length];

        PriorityQueue<FrequencyNode> nodes = new PriorityQueue<>();

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                nodes.add(new FrequencyNode((char) i, frequencies[i]));
            }
        }

        while (nodes.size() > 1) {
            FrequencyNode left = nodes.remove();
            FrequencyNode right = nodes.remove();
            nodes.add(new FrequencyNode(left, right));
        }

        root = nodes.remove();
        buildBitcodes(root, 0, 0);
    }

    public int bitcodeForChar(char c) {
        return bitcodes[c];
    }

    public int bitcodeLengthForChar(char c) {
        return bitLengths[c];
    }

    private void buildBitcodes(FrequencyNode node, int bitcode, int bitLength) {
        if (node.left == null && node.right == null) {
            bitcodes[node.c] = bitcode;
            bitLengths[node.c] = bitLength;
        } else {
            buildBitcodes(node.left, 2 * bitcode, bitLength + 1);
            buildBitcodes(node.right, 2 * bitcode + 1, bitLength + 1);
        }
    }

    public FrequencyNode getRoot() {
        return root;
    }
}
