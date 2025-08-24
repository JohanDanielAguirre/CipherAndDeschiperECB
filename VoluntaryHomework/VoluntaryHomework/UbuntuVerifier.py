import hashlib


def verificar_iso(ruta_iso, ruta_hash):
    nombre_iso = ruta_iso.split("/")[-1]
    hash_calculado = calcular_hash_iso(ruta_iso)
    hash_oficial = leer_hash_oficial(ruta_hash, nombre_iso)

    if hash_oficial is None:
        print("No se proporciono un hash oficial para esta ISO.")
        return False

    if hash_calculado == hash_oficial:
        print("La ISO es auténtica. Los hashes coinciden. Proceda con la instalación.")
        return True
    else:
        print("La ISO no coincide con el hash oficial. OJO PUEDE SER MALICIOSA.")
        return False


def calcular_hash_iso(ruta_iso):
    h = hashlib.new("sha256")
    with open(ruta_iso, "rb") as f:
        for bloque in iter(lambda: f.read(4096), b""):
            h.update(bloque)
    return h.hexdigest()


def leer_hash_oficial(ruta_hash, nombre_iso):
    with open(ruta_hash, "r") as f:
        for linea in f:
            if nombre_iso in linea:
                return linea.split()[0]
    return None


if __name__ == "__main__":
    ruta_iso = ""  # Ejemplo: "ubuntu-20.04.2-desktop-amd64.iso"
    ruta_hash = ""  # Ejemplo: "SHA256SUMS"
    verificar_iso(ruta_iso, ruta_hash)
