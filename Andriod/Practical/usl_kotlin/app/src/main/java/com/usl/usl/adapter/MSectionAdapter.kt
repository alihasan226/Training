package com.usl.usl.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.databinding.RowMsectionBinding

class MSectionAdapter(var mSectionFragment: Context?, var hashMap: HashMap<Int?, String?>, var listener: (View?, Int?, String?) -> Unit) : RecyclerView.Adapter<MSectionAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MSectionAdapter.ViewHolder {
        var view=LayoutInflater.from(mSectionFragment).inflate(R.layout.row_msection,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    override fun onBindViewHolder(holder: MSectionAdapter.ViewHolder, position: Int) {
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding:RowMsectionBinding?
        init {
            binding=DataBindingUtil.bind(view)
        }
    }
}