def caesar_cipher_unicode(text, shift):
    result = ''
    for char in text:
        # Get the Unicode code point of the character
        code_point = ord(char)
        # Only shift if the character is within the basic Latin alphabet
        if 0x0021 <= code_point <= 0x007E or 0xFF21 <= code_point <= 0xFF5E:
            # Determine whether the character is within the basic Latin uppercase or lowercase range
            if 0x0041 <= code_point <= 0x005A or 0xFF21 <= code_point <= 0xFF3A:
                base = 0x0041  # Unicode code point of 'A'
            else:
                base = 0x0061  # Unicode code point of 'a'
            # Apply the shift and handle wrapping around the alphabet
            shifted_code_point = (code_point - base + shift) % 26 + base
            # Convert the shifted code point back to a character
            result += chr(shifted_code_point)
        else:
            # Leave non-alphabetic characters unchanged
            result += char
    return result

# Example usage
plaintext = "thế thì anh cũng chịu thua em rồi"
shift = 3
encrypted_text = caesar_cipher_unicode(plaintext, shift)
print("Encrypted:", encrypted_text)

decrypted_text = caesar_cipher_unicode(encrypted_text, -shift)  # Decrypt by shifting in reverse
print("Decrypted:", decrypted_text)