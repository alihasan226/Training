package com.usl.usl.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.activity.MatchedBetActivity
import com.usl.usl.activity.SheetViewActivity
import com.usl.usl.databinding.RowMatchedbetBinding
import com.usl.usl.network.response.usersheets.Sheet
import com.usl.usl.utils.Preferences

class MatchedBetAdapter(var matchedBetActivity: MatchedBetActivity,var saveGameList: List<Sheet>) : RecyclerView.Adapter<MatchedBetAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchedBetAdapter.ViewHolder {
        var view=LayoutInflater.from(matchedBetActivity).inflate(R.layout.row_matchedbet,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return saveGameList.size
    }

    override fun onBindViewHolder(holder: MatchedBetAdapter.ViewHolder, position: Int) {
        holder.binding!!.tvGameName.text = saveGameList[position].game_name.toString()
        holder.binding!!.tvmTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].m_total?.toDouble())
        holder.binding!!.tvdTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].d_total?.toDouble())
        holder.binding!!.tvhTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].h_total?.toDouble())
        holder.binding!!.tvTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].grand_total?.toDouble())

        val gameDate: List<String> = saveGameList[position].created_at?.substring(0, 10)!!.split("-")
        var newGameDate = ""
        for (i in gameDate.indices.reversed()) {
            newGameDate = if (i == gameDate.size - 1) {
                newGameDate + gameDate[i]
            } else {
                newGameDate + "-" + gameDate[i]
            }
        }
        newGameDate = "$newGameDate   " +saveGameList[position].created_at?.substring(11,16)
        holder.binding!!.tvGameDate.text = newGameDate

        holder.binding!!.rlGame.setOnClickListener { view: View? ->
            Preferences(matchedBetActivity).getInstance(matchedBetActivity)?.storeSheet(saveGameList[position].cell_amounts, "SHEET")
            val bundle = Bundle()
            bundle.putString("GameName", saveGameList[position].game_name.toString())
            val intent = Intent(matchedBetActivity, SheetViewActivity::class.java)
            intent.putExtras(bundle)
            matchedBetActivity.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding:RowMatchedbetBinding?
        init {
            binding=DataBindingUtil.bind(view)
        }
    }

}