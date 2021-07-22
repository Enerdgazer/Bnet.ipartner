package com.example.bnetipartner.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnetipartner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import API.Entry;

public class AdapterForDataOutput extends RecyclerView.Adapter<AdapterForDataOutput.ViewHolder> {
    private static RecyclerViewClickListener itemListener;
    ArrayList<Entry> listEntry = new ArrayList<>();


    public AdapterForDataOutput(RecyclerViewClickListener itemListener) {

        AdapterForDataOutput.itemListener = itemListener;
    }

    public void setData(List<Entry> data) {
        listEntry.clear();
        listEntry.addAll(data);
        Log.d("q", "setData: " + listEntry.size());
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return listEntry.size();
    }


    @Override
    public AdapterForDataOutput.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_data, parent, false);

        return new ViewHolder(cv);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textBody;
        TextView textDa;
        TextView textDm;

        CardView cardView = holder.cardView;
        textBody = cardView.findViewById(R.id.text);
        textDa = cardView.findViewById(R.id.da);
        textDm = cardView.findViewById(R.id.dm);
        String formatDa = formatData(listEntry.get(position).getDa());

        textBody.setText(listEntry.get(position).getBody());
        textDa.setText("da: " + formatDa);
        textDm.setText("dm: ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.recyclerViewListClicked(listEntry.get(position).getBody(), formatData(listEntry.get(position).getDa()));
            }
        });
    }

    private String formatData(String da) {
        Long q = Long.parseLong(da) * 1000;
        System.out.println(q);
        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(q));
        return dateString;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;


        public ViewHolder(CardView v) {
            super(v);
            this.cardView = v;


        }


    }
}





