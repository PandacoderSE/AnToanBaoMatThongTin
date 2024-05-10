package RSA;

import java.util.Scanner;

public class RSA {
    public static boolean ktntcn(int q, int p) {
        while (p != 0) {
            int temp = q % p;
            q = p;
            p = temp;
        }
        return q == 1;
    }

    public static int kq(int e, int phi_n) {
        int[] r = new int[100];
        int[] q = new int[100];
        int[] x = new int[100];
        int[] y = new int[100];
        r[0] = phi_n;
        r[1] = e;
        x[0] = 0;
        x[1] = 1;
        y[0] = 1;
        y[1] = 1;

        int i = 1;
        while (r[i] != 1) {
            i++;
            r[i] = r[i - 2] % r[i - 1];
            q[i] = r[i - 2] / r[i - 1];
            x[i] = x[i - 2] - q[i] * x[i - 1];
            y[i] = r[i - 2] - q[i] * y[i - 1];
        }
        if (x[i] < 0) {
            x[i] = x[i] + phi_n;
        }
        return x[i];
    }

    public static int amub(int a, int b, int n) {
        int doinp = b;
        int i = 0;
        int[] mng = new int[1000];
        while (doinp != 0) {
            mng[i] = doinp % 2;
            doinp /= 2;
            i++;
        }
        int f = 1;
        for (int j = i - 1; j >= 0; j--) {
            f = (f * f) % n;
            if (mng[j] == 1) {
                f = (f * a) % n;
            }
        }
        return f;
    }

    public static int[] encrypt(String plaintext, int e, int n) {
        int[] encrypted = new int[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            encrypted[i] = amub((int) ch, e, n);
        }
        return encrypted;
    }

    public static String decrypt(int[] encrypted, int d, int n) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length; i++) {
            int decryptedChar = amub(encrypted[i], d, n);
            decrypted.append((char) decryptedChar);
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi ban nhap q , p");
        int q = sc.nextInt();
        int p = sc.nextInt();
        if (q != p) {
            int phi_n = (q - 1) * (p - 1);
            int n = q * p;
            int e = 0;
            for (int i = 2; i < phi_n; i++) {
                if (ktntcn(phi_n, i)) {
                    e = i;
                    break;
                }
            }
            if (e == 0) {
                System.out.println("Khong co e !");
            } else {
                int d = kq(e, phi_n);
                System.out.println("khoa cong khai la : (" + e + "," + n + ")");
                System.out.println("khoa bi mat la : (" + d + "," + n + ")");

                // Mã hóa và giải mã một chuỗi văn bản
                String plaintext = "Van manh";
                int[] encrypted = encrypt(plaintext, e, n);
                System.out.print("Chuỗi đã mã hóa: ");
                for (int i : encrypted) {
                    System.out.print(i + " ");
                }
                System.out.println();

                String decryptedText = decrypt(encrypted, d, n);
                System.out.println("Chuỗi đã giải mã: " + decryptedText);
            }
        }
    }
}
