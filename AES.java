import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static SecretKeySpec getKey(String myKey) throws Exception {
        byte[] key = MessageDigest.getInstance("SHA-1").digest(myKey.getBytes("UTF-8"));
        return new SecretKeySpec(key, 0, 16, "AES");
    }

    public static String encrypt(String text, String secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(secret));
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
    }

    public static String decrypt(String text, String secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKey(secret));
        return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
    }

    public static void main(String[] args) throws Exception {
        String secret = "mySecretKey"; // or take from args
        String original = "https://example.com";
        String enc = encrypt(original, secret);
        String dec = decrypt(enc, secret);

        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}

