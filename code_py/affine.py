def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

def mod_inverse(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('Modular inverse does not exist')
    else:
        return x % m

def affine_cipher_unicode(text, key_a, key_b):
    result = ''
    for char in text:
        # Get the Unicode code point of the character
        code_point = ord(char)
        # Only encrypt characters within the basic Latin alphabet
        if 0x0021 <= code_point <= 0x007E or 0xFF21 <= code_point <= 0xFF5E:
            # Apply the affine transformation
            encrypted_code_point = (key_a * (code_point - 0x0020) + key_b) % 95 + 0x0020
            # Convert the transformed code point back to a character
            result += chr(encrypted_code_point)
        else:
            # Leave non-alphabetic characters unchanged
            result += char
    return result

def affine_decipher_unicode(text, key_a, key_b):
    result = ''
    mod_inv_a = mod_inverse(key_a, 95)
    for char in text:
        # Get the Unicode code point of the character
        code_point = ord(char)
        # Only decrypt characters within the basic Latin alphabet
        if 0x0021 <= code_point <= 0x007E or 0xFF21 <= code_point <= 0xFF5E:
            # Apply the decryption transformation
            decrypted_code_point = (mod_inv_a * (code_point - 0x0020 - key_b)) % 95 + 0x0020
            # Convert the decrypted code point back to a character
            result += chr(decrypted_code_point)
        else:
            # Leave non-alphabetic characters unchanged
            result += char
    return result

# Example usage
plaintext = "giờ này thì anh cũng chịu"
key_a = 3
key_b = 7
encrypted_text = affine_cipher_unicode(plaintext, key_a, key_b)
print("Encrypted:", encrypted_text)

decrypted_text = affine_decipher_unicode(encrypted_text, key_a, key_b)
print("Decrypted:", decrypted_text)