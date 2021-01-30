package com.user.estimasi;

/*
 * Created by faozi on 01/02/18.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.user.estimasi.database.EstimasiDB;

import java.util.ArrayList;


public class SosialisasiAdapter extends RecyclerView.Adapter {

    public static ArrayList<modLaporan> items;
    Activity act;
    modLaporan mrt;

    private final int VIEW_ITEM = 1;
    private int lastPosition = -1;

    SosialisasiAdapter(Activity acts, ArrayList<modLaporan> data) {
        act = acts;
        items = data;
    }

    @Override
    public int getItemViewType(int position) {
        int VIEW_PROG = 0;
        return items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public static class BrandViewHolder extends RecyclerView.ViewHolder {

        TextView tIdTrx, tTglTrx, tNamaCust, tSttsSert, tLuasTanah, tHarga;
        Button btnUbah, btnHapus;
        CardView cardView;

        BrandViewHolder(View v) {
            super(v);

            tIdTrx = v.findViewById(R.id.tIdTrx);
            tTglTrx = v.findViewById(R.id.ttglTrx);
            tNamaCust = v.findViewById(R.id.tNamaCust);
            tSttsSert = v.findViewById(R.id.tStsSer);
            tLuasTanah = v.findViewById(R.id.tLuasTanah);
            tHarga = v.findViewById(R.id.tHarga);
            btnUbah = v.findViewById(R.id.btnUbah);
            btnHapus = v.findViewById(R.id.btnHapus);
            cardView = v.findViewById(R.id.card_view);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_sosialisasi, parent, false);

            vh = new SosialisasiAdapter.BrandViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof SosialisasiAdapter.BrandViewHolder) {

            mrt = items.get(position);

            ((BrandViewHolder) holder).tIdTrx.setText(mrt.getIdTrx());
            ((BrandViewHolder) holder).tTglTrx.setText(mrt.getTglTrx());
            ((BrandViewHolder) holder).tNamaCust.setText(mrt.getNamaCustomer());
            ((BrandViewHolder) holder).tSttsSert.setText(mrt.getSttsSertifikat());
            ((BrandViewHolder) holder).tLuasTanah.setText(mrt.getLuasTanah());
            ((BrandViewHolder) holder).tHarga.setText(mrt.getHarga());

            ((BrandViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SoundBtn.soundBtn(act);
                    DetailLaporan.ml = items.get(position);
                    DetailLaporan.isLaporan = false;
                    act.startActivity(new Intent(act, DetailLaporan.class));
                }
            });

            ((BrandViewHolder) holder).btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SoundBtn.soundBtn(act);
                    showDialog(items.get(position).getIdTrx());
                }
            });

            ((BrandViewHolder) holder).btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SoundBtn.soundBtn(act);
                    EditSosialisasi.ml = items.get(position);
                    act.startActivity(new Intent(act, EditSosialisasi.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void showDialog(final String idTrx){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(act);
        alertDialogBuilder.setTitle("Apa benar anda ingin hapus data ini ?");
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        SoundBtn.soundBtn(act);
                        EstimasiDB estimasiDB = new EstimasiDB(act);
                        estimasiDB.hapusSosialisasi(idTrx);
                        if (EditSosialisasi.isDeleteSuccess) {
                            Sosialisasi.setAdapter();
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SoundBtn.soundBtn(act);
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
