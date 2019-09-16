package exerciciocifrahuffman;

import java.util.*;
public class CryptoAnalyzer {

    int[] frequencies;
    int possibleCipher;
    String outputString;

    public CryptoAnalyzer(int[] frequencies) {
        this.frequencies = frequencies;
        detectPossibleCipher();
    }
    private void detectPossibleCipher() {
        int[] freqChars = {0, 4, 13, 17, 16, 8, 3, 12, 19, 2, 18,
            11, 14, 20, 1, 5, 6, 7, 15, 9, 10, 21, 22, 23, 24, 25};
        Integer[] indices = new Integer[26];
        for (int i = 0; i < 26; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return Integer.compare(frequencies[j], frequencies[i]);
            }
        });
        possibleCipher = (indices[0] - freqChars[0] + 26) % 26;
    }

    public void decipherPossible(String inputString) {
        char[] output = new char[inputString.length()];
        for (int i = 0; i < output.length; i++) {
            char c = inputString.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                output[i] = (char) ('A' + (c - 'A' - possibleCipher + 26) % 26);
            } else if (c >= 'a' && c <= 'z') {
                output[i] = (char) ('a' + (c - 'a' - possibleCipher + 26) % 26);
            } else {
                output[i] = c;
            }
        }
        outputString = new String(output);
    }

    public String getOutputString() {
        return outputString;
    }
}
