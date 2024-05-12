package Elgama;
import java.math.BigInteger;
import java.util.Random;

public class ElGamalExample {
    private BigInteger p, a, x, y, k, Y;
    private Random r;

    public ElGamalExample() {
        r = new Random();
        p = BigInteger.probablePrime(512, r); // Số nguyên tố lớn
        a = new BigInteger("4"); // Giả sử a là 4
        x = new BigInteger("12345678901234567890"); // Khóa riêng tư
        y = a.modPow(x, p); // Khóa công khai
        k = new BigInteger("23456789012345678901"); // Số ngẫu nhiên k
    }

    public BigInteger[] encrypt(String message) { // dùng khóa công khai {p,a,y}
        byte[] bytes = message.getBytes();
        BigInteger m = new BigInteger(bytes);
        Y = a.modPow(k, p);
        BigInteger c1 = Y;
        BigInteger c2 = m.multiply(y.modPow(k, p)).mod(p);
        return new BigInteger[]{c1, c2};
    }

    public String decrypt(BigInteger c1, BigInteger c2) {
        BigInteger m = c2.multiply(c1.modPow(x.negate(), p)).mod(p);
        return new String(m.toByteArray());
    }

    public static void main(String[] args) {
        ElGamalExample elGamal = new ElGamalExample();

        // Mã hóa
        String message = "Hà nội";
        BigInteger[] encrypted = elGamal.encrypt(message);
        System.out.println("Ciphertext: " + encrypted[0].toString(255) + " " + encrypted[1].toString(16));

        // Giải mã
        String decryptedMessage = elGamal.decrypt(encrypted[0], encrypted[1]);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
