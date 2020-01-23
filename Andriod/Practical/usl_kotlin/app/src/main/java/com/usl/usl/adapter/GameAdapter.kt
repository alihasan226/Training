package com.usl.usl.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.activity.AddAmountActivity
import com.usl.usl.activity.LandingPageActivity
import com.usl.usl.databinding.RowGamesBinding
import com.usl.usl.utils.Preferences

class GameAdapter(var landingPageActivity: LandingPageActivity,var tempgameDate: ArrayList<String>,var tempgameName: ArrayList<String>,var tempgameId: ArrayList<Int>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_games, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.binding!!.tvGameDate.setText(tempgameDate.get(position))
        holder.binding!!.tvGameName.setText(tempgameName.get(position).substring(15,tempgameName.get(position).length))
        holder.binding!!.tvOpenTime.setText(tempgameName.get(position).substring(0, 5))
        holder.binding!!.tvCloseTime.setText(tempgameName.get(position).substring(9, 14))


        holder.binding!!.llPlayGame.setOnClickListener { view: View? ->
            Preferences(landingPageActivity).getInstance(landingPageActivity)?.setGameId(tempgameId.get(position))
            landingPageActivity.startActivity(Intent(landingPageActivity, AddAmountActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return tempgameDate.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: RowGamesBinding?
        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }

}