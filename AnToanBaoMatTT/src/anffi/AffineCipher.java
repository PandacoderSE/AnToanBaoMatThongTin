package anffi;

public class AffineCipher {
	 // Hàm tìm ước số nguyên lớn nhất của hai số
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Hàm để mã hóa một ký tự
    public static char encryptChar(char ch, int a, int b) {
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                return (char) (((a * (ch - 'A') + b) % 26) + 'A');
            } else {
                return (char) (((a * (ch - 'a') + b) % 26) + 'a');
            }
        }
        return ch;
    }

    // Hàm để giải mã một ký tự
    public static char decryptChar(char ch, int a, int b) {
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                int x = ch - 'A';
                int x_inv = 0;
                // Tìm phần tử nghịch đảo của x modulo 26
                for (int i = 1; i < 26; i++) {
                    if ((a * i) % 26 == 1) {
                        x_inv = i;
                        break;
                    }
                }
                return (char) ((x_inv * (x - b + 26)) % 26 + 'A');
            } else {
                int x = ch - 'a';
                int x_inv = 0;
                // Tìm phần tử nghịch đảo của x modulo 26
                for (int i = 1; i < 26; i++) {
                    if ((a * i) % 26 == 1) {
                        x_inv = i;
                        break;
                    }
                }
                return (char) ((x_inv * (x - b + 26)) % 26 + 'a');
            }
        }
        return ch;
    }

    // Hàm để mã hóa văn bản
    public static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(encryptChar(text.charAt(i), a, b));
        }
        return result.toString();
    }

    // Hàm để giải mã văn bản
    public static String decrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(decryptChar(text.charAt(i), a, b));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "Dai hoc Cong nghiep ha noi";
        int a = 5;
        int b = 8;
        // Kiểm tra xem a có là một phần của nhóm modulo 26 hay không
        // (tức là a và 26 phải là số nguyên tố cùng nhau)
        if (gcd(a, 26) == 1) {
            String encryptedText = encrypt(text, a, b);
            System.out.println("Text: " + text);
            System.out.println("Encrypted Text: " + encryptedText);
            System.out.println("Decrypted Text: " + decrypt(encryptedText, a, b));
        } else {
            System.out.println("Không thể mã hóa với a và 26 không phải là số nguyên tố cùng nhau.");
        }
    }
}
