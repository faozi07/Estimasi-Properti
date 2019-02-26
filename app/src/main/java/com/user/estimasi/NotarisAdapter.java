package com.user.estimasi;

/*
 * Created by faozi on 01/02/18.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class NotarisAdapter extends RecyclerView.Adapter {

    public static Activity activity;
    public static ArrayList<modNotaris> items;
    modNotaris mrt;

    private final int VIEW_ITEM = 1;
    private int lastPosition = -1;

    private AlertDialog.Builder dialogs;
    private AlertDialog dialogListDetail;

    NotarisAdapter(Activity act, ArrayList<modNotaris> data) {
        activity = act;
        items = data;
        dialogs = new AlertDialog.Builder(activity);
        dialogListDetail = dialogs.create();
    }

    @Override
    public int getItemViewType(int position) {
        int VIEW_PROG = 0;
        return items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public static class BrandViewHolder extends RecyclerView.ViewHolder {

        TextView tNoUrut, tNama, tAlamat, tNotelp;
        CardView cardView;

        BrandViewHolder(View v) {
            super(v);

            tNoUrut = v.findViewById(R.id.noUrut);
            tNama = v.findViewById(R.id.namaNotaris);
            tAlamat = v.findViewById(R.id.alamatNotaris);
            tNotelp = v.findViewById(R.id.notelpNotaris);
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
                    R.layout.item_notaris, parent, false);

            vh = new NotarisAdapter.BrandViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof NotarisAdapter.BrandViewHolder) {

            mrt = items.get(position);

            ((BrandViewHolder) holder).tNoUrut.setText(String.valueOf(position+1)+".");
            ((BrandViewHolder) holder).tNama.setText(mrt.getNama());
            ((BrandViewHolder) holder).tAlamat.setText(mrt.getAlamat());
            ((BrandViewHolder) holder).tNotelp.setText(mrt.getNotelp());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
