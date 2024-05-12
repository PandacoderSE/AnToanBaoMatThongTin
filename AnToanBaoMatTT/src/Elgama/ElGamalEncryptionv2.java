package Elgama;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalEncryptionv2 {
    private BigInteger p, g, h, x, y;
    private SecureRandom rnd;

    public ElGamalEncryptionv2(BigInteger p, BigInteger g, BigInteger h, BigInteger x) {
        this.p = p;
        this.g = g;
        this.h = h;
        this.x = x;
        this.rnd = new SecureRandom();
    }

    public BigInteger[] encrypt(String message) {
        byte[] bytes = message.getBytes();
        BigInteger m = new BigInteger(bytes);
        BigInteger k = new BigInteger(p.bitLength(), rnd);
        y = g.modPow(k, p);
        BigInteger s = h.modPow(k, p);
        BigInteger c = m.multiply(s).mod(p);
        return new BigInteger[]{y, c};
    }

    public String decrypt(BigInteger[] encrypted) {
        BigInteger y = encrypted[0];
        BigInteger c = encrypted[1];
        BigInteger s = y.modPow(x, p);
        BigInteger m = c.multiply(s.modInverse(p)).mod(p);
        return new String(m.toByteArray());
    }

    public static void main(String[] args) {
        // Example usage:
        BigInteger p = new BigInteger("Your large prime number here");
        BigInteger g = new BigInteger("Your generator value here");
        BigInteger h = new BigInteger("Your public key h value here");
        BigInteger x = new BigInteger("Your private key x value here");

        ElGamalEncryptionv2 elGamal = new ElGamalEncryptionv2(p, g, h, x);

        // Encrypting the message
        String message = "Hello, this is a test message!";
        BigInteger[] encrypted = elGamal.encrypt(message);
        System.out.println("Encrypted message: " + encrypted[0].toString(16) + " " + encrypted[1].toString(16));

        // Decrypting the message
        String decryptedMessage = elGamal.decrypt(encrypted);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
