package Elgama;
import java.util.ArrayList;
import java.util.Random;

public class ElGamalEncryptionv2 {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        String msg = "encryption";
        System.out.println("Original Message: " + msg);

        long q = (long) Math.pow(10, 19) + rand.nextInt((int) Math.pow(10, 20));
        long g = rand.nextInt((int) (q - 2)) + 2;

        long key = genKey(q); // Private key for receiver
        long h = power(g, key, q);

        System.out.println("g used: " + g);
        System.out.println("g^a used: " + h);

        ArrayList<Long> enMsg = encrypt(msg, q, h, g);
        ArrayList<Character> drMsg = decrypt(enMsg, h, key, q);

        System.out.print("Decrypted Message: ");
        for (char c : drMsg) {
            System.out.print(c);
        }
        System.out.println();
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static long genKey(long q) {
        long key = rand.nextInt((int) (q - 2)) + 2;
        while (gcd(q, key) != 1) {
            key = rand.nextInt((int) (q - 2)) + 2;
        }
        return key;
    }

    private static long power(long a, long b, long c) {
        long x = 1;
        long y = a % c;

        while (b > 0) {
            if (b % 2 != 0) {
                x = (x * y) % c;
            }
            y = (y * y) % c;
            b /= 2;
        }
        return x % c;
    }

    private static ArrayList<Long> encrypt(String msg, long q, long h, long g) {
        ArrayList<Long> enMsg = new ArrayList<>();
        long k = genKey(q); // Private key for sender
        long s = power(h, k, q);
        long p = power(g, k, q);

        System.out.println("g^k used: " + p);
        System.out.println("g^ak used: " + s);

        for (int i = 0; i < msg.length(); i++) {
            enMsg.add((long) msg.charAt(i));
        }

        for (int i = 0; i < enMsg.size(); i++) {
            enMsg.set(i, s * enMsg.get(i));
        }

        return enMsg;
    }

    private static ArrayList<Character> decrypt(ArrayList<Long> enMsg, long p, long key, long q) {
        ArrayList<Character> drMsg = new ArrayList<>();
        long h = power(p, key, q);

        for (Long l : enMsg) {
            drMsg.add((char) (l / h));
        }
        return drMsg;
    }
}
