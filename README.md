# r2d2

R2D2 Android uses Android Keystore to store passwords and other sensitive information for different API versions in an encrypted form.  

## Motivation

R2D2 contains different implementations for different versions of android. As per the android version, this delegates the work to the corresponding functions for handling the operations like key generation, encryption and decrytption of the data.  
*Now there is no need for separate implementations for different API levels.*

##
