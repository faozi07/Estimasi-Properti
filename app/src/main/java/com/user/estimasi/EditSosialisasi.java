package com.user.estimasi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.user.estimasi.database.EstimasiDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditSosialisasi extends AppCompatActivity {

    EditText eTglSosialisasi, eHslSosialisasi;
    TextView idTrx, iTglTrx, iNoCustomer, iNama, iTotal;
    Button btnUbah;
    EstimasiDB estimasiDB;
    Calendar myCalendar;
    String tgl = "";

    public static modLaporan ml = new modLaporan();
    public static boolean isEditSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sosialisasi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ubah Sosialisasi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        init();
        action();
    }

    private void init() {
        myCalendar = Calendar.getInstance();
        estimasiDB = new EstimasiDB(EditSosialisasi.this);

        idTrx = findViewById(R.id.iIdTrx);
        iTglTrx = findViewById(R.id.iTglTrx);
        iNoCustomer = findViewById(R.id.iNomorCustomer);
        iNama = findViewById(R.id.iNamaCustomer);
        iTotal = findViewById(R.id.iTotal);

        eTglSosialisasi = findViewById(R.id.tTglSos);
        eHslSosialisasi = findViewById(R.id.tHslSos);

        btnUbah = findViewById(R.id.btnUbah);
    }

    private void action() {
        idTrx.setText(ml.getIdTrx());
        iTglTrx.setText(ml.getTglTrx());
        iNoCustomer.setText(ml.getNoCustomer());
        iNama.setText(ml.getNamaCustomer());
        iTotal.setText(ml.getTotal());

        eTglSosialisasi.setText(ml.getTglSosialisasi());
        eHslSosialisasi.setText(ml.getHslSosialisasi());

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tgl = sdf.format(myCalendar.getTime());
                eTglSosialisasi.setText(tgl);
            }
        };

        eTglSosialisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                DatePickerDialog mDate = new DatePickerDialog(EditSosialisasi.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate.show();
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pdLoading = new ProgressDialog(EditSosialisasi.this);
                pdLoading.setMessage("Mengubah data ...");
                pdLoading.show();

                estimasiDB.editSosialisasi(ml.getIdTrx(), eTglSosialisasi.getText().toString(), eHslSosialisasi.getText().toString());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pdLoading.dismiss();
                        if (isEditSuccess) {
                            Toast.makeText(EditSosialisasi.this,"Berhasil ubah data",Toast.LENGTH_LONG).show();
                            if (Sosialisasi.arrSosialisasi.size() > 0) {
                                Sosialisasi.arrSosialisasi.clear();
                            }
                            estimasiDB.listHitung();
                            finish();
                        }
                        else {
                            Toast.makeText(EditSosialisasi.this,"Gagal ubah data",Toast.LENGTH_LONG).show();
                        }
                    }
                },2000);
            }
        });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        SoundBtn.soundBtn(EditSosialisasi.this);
        if (Sosialisasi.arrSosialisasi.size() > 0) {
            Sosialisasi.arrSosialisasi.clear();
        }
        estimasiDB.listHitung();
        finish();
    }
}
