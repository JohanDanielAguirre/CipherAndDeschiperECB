Johan Daniel Aguirre Arias - A00395554

Alejandro Amu Garcia - A00395686

Gerson de Jesus Hurtado Borja - A0039

1. Probar el algoritmo DES mediante 4 vectores de prueba diferentes y documentar
   las respuestas.
2. Deben cambiar en el algoritmo que ahora está cifrando, para que descifre, y
   realizar 4 pruebas también los vectores de prueba entregados. Indicar que parte
   del programa cambió.
3. Deben modificar el programa para que decifre el algoritmo AES, documentar el
   proceso y probar mediante 4 vectores de prueba diferentes.

# Algoritmo DES: Explicación y Pruebas Completas

## Funcionamiento del algoritmo DES

El algoritmo DES (Data Encryption Standard) es un cifrador simétrico de bloques que opera sobre bloques de 64 bits y utiliza una clave de 56 bits efectivos (representada en 64 bits, donde 8 bits son de paridad). DES realiza 16 rondas de sustitución y permutación sobre el bloque de datos, usando subclaves derivadas de la clave principal.

### Características principales:
- **Tamaño de bloque:** 64 bits
- **Tamaño de clave:** 56 bits efectivos (64 bits con paridad)
- **Rondas:** 16 iteraciones
- **Modo utilizado:** ECB (Electronic Codebook) sin padding

### Proceso de cifrado/descifrado:
- **Cifrado:** Toma un bloque de texto plano y una clave, aplica permutaciones iniciales, 16 rondas de operaciones Feistel, y una permutación final.
- **Descifrado:** Utiliza el mismo algoritmo pero con las subclaves aplicadas en orden inverso para recuperar el texto original.

## Pruebas de Cifrado (DESEncryptTest)

Las pruebas de cifrado verifican que el algoritmo produce el ciphertext esperado para vectores de prueba conocidos. Cada prueba sigue este proceso:

1. Convierte la clave y mensaje de hexadecimal a bytes usando `DESEncrypt.hexToBytes()`
2. Crea una especificación de clave DES y genera la clave secreta
3. Inicializa el cifrador en modo `ENCRYPT_MODE` con algoritmo `DES/ECB/NoPadding`
4. Cifra el mensaje y compara con el resultado esperado

### Vectores de prueba de cifrado:

| Vector | Clave | Mensaje | Ciphertext Esperado |
|--------|-------|---------|-------------------|
| 1 | `133457799BBCDFF1` | `0123456789ABCDEF` | `85E813540F0AB405` |
| 2 | `0E329232EA6D0D73` | `8787878787878787` | `0000000000000000` |
| 3 | `0101010101010101` | `0000000000000040` | `E07C30D7E4E26E12` |
| 4 | `0123456789ABCDEF` | `1111111111111111` | `17668DFC7292532D` |

### Análisis de vectores de cifrado:
- **Vector 1:** Caso estándar con clave y mensaje típicos
- **Vector 2:** Caso especial donde el resultado es todo ceros (weak key scenario)
- **Vector 3:** Prueba con clave débil (`0101010101010101`) y mensaje con un solo bit activo
- **Vector 4:** Validación con mensaje de patrón repetitivo

## Pruebas de Descifrado (DESDescryptTest)

Las pruebas de descifrado validan que el algoritmo recupera correctamente el texto original a partir del ciphertext. El proceso es:

1. Convierte la clave y ciphertext de hexadecimal a bytes usando `DESDecrypt.hexToBytes()`
2. Crea la especificación de clave DES y genera la clave secreta
3. Inicializa el cifrador en modo `DECRYPT_MODE` con algoritmo `DES/ECB/NoPadding`
4. Descifra el ciphertext y compara con el mensaje esperado

### Vectores de prueba de descifrado:

| Vector | Clave | Ciphertext | Mensaje Esperado |
|--------|-------|------------|------------------|
| 1 | `0101010101010101` | `DD7F121CA5015619` | `4000000000000000` |
| 2 | `38627974656B6579` | `7CF45E129445D451` | `6D6573736167652E` |
| 3 | `133457799BBCDFF1` | `85E813540F0AB405` | `0123456789ABCDEF` |
| 4 | `AABB09182736CCDD` | `C0B7A8D05F3A829C` | `123456ABCD132536` |

### Análisis de vectores de descifrado:
- **Vector 1:** Descifrado con clave débil, validando el manejo correcto de estas claves especiales
- **Vector 2:** Clave en formato ASCII (`"bytekey"`) convertida a hexadecimal
- **Vector 3:** Verificación cruzada con el vector 1 de cifrado (misma clave y resultado)
- **Vector 4:** Caso con clave y mensaje personalizados para validar compatibilidad

## Métodos de utilidad

Ambas clases (`DESEncrypt` y `DESDecrypt`) incluyen métodos auxiliares esenciales:

### `hexToBytes(String str)`
- Convierte cadenas hexadecimales a arrays de bytes
- Maneja validaciones de entrada (null, longitud mínima)
- Procesa pares de caracteres hexadecimales

### `bytesToHex(byte[] data)`
- Convierte arrays de bytes a representación hexadecimal
- Formatea correctamente con ceros iniciales cuando es necesario
- Retorna resultado en mayúsculas para consistencia

## Validaciones realizadas

Las pruebas aseguran:

1. **Correctitud del algoritmo:** Los resultados coinciden con vectores de prueba estándar
2. **Reversibilidad:** El descifrado recupera exactamente el mensaje original
3. **Manejo de casos especiales:** Claves débiles y patrones especiales
4. **Interoperabilidad:** Compatibilidad con implementaciones estándar de DES
5. **Robustez:** Manejo correcto de diferentes tipos de datos de entrada

## Conclusión

El conjunto completo de pruebas garantiza que la implementación de DES en Java cumple con los estándares de la industria, validando tanto el proceso de cifrado como el de descifrado a través de vectores de prueba diversos y representativos. Esto asegura la confiabilidad y correcta funcionalidad del algoritmo en diferentes escenarios de uso.