package hill;
import java.util.Scanner;

public class hillv2 {
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    } 

    public static int oclitMoRong(int r0, int r1) {
        int[] r = new int[100];
        int[] q = new int[100];
        int[] s = new int[100];
        int[] t = new int[100];

        r[0] = r0;
        r[1] = r1;
        s[0] = 1;
        t[0] = 0;
        s[1] = 0;
        t[1] = 1;
        int i = 0;
        while (true) {
            q[i + 1] = r[i] / r[i + 1];
            r[i + 2] = r[i] % r[i + 1];
            if (i > 1) {
                s[i] = s[i - 2] - q[i - 1] * s[i - 1];
                t[i] = t[i - 2] - q[i - 1] * t[i - 1];
            }
            if (r[i + 2] == 0) break;
            i++;
        }
        i++;
        s[i] = s[i - 2] - q[i - 1] * s[i - 1];
        t[i] = t[i - 2] - q[i - 1] * t[i - 1];
        return t[i] > 0 ? t[i] : t[i] + r0;
    }

    public static int detK(int[][] k) {
        int det = k[0][0] * k[1][1] - k[0][1] * k[1][0];
        while (det < 0) {
            det += 26;
        }
        return det % 26;
    }

    public static int[][] khoaGiaiMa(int[][] k) {
        int detK1 = oclitMoRong(26, detK(k));
        int[][] pK = {{k[1][1], -k[0][1]}, {-k[1][0], k[0][0]}};

        int[][] kGiaiMa = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                pK[i][j] *= detK1;
                pK[i][j] %= 26;
                if (pK[i][j] < 0) pK[i][j] += 26;
                kGiaiMa[i][j] = pK[1 - j][1 - i];
            }
        }
        return kGiaiMa;
    }

    public static void tichMatrix(int[] p, int[][] k, int[] c) {
        for (int i = 0; i < 2; i++) {
            c[i] = 0;
            for (int j = 0; j < 2; j++) {
                c[i] += p[j] * k[j][i];
            }
            c[i] %= 26;
        }
    }

    public static String hillmahoa(String s, int[][] k) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            int[] p = {s.charAt(i) - 'A', s.charAt(i + 1) - 'A'};
            int[] c = new int[2];
            tichMatrix(p, k, c);
            res.append((char) (c[0] + 'A'));
            res.append((char) (c[1] + 'A'));
        }
        return res.toString();
    }
    
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] k = new int[2][2];

        System.out.println("Nhap ma tran khoa k:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                k[i][j] = scanner.nextInt();
            }
        }

        //System.out.println("Khoa giai ma la:");
        //khoaGiaiMa(k);

        System.out.print("Nhap xau ki tu 1: ");
        String s1 = scanner.next();
        System.out.println("Mã Hóa"+hillmahoa(s1, k));
        System.out.println("Giải mã :" );
        
    }
}

