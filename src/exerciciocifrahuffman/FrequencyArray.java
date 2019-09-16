package exerciciocifrahuffman;
public class FrequencyArray {
    int[] frequencies;
    public FrequencyArray(String s) {
        frequencies = new int[256];
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i)]++;
        }
    }
    public FrequencyTree buildFrequencyTree() {
        return new FrequencyTree(frequencies);
    }
    public int frequencyForChar(char i) {
        return frequencies[i];
    }
}
