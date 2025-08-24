package EJ2;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;

public class DESDecrypt {
    public static void main(String[] args) {
        String test = "1";
        try {
            byte[] theKey = null;
            byte[] theMsg = null;
            byte[] theExp = null;

            KeySpec ks = new DESKeySpec(theKey);
            SecretKeyFactory kf
                    = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
            cf.init(Cipher.ENCRYPT_MODE,ky);
            byte[] theCph = cf.doFinal(theMsg);

            Cipher df = Cipher.getInstance("DES/ECB/NoPadding");
            df.init(Cipher.DECRYPT_MODE, ky);
            byte[] theDec = df.doFinal(theCph);

            System.out.println("Key     : "+bytesToHex(theKey));
            System.out.println("Message : "+bytesToHex(theMsg));
            System.out.println("Cipher  : "+bytesToHex(theCph));
            System.out.println("Expected: "+bytesToHex(theExp));
            System.out.println("Decrypted: "+bytesToHex(theDec));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    public static byte[] hexToBytes(String str) {
        if (str==null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i=0; i<len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i*2,i*2+2),16);
            }
            return buffer;
        }

    }
    public static String bytesToHex(byte[] data) {
        if (data==null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i=0; i<len; i++) {
                if ((data[i]&0xFF)<16) str = str + "0"
                        + Integer.toHexString(data[i]&0xFF);
                else str = str
                        + Integer.toHexString(data[i]&0xFF);
            }
            return str.toUpperCase();
        }
    }
}