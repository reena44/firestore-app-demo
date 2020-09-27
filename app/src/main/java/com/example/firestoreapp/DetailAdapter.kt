package com.example.firestoreapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_detail.view.*


class DetailAdapter(
    var detailActivity: DetailActivity,
   var  productList: ArrayList<Detail>
) :RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_detail, parent, false)
                );

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product: Detail = productList[position]

        holder.itemView.textview_name.text = product.name
        holder.itemView.textview_brand.text= (product.age)
        holder.itemView.textview_desc.text=(product.address)

    }


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }


}
