package com.user.estimasi;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.user.estimasi.database.EstimasiDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Hitung extends AppCompatActivity {

    EditText tTglTrx, tNama, tStatusSert, tLuasTanah, tNjop, tTglTerbit, tStatusImb, tHarga;
    TextView iTglTrx, iNama, iStatusSert, iLuasTanah, iNjop, iTglTerbit, iStatusImb, iHarga, iPajakPenjual, iPajakPembeli, iAjb, iCek, iBalikNama, iFloating, iZona, iValidasi, iTotal, iPnbp;
    Button btnHitung, btnSimpan, btnBatal;
    LinearLayout layHitung, laySimpan, layBtnSimpan;
    Calendar myCalendar;
    String tgl = "";
    EstimasiDB estimasiDB;
    private boolean istgltrx = true, isLayHitung = true;
    public static boolean isSaved = false;

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
//       ======================================== layout simpan ====================================
        iTglTrx = findViewById(R.id.iTglTrx);
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
        layBtnSimpan = findViewById(R.id.layBtnSimpan);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBatal = findViewById(R.id.btnBatal);
//       ===========================================================================================
        layHitung.setVisibility(View.VISIBLE);
        btnHitung.setVisibility(View.VISIBLE);
        laySimpan.setVisibility(View.GONE);
        layBtnSimpan.setVisibility(View.GONE);

        estimasiDB = new EstimasiDB(this);
        estimasiDB.createEstimasi();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void setItemText() {
        double pajakPembeli = (Double.parseDouble(tHarga.getText().toString()) - 60000) * 5/100;
        double pajakPenjual = Double.parseDouble(tHarga.getText().toString()) * 25/1000;
        double ajb = Double.parseDouble(tHarga.getText().toString()) /100;
        double balikNama = 3000000;
        double cek = 300000;
        double floating = 500000;
        double zona = 300000;
        double validasi = 500000;
        double pnbp;
        if (Integer.parseInt(tLuasTanah.getText().toString()) <= 400) {
            pnbp = 3000000;
        }
        else {
            pnbp = 6000000;
        }

        double total = pajakPembeli+pajakPenjual+ajb+balikNama+cek+floating+zona+validasi+pnbp;

        iTglTrx.setText(tTglTrx.getText().toString());
        iNama.setText(tNama.getText().toString());
        iStatusSert.setText(tStatusSert.getText().toString());
        iLuasTanah.setText(tLuasTanah.getText().toString()+" m2");
        iNjop.setText(tNjop.getText().toString());
        iTglTerbit.setText(tTglTerbit.getText().toString());
        iStatusImb.setText(tStatusImb.getText().toString());
        iHarga.setText("Rp. "+String.format("%,.2f", Double.parseDouble(tHarga.getText().toString())));
        iPajakPenjual.setText("Rp. "+String.format("%,.2f", pajakPenjual));
        iPajakPembeli.setText("Rp. "+String.format("%,.2f", pajakPembeli));
        iAjb.setText("Rp. "+String.format("%,.2f", ajb));
        iBalikNama.setText("Rp. "+String.format("%,.2f", balikNama));
        iCek.setText("Rp. "+String.format("%,.2f", cek));
        iFloating.setText("Rp. "+String.format("%,.2f", floating));
        iZona.setText("Rp. "+String.format("%,.2f", zona));
        iValidasi.setText("Rp. "+String.format("%,.2f", validasi));
        iPnbp.setText("Rp. "+String.format("%,.2f", pnbp));
        iTotal.setText("Rp. "+String.format("%,.2f", total));
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

        tHarga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                String stok = s.toString();
//                String harga = String.format("%,.2f", Double.parseDouble(stok));
//                String hargaz = harga.replace(",","").substring(0,harga.length()-3);
//                if (!stok.equals("")) {
//                    tHarga.setText(hargaz);
//                    tHarga.setSelection(tHarga.getText().length());
//                }
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                if (tTglTrx.getText().toString().equals("") || tNama.getText().toString().equals("") || tStatusSert.getText().toString().equals("") || tLuasTanah.getText().toString().equals("") ||
                    tNjop.getText().toString().equals("") || tTglTerbit.getText().toString().equals("") || tStatusImb.getText().toString().equals("") || tHarga.getText().toString().equals("")) {
                    Toast.makeText(Hitung.this,"Inputan Anda tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    isLayHitung = false;
                    layHitung.setVisibility(View.GONE);
                    btnHitung.setVisibility(View.GONE);
                    laySimpan.setVisibility(View.VISIBLE);
                    layBtnSimpan.setVisibility(View.VISIBLE);
                    setItemText();
                }
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pDialog = new ProgressDialog(Hitung.this);
                pDialog.setMessage("Menyimpan data ...");
                pDialog.setCancelable(false);
                pDialog.show();
                Date dates = new Date();
                estimasiDB.insertHitung(iTglTrx.getText().toString(), String.valueOf(dates.getTime()), iNama.getText().toString(), iStatusSert.getText().toString(), iLuasTanah.getText().toString(),
                        iNjop.getText().toString(), tTglTerbit.getText().toString(), iStatusImb.getText().toString(), iHarga.getText().toString(), iPajakPembeli.getText().toString(),
                        iPajakPenjual.getText().toString(), iAjb.getText().toString(), iBalikNama.getText().toString(), iCek.getText().toString(), iFloating.getText().toString(), iZona.getText().toString(),
                        iValidasi.getText().toString(), iPnbp.getText().toString(), iTotal.getText().toString());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pDialog.dismiss();
                        if (isSaved) {
                            Toast.makeText(Hitung.this,"Berhasil Simpan Data",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            Toast.makeText(Hitung.this,"Gagal Simpan Data, Silakan coba kembali",Toast.LENGTH_LONG).show();
                        }
                    }
                },2000);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLayHitung = true;
                layHitung.setVisibility(View.VISIBLE);
                btnHitung.setVisibility(View.VISIBLE);
                laySimpan.setVisibility(View.GONE);
                layBtnSimpan.setVisibility(View.GONE);
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
        isSaved = false;
        if (!isLayHitung) {
            isLayHitung = true;
            layHitung.setVisibility(View.VISIBLE);
            btnHitung.setVisibility(View.VISIBLE);
            laySimpan.setVisibility(View.GONE);
            layBtnSimpan.setVisibility(View.GONE);
        }
        else {
            finish();
        }
    }
}
