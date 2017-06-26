package com.moldedbits.r2d2sample;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rahul
 * on 22-06-2017.
 */

class LocalStorage {

    private static final String PREFS_NAME = "com.directenergy.SharedPrefs";
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";
    private final SharedPreferences preferences;
    private final KeyStoreUtils keyStoreUtils;

    private static final LocalStorage instance = new LocalStorage();

    static LocalStorage getInstance() {
        return instance;
    }

    /**
     * Constructor Which create a shared preference in which data can be stored and also an object of KeyStoreUtils class.
     */
    private LocalStorage() {
        preferences = BaseApplication.getInstance().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        keyStoreUtils = new KeyStoreUtils();
    }


    /**
     * returns the username from the memory.
     *
     * @return
     */
    public String getUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    /**
     * Stores the username in the memory.
     *
     * @param username
     */
    public void setUsername(String username) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    /**
     * returns the password in decrypted form i.e. in actual form from the memory.
     *
     * @return
     */
    String getPassword() {
        String password = preferences.getString(KEY_PASSWORD, null);
        String decrypted = keyStoreUtils.decryptData(password);
        if (decrypted != null && !decrypted.equalsIgnoreCase("")) {
            password = decrypted;
        }
        return password;
    }

    /**
     * Stores the password in encrypted form in the memory.
     *
     * @param password
     */
    @SuppressWarnings("PMD.AvoidReassigningParameters")
    void setPassword(String password) {
        String encrypted = keyStoreUtils.encryptData(password);
        if (encrypted != null && !encrypted.equalsIgnoreCase("")) {
            password = encrypted;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    /**
     * Clears data stored in Shared Perference
     */
    void clear() {
        preferences.edit().clear().apply();
    }
}