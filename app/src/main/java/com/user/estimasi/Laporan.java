package com.user.estimasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.user.estimasi.database.EstimasiDB;

import java.util.ArrayList;

public class Laporan extends AppCompatActivity {

    RecyclerView rvLaporan;
    EstimasiDB estimasiDB;
    LaporanAdapter laporanAdapter;
    public static ArrayList<modLaporan> arrLaporan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Laporan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
    }

    private void init() {
        rvLaporan = findViewById(R.id.rvLaporan);

        estimasiDB = new EstimasiDB(Laporan.this);
        estimasiDB.listHitung();

        laporanAdapter = new LaporanAdapter(this, arrLaporan);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvLaporan.setLayoutManager(llm);
        rvLaporan.setHasFixedSize(true);
        rvLaporan.setAdapter(laporanAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        arrLaporan.clear();
        SoundBtn.soundBtn(Laporan.this);
        finish();
    }
}
