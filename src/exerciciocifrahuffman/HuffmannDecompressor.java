package exerciciocifrahuffman;
import java.io.*;
public class HuffmannDecompressor {

    String outputString;
    int[] frequencies;
    int[] letterFrequencies;

    public HuffmannDecompressor() {
        frequencies = new int[256];
        letterFrequencies = new int[26];
    }

    public void decompressString(InputStream in) throws IOException {
        DataInputStream stream = new DataInputStream(in);

        int sz = ArbitraryInt.readInt(stream);

        for (int j = 0; j < sz; j++) {
            int i = stream.readByte();
            frequencies[i] = ArbitraryInt.readInt(stream);
        }

        for (int i = 0; i < 26; i++) {
            letterFrequencies[i] = frequencies['A' + i] + frequencies['a' + i];
        }

        FrequencyTree tree = new FrequencyTree(frequencies);
        FrequencyNode node = tree.getRoot();

        StringBuilder chars = new StringBuilder();

        int b = 0;
        int numBits = 8;
        int len = ArbitraryInt.readInt(stream);
        for (int i = 0; i < len; i++) {
            if (numBits == 8) {
                numBits = 0;
                b = stream.readByte();
            }

            node = (b & 0x80) == 0 ? node.left : node.right;
            if (node.isLeaf()) {
                chars.append(node.c);
                node = tree.getRoot();
            }

            b <<= 1;
            numBits++;
        }

        outputString = chars.toString();
    }

    public int[] getFrequencies() {
        return frequencies;
    }

    public int[] getLetterFrequencies() {
        return letterFrequencies;
    }

    public String getOutputString() {
        return outputString;
    }
}
