package ua.kpi.cad.msa.encryptionservice.algo;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;

@Component
public class EncryptionWorker {
    public String encrypt(String value) {
        try {
            KeyPair keyPair = buildKeyPair();
            PublicKey publicKey = keyPair.getPublic(); // not used..
            PrivateKey privateKey = keyPair.getPrivate();

            byte[] encrypted = encrypt(privateKey, value);

            return new String(encrypted);

        } catch (Exception e) {
            //
        }
        return null;
    }

    private byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    private KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }
}
