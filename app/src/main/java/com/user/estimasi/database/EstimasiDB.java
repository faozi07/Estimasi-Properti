package com.user.estimasi.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.user.estimasi.Hitung;
import com.user.estimasi.Laporan;
import com.user.estimasi.Login;
import com.user.estimasi.Register;
import com.user.estimasi.modLaporan;

import java.util.Date;
import java.util.Random;

public class EstimasiDB extends SQLiteOpenHelper {
    //==============================================================================================
    private static final String DATABASE_NAME = "estimasi.db";
    private static final int DATABASE_VERSION = 1;
    //==============================================================================================

    private static final String TABLE_NAME_USER = "user";
    private static final String ID = "ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";

    private static final String TABLE_NAME_ESTIMASI = "ESTIMASI";
    private static final String id_trx = "id_trx";
    private static final String tg_trx = "tg_trx";
    private static final String no_customer = "no_customer";
    private static final String nama_customer = "nama_customer";
    private static final String sts_ser = "sts_ser";
    private static final String ls_tnh = "ls_tnh";
    private static final String njop = "njop";
    private static final String tg_tbt = "tg_tbt";
    private static final String sts_imb = "sts_imb";
    private static final String harga = "harga";
    private static final String pjk_pjl = "pjk_pjl";
    private static final String pjk_pbl = "pjk_pbl";
    private static final String ajb = "ajb";
    private static final String bn = "bn";
    private static final String cek = "cek";
    private static final String floating = "floating";
    private static final String zona = "zona";
    private static final String validasi = "validasi";
    private static final String pnbp = "pnbp";
    private static final String total = "total";
    private static final String tg_sosio = "tg_sosio";
    private static final String hs_sosio = "hs_sosio";

    public EstimasiDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sqlUser = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + " (" +
                    ID + " TEXT, " +
                    USER_NAME + " TEXT, " +
                    NAME + " TEXT, " +
                    EMAIL + " TEXT, " +
                    PASSWORD + " TEXT)";
            db.execSQL(sqlUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        onCreate(db);

    }

