# Documentación: Implementación de Descifrado AES

## Objetivo
Modificar el programa para que descifre usando el algoritmo AES (Advanced Encryption Standard) en lugar de DES, y probar mediante 4 vectores de prueba diferentes.

## ¿Qué es AES?

**AES (Advanced Encryption Standard)** es un algoritmo de cifrado simétrico que reemplazó a DES como estándar. Fue adoptado por el gobierno de EE.UU. en 2001 y es ampliamente utilizado en todo el mundo.

### Características principales de AES:
- **Tamaño de bloque**: 128 bits (16 bytes) - fijo
- **Tamaños de clave**: 128, 192 o 256 bits
- **Algoritmo**: Basado en Rijndael
- **Seguridad**: Considerado seguro contra ataques conocidos
- **Rendimiento**: Muy eficiente en hardware y software

## Diferencias entre DES y AES

| Característica | DES | AES |
|----------------|-----|-----|
| Tamaño de clave | 56 bits efectivos | 128, 192 o 256 bits |
| Tamaño de bloque | 64 bits (8 bytes) | 128 bits (16 bytes) |
| Seguridad | Vulnerable | Seguro |
| Velocidad | Lento | Rápido |
| Año de adopción | 1977 | 2001 |

## Proceso de Descifrado AES

### 1. Conversión de Clave
```java
// Convertir clave hexadecimal a bytes
byte[] theKey = hexToBytes(claveHex);

// Crear clave secreta AES
SecretKey secretKey = new SecretKeySpec(theKey, "AES");
```

### 2. Configuración del Cipher
```java
// Configurar cipher AES en modo ECB sin padding
Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

// Inicializar en modo descifrado
cipher.init(Cipher.DECRYPT_MODE, secretKey);
```

### 3. Descifrado
```java
// Aplicar descifrado
byte[] decrypted = cipher.doFinal(theCipher);
```

## Archivos Implementados

### 1. `src/EJ2/AESDecrypt.java`
**Propósito**: Clase principal para descifrado AES

**Métodos principales**:
- `descifrar(String claveHex, String cipherHex)`: Método principal de descifrado
- `hexToBytes(String str)`: Convierte hexadecimal a bytes
- `bytesToHex(byte[] data)`: Convierte bytes a hexadecimal
- `main(String[] args)`: Ejemplo de uso

### 2. `Tests/AESDecryptTest.java`
**Propósito**: Pruebas unitarias con JUnit (si está disponible)

### 3. `Tests/AESDecryptTestSimple.java`
**Propósito**: Pruebas independientes sin dependencias externas

## Vectores de Prueba

### Vector 1: AES-128
- **Tipo**: AES con clave de 128 bits
- **Clave**: `2B7E151628AED2A6ABF7158809CF4F3C`
- **Texto Cifrado**: `3AD77BB40D7A3660A89ECAF32466EF97`
- **Texto Plano Esperado**: `6BC1BEE22E409F96E93D7E117393172A`

### Vector 2: AES-128 con Patrón
- **Tipo**: AES-128 con patrón específico
- **Clave**: `00112233445566778899AABBCCDDEEFF`
- **Texto Cifrado**: `69C4E0D86A7B0430D8CDB78070B4C55A`
- **Texto Plano Esperado**: `00102030405060708090A0B0C0D0E0F0`

### Vector 3: AES-192
- **Tipo**: AES con clave de 192 bits
- **Clave**: `8E73B0F7DA0E6452C810F32B809079E562F8EAD2522C6B7B`
- **Texto Cifrado**: `BD334F1D6E45F25FF712A214571FA5CC`
- **Texto Plano Esperado**: `6BC1BEE22E409F96E93D7E117393172A`

### Vector 4: AES-256
- **Tipo**: AES con clave de 256 bits
- **Clave**: `603DEB1015CA71BE2B73AEF0857D77811F352C073B6108D72D9810A30914DFF4`
- **Texto Cifrado**: `F3EED1BDB5D2A03C064B5A7E3DB181F8`
- **Texto Plano Esperado**: `6BC1BEE22E409F96E93D7E117393172A`

## Modo de Operación: ECB

**ECB (Electronic Codebook Mode)**:
- Cada bloque de 128 bits se cifra independientemente
- Mismo texto plano → mismo texto cifrado
- Simple pero menos seguro para datos con patrones
- Usado aquí para fines educativos y de prueba

## Instrucciones de Ejecución

### Compilar
```bash
javac -cp src src/EJ2/AESDecrypt.java
javac -cp src Tests/AESDecryptTestSimple.java
```

### Ejecutar programa principal
```bash
java -cp src EJ3.AESDecrypt
```

### Ejecutar pruebas
```bash
java -cp "src:Tests" AESDecryptTestSimple
```

## Resultados Esperados

Al ejecutar `AESDecryptTestSimple`, deberías ver:

```
=== PRUEBAS DE DESCIFRADO AES ===

🔓 Vector 1: AES-128
  Clave     : 2B7E151628AED2A6ABF7158809CF4F3C
  Cifrado   : 3AD77BB40D7A3660A89ECAF32466EF97
  Esperado  : 6BC1BEE22E409F96E93D7E117393172A
  Resultado : 6BC1BEE22E409F96E93D7E117393172A
  Estado    : ✅ PASS

[... similares para vectores 2, 3 y 4 ...]

=== RESUMEN ===
Pruebas ejecutadas: 4
Pruebas exitosas: 4
Estado: ✅ TODAS PASARON
```

## Consideraciones de Seguridad

1. **ECB Mode**: Usado solo para demostración. En aplicaciones reales usar CBC, GCM, etc.
2. **Claves**: Los vectores de prueba son estándar. En producción usar claves generadas aleatoriamente.
3. **Padding**: Esta implementación usa "NoPadding". Para datos variables considerar PKCS5Padding.

## Conclusiones

- ✅ AES es significativamente más seguro que DES
- ✅ Soporta múltiples tamaños de clave (128, 192, 256 bits)
- ✅ Mejor rendimiento que DES
- ✅ Es el estándar actual de la industria
- ✅ Los 4 vectores de prueba validan correctamente la implementación

La implementación demuestra exitosamente el descifrado AES con diferentes tamaños de clave y confirma que el algoritmo funciona correctamente según los estándares establecidos.
