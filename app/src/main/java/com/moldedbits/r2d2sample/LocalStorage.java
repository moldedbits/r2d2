package com.moldedbits.r2d2sample;

import android.content.Context;
import android.content.SharedPreferences;

import com.moldedbits.r2d2.R2d2;

/**
 * Created by Rahul
 * on 22-06-2017.
 */

class LocalStorage {

    private static final String PREFS_NAME = "com.moldedbits.r2d2sample.SharedPrefs";
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";
    private static final String KEY_ALIAS = "key_alias";
    private final SharedPreferences preferences;
    private final R2d2 r2d2;

    private static final LocalStorage instance = new LocalStorage();

    static LocalStorage getInstance() {
        return instance;
    }

    /**
     * Constructor Which create a shared preference in which data can be stored and also an
     * object of R2D2 class.
     */
    private LocalStorage() {
        preferences = BaseApplication.getInstance().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        r2d2 = new R2d2(BaseApplication.getInstance().getApplicationContext(), KEY_ALIAS);
    }


    /**
     * returns the username from Shared Preference.
     */
    public String getUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    /**
     * Stores the username in the Shared Preference.
     */
    void setUsername(String username) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    /**
     * returns the password in decrypted form i.e. in actual form from the Shared Preference.
     */
    String getPassword() {
        String password = preferences.getString(KEY_PASSWORD, null);
        String decrypted = r2d2.decryptData(password);
        if (decrypted != null && !decrypted.equalsIgnoreCase("")) {
            password = decrypted;
        }
        return password;
    }

    /**
     * Stores the password in encrypted form in the Shared Preference.
     */
    @SuppressWarnings("PMD.AvoidReassigningParameters")
    void setPassword(String password) {
        String encrypted = r2d2.encryptData(password);
        if (encrypted != null && !encrypted.equalsIgnoreCase("")) {
            password = encrypted;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    /**
     * Clears data stored in Shared Preference.
     */
    void clear() {
        preferences.edit().clear().apply();
    }
}