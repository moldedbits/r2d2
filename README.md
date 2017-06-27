# r2d2

R2D2 Android uses **Android Keystore** to store passwords and other sensitive information for different API versions in an encrypted form.  

## Motivation

R2D2 contains different implementations for different versions of android. As per the android version, this delegates the work to the corresponding functions for handling the operations like key generation, encryption and decrytption of the data.  
*Now there is no need for separate implementations for different API levels.*

## Implementaion
The android KeyStore handles the tasks like random key generation and securely storing them. It acts like a secure container.  
Now depending on the API version, the sensitive information is handled accordingly.  

For **android versions 23 and higher**, *KeyGenParameterSpec API* is used. Random AES keys are generated using the API which can be used for encrypting and decrypting the data. It uses same key for encryption and decryption.The data to be secured is encrypted using the key retrieved from the KeyStrore, and then the encrypted data is stored in Shared Preferences.When the secret data needs to be retrieved, the encrypted data from the Preferences can be decrypted to plain text using the key stored securely in the KeyStore.  

For **android versions 18 and higher and Pre M**, *KeyPairGeneratorSpec API* is used.This generates a Public/Private key pair just like RSA and is added to the KeyStore securely.Encrypting a block of text is performed with the Public key of the Key Pair, whereas decryption is performed using the Private Key of the Key Pair by retrieving the keys from the KeyStore.
