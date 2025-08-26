import EJ3.AESDecrypt;
import org.junit.jupiter.api.Test;
import javax.crypto.*;
import javax.crypto.spec.*;
import static org.junit.jupiter.api.Assertions.*;


public class AESDecryptTest {

    private byte[] descifrar(String claveHex, String cipherHex) throws Exception {
        byte[] theKey = AESDecrypt.hexToBytes(claveHex);
        byte[] theCipher = AESDecrypt.hexToBytes(cipherHex);
        
        SecretKey secretKey = new SecretKeySpec(theKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        return cipher.doFinal(theCipher);
    }

    @Test
    public void testVector1_AES128() throws Exception {
        String clave = "2B7E151628AED2A6ABF7158809CF4F3C";
        String cipher = "3AD77BB40D7A3660A89ECAF32466EF97";
        String esperado = "6BC1BEE22E409F96E93D7E117393172A";
        
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, AESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector2_AES128_Pattern() throws Exception {
        String claveHex = "2B7E151628AED2A6ABF7158809CF4F3C";
        String cipherHex = "3AD77BB40D7A3660A89ECAF32466EF97";

        byte[] resultado = AESDecrypt.descifrar(claveHex, cipherHex);

        String esperado = "6BC1BEE22E409F96E93D7E117393172A";

        assertEquals(esperado, AESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector3_AES192() throws Exception {
        String clave = "8E73B0F7DA0E6452C810F32B809079E562F8EAD2522C6B7B";
        String cipher = "BD334F1D6E45F25FF712A214571FA5CC";
        String esperado = "6BC1BEE22E409F96E93D7E117393172A";
        
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, AESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector4_AES256() throws Exception {
        String clave = "603DEB1015CA71BE2B73AEF0857D77811F352C073B6108D72D9810A30914DFF4";
        String cipher = "F3EED1BDB5D2A03C064B5A7E3DB181F8";
        String esperado = "6BC1BEE22E409F96E93D7E117393172A";
        
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, AESDecrypt.bytesToHex(resultado));
    }
}
