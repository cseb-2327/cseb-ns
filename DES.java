import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class DES {
    private static byte[] key;

    public static void main(String[] args) throws Exception {
        
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        key = secretKey.getEncoded();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message to encrypt: ");
        String message = scanner.nextLine();

        byte[] encrypted = encrypt(key, message.getBytes());
        System.out.println("Encrypted (hex): " + bytesToHex(encrypted));

        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted message: " + new String(decrypted));
    }

    private static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(encrypted);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
