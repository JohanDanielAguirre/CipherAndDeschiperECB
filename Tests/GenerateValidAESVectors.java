import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Generador de vectores de prueba AES válidos
 */
public class GenerateValidAESVectors {
    
    public static void main(String[] args) {
        try {
            System.out.println("=== GENERANDO VECTORES AES VÁLIDOS ===\n");
            
            // Generar vectores válidos cifrando textos conocidos
            generateVector("AES-128 Test 1", 
                         "2B7E151628AED2A6ABF7158809CF4F3C",
                         "48656C6C6F20576F726C64212020202020"); // "Hello World!    " (16 bytes)
            
            generateVector("AES-128 Pattern", 
                         "00112233445566778899AABBCCDDEEFF",
                         "00102030405060708090A0B0C0D0E0F0");
            
            generateVector("AES-192 Test",
                         "8E73B0F7DA0E6452C810F32B809079E562F8EAD2522C6B7B",
                         "6BC1BEE22E409F96E93D7E117393172A");
            
            generateVector("AES-256 Test",
                         "603DEB1015CA71BE2B73AEF0857D77811F352C073B6108D72D9810A30914DFF4",
                         "6BC1BEE22E409F96E93D7E117393172A");
                         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void generateVector(String nombre, String claveHex, String textoPlanoHex) throws Exception {
        System.out.println("🔐 " + nombre);
        
        byte[] clave = hexToBytes(claveHex);
        byte[] textoPlano = hexToBytes(textoPlanoHex);
        
        // Cifrar
        SecretKeySpec secretKey = new SecretKeySpec(clave, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] textoCifrado = cipher.doFinal(textoPlano);
        
        // Verificar descifrando
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] verificacion = cipher.doFinal(textoCifrado);
        
        System.out.println("Clave        : " + claveHex);
        System.out.println("Texto Plano  : " + textoPlanoHex);
        System.out.println("Texto Cifrado: " + bytesToHex(textoCifrado));
        System.out.println("Verificación : " + bytesToHex(verificacion));
        System.out.println("Válido       : " + textoPlanoHex.equals(bytesToHex(verificacion)));
        System.out.println();
    }
    
    public static byte[] hexToBytes(String str) {
        if (str == null) return null;
        if (str.length() < 2) return null;
        
        int len = str.length() / 2;
        byte[] buffer = new byte[len];
        for (int i = 0; i < len; i++) {
            buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
        }
        return buffer;
    }
    
    public static String bytesToHex(byte[] data) {
        if (data == null) return null;
        
        StringBuilder str = new StringBuilder();
        for (byte b : data) {
            if ((b & 0xFF) < 16) str.append("0");
            str.append(Integer.toHexString(b & 0xFF));
        }
        return str.toString().toUpperCase();
    }
}
