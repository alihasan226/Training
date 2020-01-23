package com.usl.usl.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.activity.HistoryActivity
import com.usl.usl.activity.SheetViewActivity
import com.usl.usl.databinding.RowHistoryBinding
import com.usl.usl.network.response.usersheets.Sheet
import com.usl.usl.utils.Preferences
import java.util.*

class HistoryAdapter(var historyActivity: HistoryActivity,var historyList: ArrayList<Sheet>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view=LayoutInflater.from(historyActivity).inflate(R.layout.row_history,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.binding!!.tvGameName.text = historyList[position].game_name.toString()
        holder.binding!!.tvmTotalAmount.text = ":      " + String.format("%.1f",historyList[position].m_total?.toDouble().toString())
        holder.binding!!.tvdTotalAmount.text = ":      " + String.format("%.1f",historyList[position].d_total?.toDouble().toString())
        holder.binding!!.tvhTotalAmount.text = ":      " + String.format("%.1f",historyList[position].h_total?.toDouble().toString())
        holder.binding!!.tvTotalAmount.text = ":      " + String.format("%.1f",historyList[position].grand_total?.toDouble().toString())

        val gameDate: List<String> = historyList[position].updated_at?.substring(0, 10)!!.split("-")
        var newGameDate = ""
        for (i in gameDate.indices.reversed()) {
            newGameDate = if (i == gameDate.size - 1) {
                newGameDate + gameDate[i]
            } else {
                newGameDate + "-" + gameDate[i]
            }
        }

        newGameDate = "$newGameDate   " + java.lang.String.valueOf(historyList[position].updated_at?.substring(11,16))
        holder.binding!!.tvGameDate.text = newGameDate

        holder.binding!!.rlGame.setOnClickListener { view: View? ->
            Preferences(historyActivity).getInstance(historyActivity)!!.storeSheet(historyList[position].cell_amounts, "SHEET")
            val bundle = Bundle()
            bundle.putString("GameName", historyList[position].game_name.toString())
            val intent = Intent(historyActivity, SheetViewActivity::class.java)
            intent.putExtras(bundle)
            historyActivity.startActivity(intent)
        }
    }

    class ViewHolder(item:View) : RecyclerView.ViewHolder(item) {
        var binding:RowHistoryBinding?
        init {
            binding=DataBindingUtil.bind(item)
        }
    }
}