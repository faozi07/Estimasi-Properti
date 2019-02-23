package com.user.estimasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.user.estimasi.database.EstimasiDB;

public class Login extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText editUsername, editPassword;
    public static boolean isTerdaftar = false;
    public static String username = "", nama = "", email = "", password = "";
    private boolean isExit = false;
    EstimasiDB estimasiDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
        }

        estimasiDB = new EstimasiDB(this);
        btnLogin = findViewById(R.id.masuk);
        btnRegister = findViewById(R.id.daftar);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(Login.this);
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                if (editUsername.getText().toString().equals("") || editPassword.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Isi data dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    estimasiDB.login(editUsername.getText().toString(), editPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Login ...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            cekDataUser();
                        }
                    }, 3000);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(Login.this);
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    private void cekDataUser() {
        if (isTerdaftar) {
            finish();
            SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
            SharedPreferences.Editor loginEditor = spLogin.edit();
            loginEditor.putString(StaticVars.SP_LOGIN_USERNAME, username);
            loginEditor.putString(StaticVars.SP_LOGIN_NAMA, nama);
            loginEditor.putString(StaticVars.SP_LOGIN_EMAIL, email);
            loginEditor.putString(StaticVars.SP_LOGIN_PASSWORD, password);
            loginEditor.apply();
            startActivity(new Intent(Login.this, MenuUtama.class));
        } else {
            Toast.makeText(Login.this, "Login gagal, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SoundBtn.soundBtn(Login.this);
        if (!isExit) {
            Toast.makeText(Login.this, "Tekan sekali lagi untuk keluar",Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
        }
        else {
            finish();
        }
    }
}