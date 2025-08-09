import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;
public class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String plainText = sc.nextLine();
        System.out.print("Enter secret key (16 characters): ");
        String keyString = sc.nextLine();
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
        cipher.init(Cipher.DECRYPT_MODE, key);
        String decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)), "UTF-8");
        System.out.println("\nEncrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
        sc.close();
    }
}
