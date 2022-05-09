package com.areeb.adminpatholab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.areeb.adminpatholab.Model.HomeModel;
import com.areeb.adminpatholab.R;
import com.areeb.adminpatholab.clickListner.HomeListner;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<HomeModel> homelist ;
    Context context;
    HomeListner homeListner;

    public HomeAdapter(ArrayList<HomeModel> homelist, Context context, HomeListner homeListner) {
        this.homelist = homelist;
        this.context = context;
        this.homeListner = homeListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slots,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel list = homelist.get(position);
        holder.name.setText(list.getPatientName());

        holder.cardlistner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                homeListner.onHomeItemClicked(homelist.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CardView cardlistner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patientName);
            cardlistner = itemView.findViewById(R.id.cardListner);

        }
    }
}
