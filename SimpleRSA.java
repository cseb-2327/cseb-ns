import java.math.BigInteger;

public class SimpleRSA {
    public static void main(String[] args) {
        // Small prime numbers (only for demonstration)
        BigInteger p = new BigInteger("61");
        BigInteger q = new BigInteger("53");

        // Compute n = p * q
        BigInteger n = p.multiply(q);

        // Compute phi(n) = (p-1)*(q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose e (public exponent), 1 < e < phi and gcd(e, phi) = 1
        BigInteger e = new BigInteger("17");

        // Compute d (private exponent), d * e ≡ 1 (mod phi)
        BigInteger d = e.modInverse(phi);

        // Display keys
        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        // Message to encrypt
        BigInteger message = new BigInteger("65");
        System.out.println("Original Message: " + message);

        // Encrypt: c = m^e mod n
        BigInteger encrypted = message.modPow(e, n);
        System.out.println("Encrypted Message: " + encrypted);

        // Decrypt: m = c^d mod n
        BigInteger decrypted = encrypted.modPow(d, n);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
