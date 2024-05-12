# Python program to illustrate ElGamal encryption

import random 
from math import pow
import baseCal

a = random.randint(2, 10)
key = ' aáàạảãăắằặẳẵâấầậẩẫbcdđeéẹẻẽêếềệểễfghiíìịỉĩjklmnoóòọỏõôốồộổỗơớờợởỡpqrstuúùụủũưứừựửữvwxyýỳỵỷỹAÁÀẠẢÃĂẮẰẶẲẴÂẤẦẬẨẪBCDĐEÉẸẺẼÊẾỀỆỂỄFGHIÍÌỊỈĨJKLMNOÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠPQRSTUÚÙỤỦŨƯỨỪỰỬỮVWXYÝỲỴỶỸ0123456789`~!@#$%^&*()'


def gcd(a, b):
	if a < b:
		return gcd(b, a)
	elif a % b == 0:
		return b
	else:
		return gcd(b, a % b)

# Modular exponentiation
def power(a, b, c):
	x = 1
	y = a

	while b > 0:
		if b % 2 != 0:
			x = (x * y) % c
		y = (y * y) % c
		b = int(b / 2)

	return x % c

# Generating large prime numbers
def randomPrime(q):

	key = random.randint(pow(10, 2), q)
	while gcd(q, key) != 1:
		key = random.randint(pow(10, 2), q)

	return key

def encrypt(M, p, a, y):
	k = random.randint(1, p-1)
	Ki = power(y, k, p)

	c1 = power(a, k, p)
	c2 = ((Ki % p) * (M % p)) % p 
	return c1, c2

def decrypt(c1, c2, x, p):
	Kii = power(c1, x, p)
	K_invert = baseCal.modInverse(Kii, p)
	Mi = c2 * K_invert % p 
	return Mi 

def encoder(message, p, a, y):
	global key
	result = []
	# Calling the encrypting function in encoding function
 
	for letter in message:
		try:
			m = key.index(letter)
			c1, c2 = encrypt(m, p, a, y)
			result.append(c1)
			result.append(c2)
		except ValueError:
			result.append(m)
			result.append(m)

	return result

def decoder(encoded, x, p):
	global key
	result = []
	s = ''
	# Calling the decrypting function decoding function
	# for c1, c2 in encoded:
	# 	try:
	# 		m = decrypt(c1, c2, x, p)
	# 		result.append(key[m])
	# 	except ValueError:
	# 		result.append(key[c1])

	i = 0
	n = len(encoded)

	while True:
		c1 = encoded[i]
		c2 = encoded[i + 1]
		i = i + 2

		try:
			m = decrypt(c1, c2, x, p)
			result.append(key[m])
		except ValueError:
			result.append(key[c1])

		if i >= n: break
	return result

# MARK: - MAIN
def main():

	message = 'Tôi yêu Việt Nam'
	print("Original Message :", message)

	p = random.randint(pow(10, 2), pow(10, 5))
	p = randomPrime(p)
	x = random.randint(0, p)
	y = power(a, x, p)

	coded = encoder(message, p, a, y)

	print("Initial message:")
	print(message)
	
	print("\nThe encoded message(encrypted by public key):")
	print(''.join(str(p) for p in coded))
    
	print("\nThe decoded message(decrypted by public key): ")
	print(''.join(str(p) for p in decoder(coded, x, p)))




if __name__ == '__main__':
	main()
