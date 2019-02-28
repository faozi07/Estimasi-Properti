package com.user.estimasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailLaporan extends AppCompatActivity {

    TextView iTglTrx, iNama, iStatusSert, iLuasTanah, iNjop, iTglTerbit, iStatusImb, iHarga, iPajakPenjual, iPajakPembeli, iAjb, iCek, iBalikNama, iFloating, iZona, iValidasi, iTotal,
            iPnbp, iNoCustomer, iTglSos, iHasilSos, iIdTrx;
    public static modLaporan ml = new modLaporan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("Detail Laporan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
        action();
    }

    private void init() {
        iIdTrx = findViewById(R.id.iIdTrx);
        iTglTrx = findViewById(R.id.iTglTrx);
        iNoCustomer = findViewById(R.id.iNomorCustomer);
        iNama = findViewById(R.id.iNamaCustomer);
        iStatusSert = findViewById(R.id.iStatusSert);
        iLuasTanah = findViewById(R.id.iLuasTanah);
        iNjop = findViewById(R.id.iNjop);
        iTglTerbit = findViewById(R.id.iTglTerbit);
        iStatusImb = findViewById(R.id.iStatusImb);
        iHarga = findViewById(R.id.iHarga);
        iPajakPenjual = findViewById(R.id.iPajakPenjual);
        iPajakPembeli = findViewById(R.id.iPajakPembeli);
        iAjb = findViewById(R.id.iAjb);
        iCek = findViewById(R.id.iCek);
        iBalikNama = findViewById(R.id.iBalikNama);
        iFloating = findViewById(R.id.iFloating);
        iZona = findViewById(R.id.iZona);
        iValidasi = findViewById(R.id.iValidasi);
        iPnbp = findViewById(R.id.iPnbp);
        iTotal = findViewById(R.id.iTotal);
        iTglSos = findViewById(R.id.iTglSosial);
        iHasilSos = findViewById(R.id.iHasilSos);
    }

    private void action() {
        iIdTrx.setText(ml.getIdTrx());
        iTglTrx.setText(ml.getTglTrx());
        iNoCustomer.setText(ml.getNoCustomer());
        iNama.setText(ml.getNamaCustomer());
        iStatusSert.setText(ml.getSttsSertifikat());
        iLuasTanah.setText(ml.getLuasTanah());
        iNjop.setText(ml.getNjop());
        iTglTerbit.setText(ml.getTglTerbit());
        iStatusImb.setText(ml.getSttsImb());
        iHarga.setText(ml.getHarga());
        iPajakPenjual.setText(ml.getPjkPenjual());
        iPajakPembeli.setText(ml.getPjkPembeli());
        iAjb.setText(ml.getAjb());
        iBalikNama.setText(ml.getBalikNama());
        iCek.setText(ml.getCek());
        iFloating.setText(ml.getFloating());
        iZona.setText(ml.getZona());
        iValidasi.setText(ml.getValidasi());
        iPnbp.setText(ml.getPnbp());
        iTotal.setText(ml.getTotal());

        if (ml.getTglSosialisasi().equals("")) {
            iTglSos.setText("-");
        }
        else {
            iTglSos.setText(ml.getTglSosialisasi());
        }

        if (ml.getHslSosialisasi().equals("")) {
            iHasilSos.setText("-");
        }
        else {
            iHasilSos.setText(ml.getHslSosialisasi());
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        SoundBtn.soundBtn(DetailLaporan.this);
        finish();
    }
}
