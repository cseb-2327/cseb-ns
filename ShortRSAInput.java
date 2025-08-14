import javax.crypto.Cipher;
import java.security.*;
import java.util.*;

public class ShortRSAInput {
    public static void main(String[] args) throws Exception {
        // Key pair generation
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        // Get plaintext from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String message = sc.nextLine();

        // Encrypt
        Cipher enc = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        enc.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] cipherText = enc.doFinal(message.getBytes());

        // Decrypt
        Cipher dec = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        dec.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        byte[] plainText = dec.doFinal(cipherText);

        // Output
        System.out.println("\nCipher (Base64): " + Base64.getEncoder().encodeToString(cipherText));
        System.out.println("Decrypted: " + new String(plainText));
    }
}
