package com.usl.usl.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.activity.AccountActivity
import com.usl.usl.databinding.RowAccountBinding
import com.usl.usl.network.response.account.Account
import com.usl.usl.utils.Preferences

class AccountAdapter(var accountActivity: AccountActivity,var account: List<Account>) : RecyclerView.Adapter<AccountAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder {
        val view=LayoutInflater.from(accountActivity).inflate(R.layout.row_account,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return account.size
    }

    override fun onBindViewHolder(holder: AccountAdapter.ViewHolder, position: Int) {
        var date = ""
        val newDate: List<String> = account[position].created_at?.substring(0, 10)!!.split("-")
        for (i in newDate.indices.reversed()) {
            date = if (i == newDate.size - 1) {
                date + newDate[i]
            } else {
                date + "/" + newDate[i]
            }
        }

        date = "$date   " + java.lang.String.valueOf(account[position].created_at?.substring(11,16))
        holder.binding!!.tvDate.text = date
        holder.binding!!.tvGameName.setText(account[position].game_name)
        if (account[position].from_user_id === com.usl.usl.utils.Preferences(accountActivity).getInstance(accountActivity)?.getId()) {
            if (account[position].from_user_name?.substring(0,6)=="Player") {
                holder.binding!!.tvFromUser.text = "Me"
            } else {
                holder.binding!!.tvFromUser.setText(account[position].from_user_name)
            }
        } else {
            holder.binding!!.tvFromUser.setText(java.lang.String.valueOf(account[position].from_user_name))
        }

        if (account[position].from_user_id === com.usl.usl.utils.Preferences(accountActivity).getInstance(accountActivity)?.getId()) {
            holder.binding!!.tvToUser.setText(java.lang.String.valueOf(account[position].to_user_name))
        } else {
            if (account[position].to_user_name?.substring(0, 6)=="Player") {
                holder.binding!!.tvToUser.text = "Me"
            } else {
                holder.binding!!.tvToUser.setText(account[position].to_user_name)
            }
        }

        holder.binding!!.tvCommentUser.text = account[position].comment

        if (account[position].from_user_id == Preferences(accountActivity).getInstance(accountActivity)?.getId()) {
            holder.binding!!.tvAmountTotal.text = account[position].amount.toString()
            holder.binding!!.tvAmountTotal.setTextColor(Color.RED)
        } else if (account[position].to_user_id == Preferences(accountActivity).getInstance(accountActivity)?.getId()) {
            holder.binding!!.tvAmountTotal.text = account[position].amount.toString()
            holder.binding!!.tvAmountTotal.setTextColor(Color.parseColor("#51A020"))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding:RowAccountBinding?
        init {
            binding=DataBindingUtil.bind(view)
        }
    }
}