    public void createEstimasi() {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sqlEstimasi = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ESTIMASI + " (" +
                    id_trx + " TEXT, " +
                    tg_trx + " TEXT, " +
                    no_customer + " TEXT, " +
                    nama_customer + " TEXT, " +
                    sts_ser + " TEXT, " +
                    ls_tnh + " TEXT, " +
                    njop + " TEXT, " +
                    tg_tbt + " TEXT, " +
                    sts_imb + " TEXT, " +
                    harga + " TEXT, " +
                    pjk_pjl + " TEXT, " +
                    pjk_pbl + " TEXT, " +
                    ajb + " TEXT, " +
                    bn + " TEXT, " +
                    cek + " TEXT, " +
                    floating + " TEXT, " +
                    zona + " TEXT, " +
                    validasi + " TEXT, " +
                    pnbp + " TEXT, " +
                    total + " TEXT, " +
                    tg_sosio + " TEXT, " +
                    hs_sosio + " TEXT);";
            db.execSQL(sqlEstimasi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            SQLiteDatabase database = getWritableDatabase();
            String updateQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_USER;
            database.execSQL(updateQuery);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertUser(String nama, String email, String username, String password) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME_USER + " (" + ID + ", " + USER_NAME + ", "+NAME+", "+EMAIL+", "+ PASSWORD+") VALUES ('"
                    + String.valueOf(new Random().nextInt()) + "', '" + username + "', '" + nama + "', '"+email+"', '"+password+"');";
            db.execSQL(sql);
            Register.registerSukses = true;
        } catch (Exception exp) {
            exp.printStackTrace();
            Register.registerSukses = false;
        }
    }
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void cekUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT " + USER_NAME +" FROM " + TABLE_NAME_USER +
                " WHERE " + USER_NAME + "='" + username + "'", null);
        if (cursor.moveToFirst()) {
            do {
                Register.isRegistered = cursor.getString(0) != null && username.equals(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
    }
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void login(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER +
                " WHERE " + USER_NAME + "='" + username + "' AND "+PASSWORD+" = '"+password+"'", null);
        if (cursor.moveToFirst()) {
            do {
                Login.isTerdaftar = cursor.getString(1) != null && username.equals(cursor.getString(1));
                Login.username = cursor.getString(1);
                Login.nama = cursor.getString(2);
                Login.email = cursor.getString(3);
            } while (cursor.moveToNext());
        }
        db.close();
    }
    public void insertHitung(String tglTrx, String noCustomer, String namaCustomer, String sttsSertifikat, String luasTanah, String njops, String tglTerbit, String sttsImb,
                             String hargas, String pjkPembeli, String pjkPenjual, String ajbs, String balikNama, String ceks, String floatings, String zonas, String validasis, String pnbps,
                             String totals) {
        try {
            Date dates = new Date();
            SQLiteDatabase db = getWritableDatabase();
            String sqlEstimasi = "INSERT INTO " + TABLE_NAME_ESTIMASI + " (" +
                    id_trx + ", " +
                    tg_trx + ", " +
                    no_customer + ", " +
                    nama_customer + ", " +
                    sts_ser + ", " +
                    ls_tnh + ", " +
                    njop + ", " +
                    tg_tbt + ", " +
                    sts_imb + ", " +
                    harga + ", " +
                    pjk_pjl + ", " +
                    pjk_pbl + ", " +
                    ajb + ", " +
                    bn + ", " +
                    cek + ", " +
                    floating + ", " +
                    zona + ", " +
                    validasi + ", " +
                    pnbp + ", " +
                    total + ", " +
                    tg_sosio + ", " +
                    hs_sosio + ") VALUES ('" +

                    dates.getDate()+dates.getHours()+dates.getMinutes()+dates.getSeconds() + "', '" +
                    tglTrx + "', '" +
                    noCustomer + "', '" +
                    namaCustomer + "', '" +
                    sttsSertifikat + "', '" +
                    luasTanah + "', '" +
                    njops + "', '" +
                    tglTerbit + "', '" +
                    sttsImb + "', '" +
                    hargas + "', '" +
                    pjkPenjual + "', '" +
                    pjkPembeli + "', '" +
                    ajbs + "', '" +
                    balikNama + "', '" +
                    ceks + "', '" +
                    floatings + "', '" +
                    zonas + "', '" +
                    validasis + "', '" +
                    pnbps + "', '" +
                    totals + "', '', '');";
            db.execSQL(sqlEstimasi);
            Hitung.isSaved = true;
        } catch (Exception exp) {
            Hitung.isSaved = false;
            exp.printStackTrace();
        }
    }
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void listHitung() {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_ESTIMASI + ";", null);
        if (cursor.moveToFirst()) {
            do {
                Log.i("listHitungs : ",cursor.getString(0) + " - " + // ID Trx
                        cursor.getString(1) + " - " + // Tgl Trx
                        cursor.getString(2) + " - " + // no customer
                        cursor.getString(3) + " - " + // nama customer
                        cursor.getString(4) + " - " + // status sertifikat
                        cursor.getString(5) + " - " + // luas tanah
                        cursor.getString(6) + " - " + // njop
                        cursor.getString(7) + " - " + // tgl terbit
                        cursor.getString(8) + " - " + // imb
                        cursor.getString(9) + " - " + // harga
                        cursor.getString(10) + " - " + // pajak penjual
                        cursor.getString(11) + " - " + // pajak pembeli
                        cursor.getString(12) + " - " + // ajb
                        cursor.getString(13) + " - " + // balik nama
                        cursor.getString(14) + " - " + // cek
                        cursor.getString(15) + " - " + // floating
                        cursor.getString(16) + " - " + // zona
                        cursor.getString(17) + " - " + // validasi
                        cursor.getString(18) + " - " + // pnbp
                        cursor.getString(19) + " - "); // total
                modLaporan mh = new modLaporan();
                mh.setIdTrx(cursor.getString(0));
                mh.setTglTrx(cursor.getString(1));
                mh.setNoCustomer(cursor.getString(2));
                mh.setNamaCustomer(cursor.getString(3));
                mh.setSttsSertifikat(cursor.getString(4));
                mh.setLuasTanah(cursor.getString(5));
                mh.setNjop(cursor.getString(6));
                mh.setTglTerbit(cursor.getString(7));
                mh.setSttsImb(cursor.getString(8));
                mh.setHarga(cursor.getString(9));
                mh.setPjkPenjual(cursor.getString(10));
                mh.setPjkPembeli(cursor.getString(11));
                mh.setAjb(cursor.getString(12));
                mh.setBalikNama(cursor.getString(13));
                mh.setCek(cursor.getString(14));
                mh.setFloating(cursor.getString(15));
                mh.setZona(cursor.getString(16));
                mh.setValidasi(cursor.getString(17));
                mh.setPnbp(cursor.getString(18));
                mh.setTotal(cursor.getString(19));
                mh.setTglSosialisasi(cursor.getString(20));
                mh.setHslSosialisasi(cursor.getString(21));
                Laporan.arrLaporan.add(mh);
            } while (cursor.moveToNext());
        }
        db.close();
    }
}
