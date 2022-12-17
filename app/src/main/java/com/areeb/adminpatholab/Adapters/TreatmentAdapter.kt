package com.areeb.adminpatholab.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.areeb.adminpatholab.Model.TreatmentModel
import com.areeb.adminpatholab.R

class TreatmentAdapter(val context: Context, val treatmentList:ArrayList<TreatmentModel>) : RecyclerView.Adapter<TreatmentAdapter.treatmentViewHolder>() {


  class treatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val TreatmentName :TextView = itemView.findViewById(R.id.treatmentName);


  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): treatmentViewHolder {
        val view :View  = LayoutInflater.from(context).inflate(R.layout.treatment_item,parent,false);
        return  treatmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: treatmentViewHolder, position: Int) {
        holder.TreatmentName.setText(treatmentList[position].name)
    }

    override fun getItemCount(): Int {
        return treatmentList.size
    }
}