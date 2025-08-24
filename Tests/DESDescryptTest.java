import EJ2.DESDecrypt;
import org.junit.Test;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import static org.junit.jupiter.api.Assertions.*;

public class DESDescryptTest {

    private byte[] descifrar(String claveHex, String cipherHex) throws Exception {
        byte[] theKey = DESDecrypt.hexToBytes(claveHex);
        byte[] theCipher = DESDecrypt.hexToBytes(cipherHex);
        KeySpec ks = new DESKeySpec(theKey);
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        SecretKey ky = kf.generateSecret(ks);
        Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
        cf.init(Cipher.DECRYPT_MODE, ky);
        return cf.doFinal(theCipher);
    }

    @Test
    public void testVector1() throws Exception {
        String clave = "0101010101010101";
        String cipher = "DD7F121CA5015619";
        String esperado = "4000000000000000";
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, DESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector2() throws Exception {
        String clave = "38627974656B6579";
        String cipher = "7CF45E129445D451";
        String esperado = "6D6573736167652E";
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, DESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector3() throws Exception {
        String clave = "133457799BBCDFF1";
        String cipher = "85E813540F0AB405";
        String esperado = "0123456789ABCDEF";
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, DESDecrypt.bytesToHex(resultado));
    }

    @Test
    public void testVector4() throws Exception {
        String clave = "AABB09182736CCDD";
        String cipher = "C0B7A8D05F3A829C";
        String esperado = "123456ABCD132536";
        byte[] resultado = descifrar(clave, cipher);
        assertEquals(esperado, DESDecrypt.bytesToHex(resultado));
    }
}