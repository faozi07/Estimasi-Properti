package com.user.estimasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuUtama extends AppCompatActivity {

    ImageButton imageNotaris, imageHitung, imageRaywhite, imageLaporan, imageProperti, imageSosialisasi, imageAboutUs, imageExit;
    SharedPreferences spLogin;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        action();
    }

    private void init() {
        imageNotaris = findViewById(R.id.notaris);
        imageRaywhite = findViewById(R.id.raywhite);
        imageProperti = findViewById(R.id.properti);
        imageHitung = findViewById(R.id.hitung);
        imageLaporan = findViewById(R.id.laporan);
        imageSosialisasi = findViewById(R.id.sosio);
        imageAboutUs = findViewById(R.id.about);
        imageExit = findViewById(R.id.exit);
        spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
    }

    private void action() {
        imageHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                startActivity(new Intent(MenuUtama.this, Hitung.class));
            }
        });

        imageNotaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                startActivity(new Intent(MenuUtama.this, Notaris.class));
            }
        });
        imageRaywhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.raywhitemetropolitanbekasi.com"));
                startActivity(intent);
            }
        });
        imageProperti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.raywhite.co.id/news/155807pajak-pajak-di-dunia-properti"));
                startActivity(intent);
            }
        });
        imageLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                startActivity(new Intent(MenuUtama.this, Laporan.class));
            }
        });
        imageSosialisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                startActivity(new Intent(MenuUtama.this,SosialisasiActivity.class));
            }
        });

        imageAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                startActivity(new Intent(MenuUtama.this,TentangActivity.class));
            }
        });

        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundBtn.soundBtn(MenuUtama.this);
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Apa benar anda ingin Logout ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk logout!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        SoundBtn.soundBtn(MenuUtama.this);
                        SharedPreferences.Editor loginEditor = spLogin.edit();
                        loginEditor.clear();
                        loginEditor.apply();
                        finish();
                        startActivity(new Intent(MenuUtama.this,Login.class));
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SoundBtn.soundBtn(MenuUtama.this);
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        SoundBtn.soundBtn(MenuUtama.this);
        if (!isExit) {
            Toast.makeText(MenuUtama.this, "Tekan sekali lagi untuk keluar",Toast.LENGTH_LONG).show();
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
