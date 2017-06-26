package com.moldedbits.r2d2sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.moldedbits.rahul.keystore.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText etUsername;

    @Bind(R.id.et_password)
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_encrypt)
    public void onEncrypt() {
        LocalStorage.getInstance().setUsername(etUsername.getText().toString());
        LocalStorage.getInstance().setPassword(etPassword.getText().toString());
    }

    @OnClick(R.id.btn_decrypt)
    public void onDecrypt() {
        String password = LocalStorage.getInstance().getPassword();
    }
}