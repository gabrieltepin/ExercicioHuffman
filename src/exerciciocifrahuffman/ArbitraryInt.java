package exerciciocifrahuffman;
import java.io.*;
import java.util.*;
public class ArbitraryInt {
    public static int readInt(InputStream in) throws IOException {
        int v = 0;

        int b;
        do {
            b = in.read();
            if (b == -1) {
                return -1;
            }
            v = (v << 7) | (b & 0x7F);
        } while ((b & 0x80) != 0);

        return v;
    }
    public static void writeInt(OutputStream out, int v) throws IOException {
        Stack<Integer> st = new Stack<>();
        while (v != 0) {
            st.push(v & 0x7F);
            v >>= 7;
        }
        while (!st.empty()) {
            int b = st.pop();
            if (!st.empty()) {
                b |= 0x80;
            }
            out.write(b);
        }
    }
}
