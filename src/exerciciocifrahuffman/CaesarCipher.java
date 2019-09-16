package exerciciocifrahuffman;
public class CaesarCipher {
    int cipher;
    public CaesarCipher(int cipher) {
        this.cipher = cipher;
    }
    private String doCipher(String message, int cipher) {
        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) ('A' + ((chars[i] - 'A') + cipher) % 26);
            } else if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char) ('a' + ((chars[i] - 'a') + cipher) % 26);
            }
        }
        return new String(chars);
    }
    public String cipher(String message) {
        return doCipher(message, cipher);
    }
    public String decipher(String message) {
        return doCipher(message, 26 - cipher);
    }
}
