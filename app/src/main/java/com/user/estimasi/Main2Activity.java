package com.user.estimasi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    ImageButton imageNotaris, imageHitung, imageRaywhite, imageLaporan, imageProperti, imageSosialisasi, imageAboutUs, imageExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageNotaris = findViewById(R.id.notaris);
        imageRaywhite = findViewById(R.id.raywhite);
        imageProperti = findViewById(R.id.properti);
        imageHitung = findViewById(R.id.hitung);
        imageLaporan = findViewById(R.id.laporan);
        imageSosialisasi = findViewById(R.id.sosio);
        imageAboutUs = findViewById(R.id.about);
        imageExit = findViewById(R.id.exit);


        imageHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,HitungActivity.class));
            }
        });

        imageNotaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,NotarisActivity.class));
            }
        });
        imageRaywhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                startActivity(new Intent(Main2Activity.this,LaporanActivity.class));
            }
        });
        imageSosialisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,SosialisasiActivity.class));
            }
        });


        imageAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,TentangActivity.class));
            }
        });
        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
