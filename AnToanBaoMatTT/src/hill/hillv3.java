package hill;

import java.util.*;

public class hillv3 {

	public static void getKeyMatrix(String key, int keyMatrix[][]) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				keyMatrix[i][j] = (key.charAt(k)) % 65;
				k++;
			}
		}
	}

	public static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
		int x, i, j;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 1; j++) {
				cipherMatrix[i][j] = 0;

				for (x = 0; x < 3; x++) {
					cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
				}

				cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
			}
		}
	}

	public static void HillCipher(String message, String key) {
		int[][] keyMatrix = new int[3][3];
		getKeyMatrix(key, keyMatrix);
		int[][] messageVector = new int[3][1];
		for (int i = 0; i < 3; i++)
			messageVector[i][0] = (message.charAt(i)) % 65;
		int[][] cipherMatrix = new int[3][1];
		encrypt(cipherMatrix, keyMatrix, messageVector);
		String CipherText = "";
		for (int i = 0; i < 3; i++)
			CipherText += (char) (cipherMatrix[i][0] + 65);
		System.out.print(" Ciphertext:" + CipherText);
	}
	 public static void decrypt(int cipherMatrix[][], int inverseKeyMatrix[][], int decryptedMatrix[][]) {
	        int i, j, x;
	        for (i = 0; i < 3; i++) {
	            for (j = 0; j < 1; j++) {
	                decryptedMatrix[i][j] = 0;

	                for (x = 0; x < 3; x++) {
	                    decryptedMatrix[i][j] += inverseKeyMatrix[i][x] * cipherMatrix[x][j];
	                }

	                decryptedMatrix[i][j] = Math.floorMod(decryptedMatrix[i][j], 26);
	            }
	        }
	    }
	 public static void HillCipherDecrypt(String cipherText, int[][] inverseKeyMatrix) {
	        int[][] cipherMatrix = new int[3][1];
	        for (int i = 0; i < 3; i++)
	            cipherMatrix[i][0] = (cipherText.charAt(i)) % 65;
	        int[][] decryptedMatrix = new int[3][1];
	        decrypt(cipherMatrix, inverseKeyMatrix, decryptedMatrix);
	        String DecryptedText = "";
	        for (int i = 0; i < 3; i++)
	            DecryptedText += (char) (decryptedMatrix[i][0] + 65);
	        System.out.print(" Decrypted Text: " + DecryptedText);
	    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Message");
		String message = sc.next();
		System.out.println("Enter Key");
		String key = sc.next();

		HillCipher(message, key);
	//	HillCipherDecrypt(key, );
	}
}
