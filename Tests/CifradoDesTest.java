import EJ1.DESTest;
import org.junit.jupiter.api.Test;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import static org.junit.jupiter.api.Assertions.*;

public class CifradoDesTest {

    private byte[] cifrar(String claveHex, String mensajeHex) throws Exception {
        byte[] theKey = DESTest.hexToBytes(claveHex);
        byte[] theMsg = DESTest.hexToBytes(mensajeHex);
        KeySpec ks = new DESKeySpec(theKey);
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        SecretKey ky = kf.generateSecret(ks);
        Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
        cf.init(Cipher.ENCRYPT_MODE, ky);
        return cf.doFinal(theMsg);
    }

    @Test
    void testVector1() throws Exception {
        String clave = "133457799BBCDFF1";
        String mensaje = "0123456789ABCDEF";
        String esperado = "85E813540F0AB405";
        byte[] resultado = cifrar(clave, mensaje);
        assertEquals(esperado, DESTest.bytesToHex(resultado));
    }

    @Test
    void testVector2() throws Exception {
        String clave = "0E329232EA6D0D73";
        String mensaje = "8787878787878787";
        String esperado = "0000000000000000";
        byte[] resultado = cifrar(clave, mensaje);
        assertEquals(esperado, DESTest.bytesToHex(resultado));
    }

    @Test
    void testVector3() throws Exception {
        String clave = "0101010101010101";
        String mensaje = "0000000000000040";
        String esperado = "E07C30D7E4E26E12";
        byte[] resultado = cifrar(clave, mensaje);
        assertEquals(esperado, DESTest.bytesToHex(resultado));
    }

    @Test
    void testVector4() throws Exception {
        String clave = "0123456789ABCDEF";
        String mensaje = "1111111111111111";
        String esperado = "17668DFC7292532D";
        byte[] resultado = cifrar(clave, mensaje);
        assertEquals(esperado, DESTest.bytesToHex(resultado));
    }
}