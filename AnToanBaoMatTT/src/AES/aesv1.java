package AES;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;
public class aesv1 {

	public static String maHoaAES(String vanBan, SecretKey key) throws Exception {
		        Cipher cipher = Cipher.getInstance("AES");
		        cipher.init(Cipher.ENCRYPT_MODE, key);

		        byte[] vanBanMaHoa = cipher.doFinal(vanBan.getBytes());
		        return Base64.getEncoder().encodeToString(vanBanMaHoa);
		    }

		    public static String giaiMaAES(String vanBanMaHoa, SecretKey key) throws Exception {
		        Cipher cipher = Cipher.getInstance("AES");
		        cipher.init(Cipher.DECRYPT_MODE, key);

		        byte[] vanBanMaHoaBytes = Base64.getDecoder().decode(vanBanMaHoa);
		        byte[] vanBanGiaiMaBytes = cipher.doFinal(vanBanMaHoaBytes);

		        return new String(vanBanGiaiMaBytes);
		    }

		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);

		        try {
		            // Khởi tạo khóa AES
		            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		            SecretKey key = keyGenerator.generateKey();

		            System.out.print("Nhập vào chuỗi cần mã hóa: ");
		            String vanBan = scanner.nextLine();

		            // Mã hóa
		            String vanBanMaHoa = maHoaAES(vanBan, key);
		            System.out.println("Văn bản đã mã hóa: " + vanBanMaHoa);

		            // Giải mã
		            String vanBanGiaiMa = giaiMaAES(vanBanMaHoa, key);
		            System.out.println("Văn bản đã giải mã: " + vanBanGiaiMa);

		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            scanner.close();
		        }
		    }


}
