import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;
public class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit AES
        SecretKey secretKey = keyGen.generateKey();
        System.out.print("Enter text to encrypt: ");
        String plainText = sc.nextLine();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String encryptedText = Base64.getEncoder()
                .encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)), "UTF-8");
        System.out.println("\nGenerated Secret Key (Base64): " +
                Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
        sc.close();
    }
}
