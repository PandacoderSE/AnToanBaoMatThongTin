from Crypto.Cipher import DES
from Crypto.Random import get_random_bytes

def pad(text):
    while len(text) % 8 != 0:
        text += b' '  # Padding with spaces to align with block size
    return text

def encrypt(plaintext, key):
    cipher = DES.new(key, DES.MODE_ECB)
    padded_plaintext = pad(plaintext.encode('utf-8'))
    ciphertext = cipher.encrypt(padded_plaintext)
    return ciphertext

def decrypt(ciphertext, key):
    cipher = DES.new(key, DES.MODE_ECB)
    decrypted = cipher.decrypt(ciphertext)
    return decrypted.decode('utf-8').strip()

# Example usage
key = get_random_bytes(8)  # Generate a random 8-byte key
plaintext = "Hello, world! 😀"  # Unicode plaintext
print("Plaintext:", plaintext)

# Encrypt the plaintext
encrypted = encrypt(plaintext, key)
print("Encrypted:", encrypted)

# Decrypt the ciphertext
decrypted = decrypt(encrypted, key)
print("Decrypted:", decrypted)