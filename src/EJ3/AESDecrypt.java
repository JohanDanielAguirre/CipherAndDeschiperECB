package EJ3;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AESDecrypt {
    
    public static void main(String[] args) {
        try {
            // Ejemplo de uso con AES-128
            String claveHex = "2B7E151628AED2A6ABF7158809CF4F3C"; 
            String cipherHex = "3AD77BB40D7A3660A89ECAF32466EF97";
            
            byte[] resultado = descifrar(claveHex, cipherHex);
            
            System.out.println("=== DESCIFRADO AES ===");
            System.out.println("Clave (hex)    : " + claveHex);
            System.out.println("Cifrado (hex)  : " + cipherHex);
            System.out.println("Descifrado (hex): " + bytesToHex(resultado));
            
        } catch (Exception e) {
            System.err.println("Error en el descifrado AES: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static byte[] descifrar(String claveHex, String cipherHex) throws Exception {
        byte[] theKey = hexToBytes(claveHex);
        byte[] theCipher = hexToBytes(cipherHex);
        
        SecretKey secretKey = new SecretKeySpec(theKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        return cipher.doFinal(theCipher);
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16) {
                    str = str + "0" + Integer.toHexString(data[i] & 0xFF);
                } else {
                    str = str + Integer.toHexString(data[i] & 0xFF);
                }
            }
            return str.toUpperCase();
        }
    }
}
