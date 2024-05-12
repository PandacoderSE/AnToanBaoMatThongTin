
import random
import math

# A set will be the collection of prime numbers,
# where we can select random primes p and q
prime = set()

public_key = None
private_key = None
n = None

# We will run the function only once to fill the set of
# prime numbers
def primefiller():
    # Method used to fill the primes set is Sieve of
    # Eratosthenes (a method to collect prime numbers)
    seive = [True] * 250
    seive[0] = False
    seive[1] = False
    for i in range(2, 250):
        for j in range(i * 2, 250, i):
            seive[j] = False

    # Filling the prime numbers
    for i in range(len(seive)):
        if seive[i]:
            prime.add(i)

#a^b mod c
def power(a, b, c):
    x = 1
    y = a

    while b > 0:
        if b % 2 != 0:
            x = (x * y) % c;
        y = (y * y) % c
        b = int(b / 2)

    return x % c
# Picking a random prime number and erasing that prime
# number from list because p!=q
def pickrandomprime():
    global prime
    k = random.randint(0, len(prime) - 1)
    it = iter(prime)
    for _ in range(k):
        next(it)

    ret = next(it)
    prime.remove(ret)
    return ret


def setkeys():
    global public_key, private_key, n
    p = pickrandomprime()  # First prime number
    q = pickrandomprime()  # Second prime number

    n = p * q
    fi = (p - 1) * (q - 1)

    e = 2
    while True:
        if math.gcd(e, fi) == 1:
            break
        e += 1
    print (n);
    print (p, " " , q)
    # d = (k*Φ(n) + 1) / e for some integer k
    public_key = e #khóa công khai e
    #tìm d sao cho (e*d) mod fi = 1; thay vì tính d = e^-1 mod fi thì làm như này cũng được

    d = 2
    while True:
        if (d * e) % fi == 1:
            break
        d += 1



    private_key = d #khóa bí mật d


# To encrypt the given number
# Mã hóa từng kí tự một sau khi chuyển về dạng thứ tự
def encrypt(message):
    global public_key, n
    e = public_key
    #Mã hóa theo công thức c(encrypted_text) = (m(message)^e) mod n

    encrypted_text = 1
    while e > 0:
        encrypted_text *= message
        encrypted_text %= n
        e -= 1

    return encrypted_text


# To decrypt the given number
def decrypt(encrypted_text):
    global private_key, n
    d = private_key
    #Giải mã hóa theo công thức d(decrypted_text) = (c(encrypted_text)^d) mod n
    decrypted = 1
    while d > 0:
        decrypted *= encrypted_text
        decrypted %= n
        d -= 1
    return decrypted


# First converting each character to its ASCII value and
# then encoding it then decoding the number to get the
# ASCII and converting it to character
def encoder(message):
    encoded = []
    # Calling the encrypting function in encoding function
    for letter in message:
        encoded.append(encrypt(ord(letter)))
    return encoded


def decoder(encoded):
    s = ''
    # Calling the decrypting function decoding function
    for num in encoded:
        s += chr(decrypt(num))
    return s


if __name__ == '__main__':
    primefiller()
    setkeys()
    message = "Trường đại học công nghiệp Hà Nội"
    # Uncomment below for manual input
    # message = input("Enter the message\n")
    # Calling the encoding function
    coded = encoder(message)

    print("Initial message:")
    print(message)
    print("\n\nThe encoded message(encrypted by public key)\n")
    print(''.join(str(p) for p in coded))
    print("\n\nThe decoded message(decrypted by public key)\n")
    print(''.join(str(p) for p in decoder(coded)))