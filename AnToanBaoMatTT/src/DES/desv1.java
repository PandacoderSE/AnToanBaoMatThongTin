package DES;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class desv1 {
	 public static String maHoa(String vanBan, String khoa) throws Exception {
	        DESKeySpec desKeySpec = new DESKeySpec(khoa.getBytes());
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

	        byte[] vanBanMaHoa = cipher.doFinal(vanBan.getBytes());
	        return Base64.getEncoder().encodeToString(vanBanMaHoa);
	    }

	    public static String giaiMa(String vanBanMaHoa, String khoa) throws Exception {
	        DESKeySpec desKeySpec = new DESKeySpec(khoa.getBytes());
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);

	        byte[] vanBanMaHoaBytes = Base64.getDecoder().decode(vanBanMaHoa);
	        byte[] vanBanGiaiMaBytes = cipher.doFinal(vanBanMaHoaBytes);

	        return new String(vanBanGiaiMaBytes);
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        try {
	            System.out.print("Nhập vào chuỗi cần mã hóa: ");
	            String vanBan = scanner.nextLine();

	            System.out.print("Nhập vào khóa (8 ký tự): ");
	            String khoa = scanner.nextLine();

	            // Kiểm tra độ dài của khóa
	            if (khoa.length() != 8) {
	                System.out.println("Khóa phải có độ dài 8 ký tự.");
	                return;
	            }

	            // Mã hóa
	            String vanBanMaHoa = maHoa(vanBan, khoa);
	            System.out.println("Văn bản đã mã hóa: " + vanBanMaHoa);

	            // Giải mã
	            String vanBanGiaiMa = giaiMa(vanBanMaHoa, khoa);
	            System.out.println("Văn bản đã giải mã: " + vanBanGiaiMa);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            scanner.close();
	        }
	    }
}