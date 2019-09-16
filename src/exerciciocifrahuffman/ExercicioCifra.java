package exerciciocifrahuffman;

import java.io.*;
import java.util.Scanner;

public class ExercicioCifra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose one option");
        System.out.println("1 - Cryptograph and Compress");
        System.out.println("2 - Decompress and decryptograph");
        System.out.println("3 - Cryptography Analysis");
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                option1(sc);
                break;
            case 2:
                option2(sc);
                break;
            case 3:
                option3(sc);
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }
    private static void option1(Scanner sc) {
        System.out.println("Choose the numeric cipher:");
        int cipher = sc.nextInt();
        // int cipher = 5;
        sc.nextLine();

        HuffmannCompressor hc = new HuffmannCompressor();
        CaesarCipher caesar = new CaesarCipher(cipher);

        System.out.println("Enter input file path:");
        String inFilename = sc.nextLine();
        // String inFilename = "src/input.txt";

        System.out.println("Enter output file path:");
        String filename = sc.nextLine();
        // String filename = "src/output.txt";

        try {
            FileInputStream in = new FileInputStream(inFilename);
            StringBuilder sb = new StringBuilder();

            while (true) {
                int b = in.read();
                if (b == -1) {
                    break;
                }
                sb.append((char) b);
            }

            FileOutputStream out = new FileOutputStream(filename);
            hc.compressString(caesar.cipher(sb.toString()), out);
        } catch (IOException e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }

    private static void option2(Scanner sc) {
        System.out.println("Enter numeric key: ");
        int cipher = sc.nextInt();
        sc.nextLine();

        HuffmannDecompressor hd = new HuffmannDecompressor();
        CaesarCipher caesar = new CaesarCipher(cipher);

        System.out.println("Enter input file path:");
        String inFilename = sc.nextLine();

        System.out.println("Enter output file path:");
        String filename = sc.nextLine();

        try {
            FileInputStream in = new FileInputStream(inFilename);
            hd.decompressString(in);

            FileOutputStream out = new FileOutputStream(filename);
            String str = caesar.decipher(hd.getOutputString());
            for (char c : str.toCharArray()) {
                out.write(c);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    private static void option3(Scanner sc) {
        sc.nextLine();

        System.out.println("Enter input file path:");
        String filename = sc.nextLine();
        System.out.println("Enter output file path:");
        String outFilename = sc.nextLine();

        HuffmannDecompressor hd = new HuffmannDecompressor();

        try {
            FileInputStream in = new FileInputStream(filename);
            hd.decompressString(in);

            CryptoAnalyzer crypto = new CryptoAnalyzer(hd.getLetterFrequencies());
            crypto.decipherPossible(hd.getOutputString());

            FileOutputStream out = new FileOutputStream(outFilename);
            for (char c : crypto.getOutputString().toCharArray()) {
                out.write(c);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

}
