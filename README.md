# r2d2

R2D2 Android uses **Android Keystore** to store passwords and other sensitive information for different API versions in an encrypted form.  

## Gradle
    compile 'com.moldedbits.r2d2:r2d2:1.0.1'

## Maven
    <dependency>
      <groupId>com.moldedbits.r2d2</groupId>
      <artifactId>r2d2</artifactId>
      <version>1.0.1</version>
      <type>pom</type>
    </dependency>


## Implementaion
The android KeyStore handles the tasks like random key generation and securely storing them. It acts like a secure container.  
Now depending on the API version, the sensitive information is handled accordingly.  

For **android versions 23 and higher**, *KeyGenParameterSpec API* is used. Random AES keys are generated using the API which can be used for encrypting and decrypting the data. It uses same key for encryption and decryption. The data to be secured is encrypted using the key retrieved from the KeyStore, and then the encrypted data is stored in Shared Preferences. When the secret data needs to be retrieved, the encrypted data from the Preferences can be decrypted to plain text using the key stored securely in the KeyStore.  

For **android versions 18 and higher and Pre M**, *KeyPairGeneratorSpec API* is used. This generates a Public/Private key pair just like RSA and is added to the KeyStore securely. Encrypting a block of text is performed with the Public key of the Key Pair, whereas decryption is performed using the Private Key of the Key Pair by retrieving the keys from the KeyStore.  

For **android versions 16 and higher and pre 18**, we are simply encrypting and decrypting the data. This is done by hashing the data with the hash value generated using the *SHA-1* hash function. This cipher text is stored in the Preferences. To retrieve the secret information, the cipher text is converted to plain text using the hash value.  

## Usage
R2D2 does all the hard work for you in background and is super easy to implement. 

* For Encryption : All you need to do is just call the following method and the rest it will handle. 

```
void setPassword(String password) {
        String encrypted = r2d2.encryptData(password);
        if (encrypted != null && !encrypted.equalsIgnoreCase("")) {
            password = encrypted;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }
```  

* For Decryption : Similar to the way encryption works, decryption also works works by calling the following method.  

```
String getPassword() {
        String password = preferences.getString(KEY_PASSWORD, null);
        String decrypted = r2d2.decryptData(password);
        if (decrypted != null && !decrypted.equalsIgnoreCase("")) {
            password = decrypted;
        }
        return password;
    }
```
Please feel free to contribute or report issues.
