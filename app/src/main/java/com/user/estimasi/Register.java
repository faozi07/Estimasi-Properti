package com.user.estimasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.user.estimasi.database.EstimasiDB;

import java.util.Random;

public class Register extends AppCompatActivity {

    EditText tNama, tEmail, tUsername, tPassword;
    Button btnRegist;
    public static boolean isRegistered = false, registerSukses = false;
    EstimasiDB estimasiDB = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Register");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
        setAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRegistered = false;
        registerSukses = false;
    }

    private void init() {
        tNama = findViewById(R.id.tnama);
        tEmail = findViewById(R.id.temail);
        tUsername = findViewById(R.id.tusername);
        tPassword = findViewById(R.id.tpassword);
        btnRegist = findViewById(R.id.btnRegister);

        estimasiDB = new EstimasiDB(this);
        final SQLiteDatabase sqlDb = estimasiDB.getWritableDatabase();
        estimasiDB.onCreate(sqlDb);
    }

    private void setAction() {
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(Register.this);
                if (tNama.getText().toString().equals("") || tEmail.getText().toString().equals("") || tUsername.getText().toString().equals("") || tPassword.getText().toString().equals("")) {
                    Toast.makeText(Register.this,"Periksa kembali inputan Anda",Toast.LENGTH_SHORT).show();
                }
                else {
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }

                    estimasiDB.cekUser(tUsername.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMessage("Mendaftar ...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isRegistered) {
                                Toast.makeText(Register.this, "User sudah terdaftar, gunakan data user lain", Toast.LENGTH_LONG).show();
                            } else {
                                estimasiDB.insertUser(tNama.getText().toString(), tEmail.getText().toString(), tUsername.getText().toString(), tPassword.getText().toString());
                            }
                        }
                    }, 2000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            if (registerSukses) {
                                finish();
                                Toast.makeText(Register.this, "Berhasil Daftar, silahkan login", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Register.this, "Gagal Daftar, silahkan coba lagi", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 4000);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SoundBtn.soundBtn(Register.this);
        finish();
    }
}
