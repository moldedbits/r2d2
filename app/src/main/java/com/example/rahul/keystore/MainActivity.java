package com.example.rahul.keystore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_encrypt)
    Button btnEncrypt;
    @Bind(R.id.btn_decrypt)
    Button btnDecrypt;
    @Bind(R.id.tv_encrypted)
    TextView tvEncrypted;
    @Bind(R.id.tv_decrypted)
    TextView tvDecrypted;

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
        tvDecrypted.setText(password);
    }
}