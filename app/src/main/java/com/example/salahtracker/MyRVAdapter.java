package com.example.salahtracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class MyRVAdapter  extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder> {
    private Context context;
    private ArrayList ID,nstName, date, sabak, sabki, manzil;
    Activity activity;

    MyRVAdapter(Activity activity,Context context, ArrayList _ID, ArrayList _stName,ArrayList _date,  ArrayList _sabak,ArrayList _sabki,ArrayList _manzil){
        this.activity=activity;
        this.context = context;
        //Setting values
        this.ID = _ID;
        this.nstName=_stName;
        this.date=_date;
        this.sabak=_sabak;
        this.sabki=_sabki;
        this.manzil=_manzil;
    }

    @NonNull
    @Override
    public MyRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRVAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_txt.setText(String.valueOf(ID.get(position)));
        holder.stName_txt.setText(String.valueOf(nstName.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.sabak_txt.setText(String.valueOf(sabak.get(position)));
        holder.sabki_txt.setText(String.valueOf(sabki.get(position)));
        holder.manzil_txt.setText(String.valueOf(manzil.get(position)));
        holder.single_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,activity_update.class);
                intent.putExtra("id", String.valueOf(ID.get(position)));
                intent.putExtra("stName", String.valueOf(nstName.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("sabak", String.valueOf(sabak.get(position)));
                intent.putExtra("sabki", String.valueOf(sabki.get(position)));
                intent.putExtra("manzil", String.valueOf(manzil.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, stName_txt, date_txt, sabak_txt, sabki_txt, manzil_txt;
        LinearLayout single_item_layout;
        public MyViewHolder(View itemView) {
            super(itemView);

            id_txt=itemView.findViewById(R.id.TVnm_id);
            stName_txt=itemView.findViewById(R.id.TVStudent);
            date_txt= itemView.findViewById(R.id.TVDate);
            sabak_txt= itemView.findViewById(R.id.TVSabak);
            sabki_txt= itemView.findViewById(R.id.TVSabki);
            manzil_txt= itemView.findViewById(R.id.TVManzil);
            single_item_layout=itemView.findViewById(R.id.single_item);
        }
    }
}