package Elgama;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalEncryption {
    private BigInteger p, a, x, y;
    private SecureRandom rnd = new SecureRandom();

    public ElGamalEncryption(BigInteger p, BigInteger a,BigInteger x) {
        this.p = p;
        this.a = a;
        this.x = x ;//new BigInteger(p.bitLength() - 1, rnd); // Private key
        this.y= a.modPow(x, p); // Public key //y
    }

    public BigInteger[] encrypt(String message, BigInteger k ) {
        byte[] bytes = message.getBytes();
        BigInteger m = new BigInteger(bytes);
       // BigInteger k = new BigInteger(p.bitLength() - 1, rnd);
        BigInteger c1 = a.modPow(k, p);
        BigInteger c2 = y.modPow(k, p).multiply(m).mod(p);
        return new BigInteger[]{c1, c2};
    }


    public String decrypt(BigInteger[] ciphertext) {
        BigInteger c1 = ciphertext[0];
        BigInteger c2 = ciphertext[1];
        BigInteger s = c1.modPow(x, p);
        BigInteger plaintext = c2.multiply(s.modInverse(p)).mod(p);
        return new String(plaintext.toByteArray());
    }


    public static void main(String[] args) {
        BigInteger p = new BigInteger("97");
        BigInteger a = new BigInteger("5"); //a
        BigInteger x = new BigInteger("58");
        BigInteger k = new BigInteger("36");
        ElGamalEncryption elGamal = new ElGamalEncryption(p, a,x);
        String message = "DHCN hanoi";

        // Encrypt
        BigInteger[] encrypted = elGamal.encrypt(message,k);
        System.out.print("Encrypted: ");
        for (BigInteger part : encrypted) {
            System.out.print(part.toString() + " ");
        }
        System.out.println();

        // Decrypt
        String decrypted = elGamal.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

}
