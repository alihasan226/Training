package com.usl.usl.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.databinding.RowDsectionBinding

class DSectionAdapter(var context: Context?,var hashMap: HashMap<Int?, String?>,var listener: (View?, Int?, String?) -> Unit) : RecyclerView.Adapter<DSectionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DSectionAdapter.ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.row_dsection,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    override fun onBindViewHolder(holder: DSectionAdapter.ViewHolder, position: Int) {
        val keys: Array<Int?> = hashMap.keys.toTypedArray()
        holder.binding!!.tvpos.text = keys[position].toString()
        holder.binding!!.tvvalue.text = hashMap.get(keys[position]).toString()

        holder.binding!!.ivDelete.setOnClickListener { view: View? ->
            listener(view,position,"Delete")
        }

        holder.binding!!.ivEdit.setOnClickListener { view: View? ->
            listener(view,position,"Edit")
        }
    }


    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        var binding:RowDsectionBinding?
        init {
            binding=DataBindingUtil.bind(view)
        }
    }

}