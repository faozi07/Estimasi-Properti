package com.user.estimasi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Hitung extends AppCompatActivity {

    EditText tTglTrx, tNama, tStatusSert, tLuasTanah, tNjop, tTglTerbit, tStatusImb, tHarga;
    TextView iTglTrx, iNama, iStatusSert, iLuasTanah, iNjop, iTglTerbit, iStatusImb, iHarga;
    Button btnHitung;
    LinearLayout layHitung, laySimpan;
    Calendar myCalendar;
    String tgl = "";
    private boolean istgltrx = true, isLayHitung = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("Hitung");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
        action();
    }

    private void init() {
//       ======================================== layout hitung ====================================
        tTglTrx = findViewById(R.id.ttglTrx);
        tNama = findViewById(R.id.tNamaCust);
        tStatusSert = findViewById(R.id.tStsSer);
        tLuasTanah = findViewById(R.id.tLuasTanah);
        tNjop = findViewById(R.id.tnilaiNjop);
        tTglTerbit = findViewById(R.id.tTanggalTbt);
        tStatusImb = findViewById(R.id.tStsImb);
        tHarga = findViewById(R.id.tHarga);
        btnHitung = findViewById(R.id.btnHitung);
        myCalendar = Calendar.getInstance();
        layHitung = findViewById(R.id.layHitung);
        laySimpan = findViewById(R.id.laySimpan);
        layHitung.setVisibility(View.VISIBLE);
        btnHitung.setVisibility(View.VISIBLE);
        laySimpan.setVisibility(View.GONE);
//       ======================================== layout simpan ====================================
        iTglTrx = findViewById(R.id.iTglTrx);
        iNama = findViewById(R.id.iNamaCustomer);
        iStatusSert = findViewById(R.id.iStatusSert);
        iLuasTanah = findViewById(R.id.iLuasTanah);
        iNjop = findViewById(R.id.iNjop);
        iTglTerbit = findViewById(R.id.iTglTerbit);
        iStatusImb = findViewById(R.id.iStatusImb);
        iHarga = findViewById(R.id.iHarga);
    }

    private void setItemText() {
        iTglTrx.setText(tTglTrx.getText().toString());
        iNama.setText(tNama.getText().toString());
        iStatusSert.setText(tStatusSert.getText().toString());
        iLuasTanah.setText(tLuasTanah.getText().toString()+" m2");
        iNjop.setText(tNjop.getText().toString());
        iTglTerbit.setText(tTglTerbit.getText().toString());
        iStatusImb.setText(tStatusImb.getText().toString());
        iHarga.setText("Rp. "+String.format("%,.2f", Double.parseDouble(tHarga.getText().toString())));
    }

    private void action() {
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
                if (istgltrx) {
                    tTglTrx.setText(tgl);
                }
                else {
                    tTglTerbit.setText(tgl);
                }
            }
        };

        tTglTrx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                DatePickerDialog mDate = new DatePickerDialog(Hitung.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDate.show();
                istgltrx = true;
            }
        });


        tTglTerbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                DatePickerDialog mDate = new DatePickerDialog(Hitung.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate.show();
                istgltrx = false;
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLayHitung = false;
                layHitung.setVisibility(View.GONE);
                btnHitung.setVisibility(View.GONE);
                laySimpan.setVisibility(View.VISIBLE);
                setItemText();
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
        SoundBtn.soundBtn(Hitung.this);
        if (!isLayHitung) {
            isLayHitung = true;
            layHitung.setVisibility(View.VISIBLE);
            btnHitung.setVisibility(View.VISIBLE);
            laySimpan.setVisibility(View.GONE);
        }
        else {
            finish();
        }
    }
}
