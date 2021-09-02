import random

chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^&*()"
num_chars = int(input('Length of password: '))

new_pass = random.sample(chars, num_chars)

password = "".join(new_pass)

print(password)

