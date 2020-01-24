package com.usl.usl.adapter

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.usl.usl.R
import com.usl.usl.activity.AddAmountActivity
import com.usl.usl.activity.SheetViewActivity
import com.usl.usl.activity.SheetsActivity
import com.usl.usl.databinding.RowSheetdataBinding
import com.usl.usl.network.response.usersheets.Sheet
import com.usl.usl.utils.Preferences
import java.util.*

class SheetsDataAdapter(var sheetsActivity: SheetsActivity, var saveGameList: ArrayList<Sheet>, var listener: (View,Int,String) -> Unit) : RecyclerView.Adapter<SheetsDataAdapter.ViewHolder>() {

    var mHashMap = HashMap<Int, String>()
    var dHashMap = HashMap<Int, String>()
    var hHashMap = HashMap<Int, String>()
    var hashMap =  HashMap<String, String>()
    var sheetValue = ArrayList<String>()
    var sheetPosition = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetsDataAdapter.ViewHolder {
        var view=LayoutInflater.from(sheetsActivity).inflate(R.layout.row_sheetdata,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return saveGameList.size
    }

    override fun onBindViewHolder(holder: SheetsDataAdapter.ViewHolder, position: Int) {
        holder.binding!!.tvGameName.text = saveGameList[position].game_name.toString()
        holder.binding!!.tvmTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].m_total?.toDouble())
        holder.binding!!.tvdTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].d_total?.toDouble())
        holder.binding!!.tvhTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].h_total?.toDouble())
        holder.binding!!.tvTotalAmount.text = ":      " + String.format("%.1f",saveGameList[position].grand_total?.toDouble())

        val gameDate: List<String> = saveGameList[position].created_at!!.substring(0, 10)?.split("-")
        var newGameDate = ""
        for (i in gameDate.indices.reversed()) {
            newGameDate = if (i == gameDate.size - 1) {
                newGameDate + gameDate[i]
            } else {
                newGameDate + "-" + gameDate[i]
            }
        }

        newGameDate = "$newGameDate   "+saveGameList[position].created_at?.substring(11,16)
        holder.binding!!.tvGameDate.text = newGameDate

        if (saveGameList[position].editable!!) {
            holder.binding!!.btnEdit.visibility = View.VISIBLE
            holder.binding!!.btnEdit.setOnClickListener { view: View? ->
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.storeSheet(saveGameList[position].cell_amounts, "SHEET")
                hashMap = Preferences(sheetsActivity).getInstance(sheetsActivity)?.getSheet("SHEET")!!
                for (i in 0 until hashMap.size) {
                    val keys: Array<Any> = hashMap.keys.toTypedArray()
                    sheetPosition.add(keys[i].toString())
                    sheetValue.add(hashMap.get(keys[i]).toString().toFloat().toString())
                }
                fillData()
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.storeNewSheet(mHashMap, "MSHEET")
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.storeNewSheet(dHashMap, "DSHEET")
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.storeNewSheet(hHashMap, "HSHEET")
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.setsheetId(saveGameList[position].id)
                Preferences(sheetsActivity).getInstance(sheetsActivity)?.setHCounter(2)
                val bundle = Bundle()
                bundle.putString("GETSHEET", "sheet_get")
                val intent = Intent(sheetsActivity, AddAmountActivity::class.java)
                intent.putExtras(bundle)
                sheetsActivity.startActivity(intent)
                (sheetsActivity as Activity).finish()
            }
        }

        holder.binding!!.btnSheet.setOnClickListener { view: View? ->
            Preferences(sheetsActivity).getInstance(sheetsActivity)?.storeSheet(saveGameList[position].cell_amounts, "SHEET")
            val bundle = Bundle()
            bundle.putString("GameName", saveGameList[position].game_name.toString())
            val intent = Intent(sheetsActivity, SheetViewActivity::class.java)
            intent.putExtras(bundle)
            sheetsActivity.startActivity(intent)
        }

        holder.binding!!.btnSubmit.setOnClickListener { view: View? ->
            AlertDialog.Builder(sheetsActivity)
                .setTitle("USL")
                .setMessage("Are you sure want to Submit Sheet?")
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface: DialogInterface?, i: Int ->
                    Preferences(sheetsActivity).getInstance(sheetsActivity)?.setsheetId(saveGameList[position].id)
                    if (view != null) {
                        listener(view, position, "Submit")
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        if (saveGameList[position].deletable!!) {
            holder.binding!!.btnDelete.visibility = View.VISIBLE
            holder.binding!!.btnDelete.setOnClickListener { view: View? ->
                AlertDialog.Builder(sheetsActivity)
                    .setTitle("USL")
                    .setMessage("Are you sure want to Delete Sheet?")
                    .setPositiveButton(
                        "Ok"
                    ) { dialogInterface: DialogInterface?, i: Int ->
                        Preferences(sheetsActivity).getInstance(sheetsActivity)?.setsheetId(saveGameList[position].id)
                        if(view!=null){
                            listener(view, position, "Delete")
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding: RowSheetdataBinding?
        init {
            binding=DataBindingUtil.bind(view)
        }
    }


    fun fillData() {
        var x = 1
        var y = 1
        for (k in sheetPosition.indices) {
            x = 1
            y = 1
            for (i in 0..9) {
                y = x
                for (j in 0..11) {
                    if (j < 10) {
                        if (y.toString().equals(sheetPosition[k], ignoreCase = true)) {
                            mHashMap[sheetPosition[k].toInt()] = hashMap[sheetPosition[k]]!!
                        }
                    }
                    if (j == 10) {
                        if (y.toString().equals(sheetPosition[k], ignoreCase = true)) {
                            if (sheetPosition[k].toInt() == 101) {
                                dHashMap[1] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 102) {
                                dHashMap[2] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 103) {
                                dHashMap[3] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 104) {
                                dHashMap[4] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 105) {
                                dHashMap[5] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 106) {
                                dHashMap[6] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 107) {
                                dHashMap[7] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 108) {
                                dHashMap[8] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 109) {
                                dHashMap[9] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 110) {
                                dHashMap[10] = hashMap[sheetPosition[k]]!!
                            }
                        }
                    }
                    if (j == 11) {
                        if (y.toString().equals(sheetPosition[k], ignoreCase = true)) {
                            if (sheetPosition[k].toInt() == 111) {
                                hHashMap[1] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 112) {
                                hHashMap[2] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 113) {
                                hHashMap[3] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 114) {
                                hHashMap[4] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 115) {
                                hHashMap[5] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 116) {
                                hHashMap[6] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 117) {
                                hHashMap[7] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 118) {
                                hHashMap[8] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 119) {
                                hHashMap[9] = hashMap[sheetPosition[k]]!!
                            } else if (sheetPosition[k].toInt() == 120) {
                                hHashMap[10] = hashMap[sheetPosition[k]]!!
                            }
                        }
                    }
                    y = y + 10
                }
                x = x + 1
            }
        }
    }

}