package exerciciocifrahuffman;
import java.io.*;
import java.util.*;
public class HuffmannCompressor {
    public HuffmannCompressor() {
    }
    public void compressString(String message, OutputStream out) throws IOException {
        DataOutputStream stream = new DataOutputStream(out);
        FrequencyArray farr = new FrequencyArray(message);
        FrequencyTree tree = farr.buildFrequencyTree();
        int sz = 0;
        for (int i = 0; i < 256; i++) {
            int freq = farr.frequencyForChar((char) i);
            if (freq != 0) {
                sz++;
            }
        }
        ArbitraryInt.writeInt(stream, sz);
        for (int i = 0; i < 256; i++) {
            int freq = farr.frequencyForChar((char) i);
            if (freq == 0) {
                continue;
            }
            stream.writeByte(i);
            ArbitraryInt.writeInt(stream, freq);
        }
        int curbuf = 0, curlen = 0, totallen = 0;
        ArrayList<Byte> bytes = new ArrayList<>();
        for (char c : message.toCharArray()) {
            int bit = tree.bitcodeForChar(c);
            int len = tree.bitcodeLengthForChar(c);
            curbuf = (curbuf << len) | bit;
            curlen += len;
            totallen += len;
            while (curlen >= 8) {
                bytes.add((byte) (curbuf >> (curlen - 8)));
                curbuf &= ~(0xFF << (curlen - 8));
                curlen -= 8;
            }
        }
        if (curlen > 0) {
            bytes.add((byte) (curbuf << (8 - curlen)));
        }
        ArbitraryInt.writeInt(stream, totallen);
        for (Byte b : bytes) {
            stream.write(b);
        }
    }
}
