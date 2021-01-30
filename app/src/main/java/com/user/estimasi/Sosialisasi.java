package com.user.estimasi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.user.estimasi.database.EstimasiDB;

import java.util.ArrayList;

public class Sosialisasi extends AppCompatActivity {

    public static RecyclerView rvLaporan;
    public static EstimasiDB estimasiDB;
    public static SosialisasiAdapter sosialisasiAdapter;
    public static ArrayList<modLaporan> arrSosialisasi = new ArrayList<>();
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sosisalisasi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
    }

    private void init() {
        rvLaporan = findViewById(R.id.rvLaporan);
        activity = Sosialisasi.this;

        setAdapter();
    }

    public static void setAdapter() {
        if (arrSosialisasi.size() > 0) {
            arrSosialisasi.clear();
        }
        estimasiDB = new EstimasiDB(activity);
        estimasiDB.listHitung();

        sosialisasiAdapter = new SosialisasiAdapter(activity, arrSosialisasi);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        rvLaporan.setLayoutManager(llm);
        rvLaporan.setHasFixedSize(true);
        rvLaporan.setAdapter(sosialisasiAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        arrSosialisasi.clear();
        SoundBtn.soundBtn(Sosialisasi.this);
        finish();
    }
}
