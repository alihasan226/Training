package com.usl.usl.fragment

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawnlin.numberpicker.NumberPicker
import com.usl.usl.R
import com.usl.usl.adapter.MSectionAdapter
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Preferences

class MSectionFragment(var bundle: Bundle?) : Fragment(){

    var appUser = AppUser()
    var linearLayoutManager: LinearLayoutManager? = null
    var hashMap: HashMap<Int?, String?>? = null
    var temphashMap:HashMap<Int?,String?>? = null
    var temp = 0.0f
    var tableAdapter: MSectionAdapter? = null
    var chosen_value = 1
    val handler = Handler()
    var counter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View =inflater.inflate(R.layout.fragment_msection, container, false)
        var numberPicker: NumberPicker = view.findViewById(R.id.numberpickerPosition)
        var etValue1:TextView = view.findViewById(R.id.etValue1)
        var recyclerView: RecyclerView = view.findViewById(R.id.rvData)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        temphashMap = HashMap<Int?, String?>()
        hashMap = HashMap<Int?,String?>()

        if (bundle != null && counter == 0) {
            if (bundle!!.getString("GETSHEET")=="sheet_get") {
                hashMap = Preferences(context).getInstance(context)?.getNewSheet("MSHEET")
                Preferences(context).getInstance(context)?.storeHashMap(hashMap, "MSection")
            }
        }

        numberPicker.value = 1
        val data = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32","33", "34", "35", "36", "37", "38", "39", "40", "41", "42","43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65","66", "67", "68", "69", "70", "71","72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100")
        numberPicker.minValue = 1
        numberPicker.maxValue = data.size
        numberPicker.displayedValues = data
        numberPicker.isFadingEdgeEnabled = true
        numberPicker.isScrollerEnabled = true
        numberPicker.wrapSelectorWheel = true

        temphashMap = Preferences(context).getInstance(context)?.getHashMap("MSection")
        for (i in 0 until temphashMap!!.size) {
            val keys: Array<Int?> = temphashMap!!.keys.toTypedArray()
            if (temphashMap!!.get(keys[i]).toString().toFloat() > 0.0f) {
                hashMap!![keys[i].toString().toInt()] = temphashMap!!.get(keys[i]).toString()
            }
        }
        tableAdapter= MSectionAdapter(context,hashMap as HashMap<Int?,String?>){view:View?,position:Int?,OPERATION:String? ->
            if(OPERATION=="Delete"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                temphashMap!!.clear()
                temphashMap=Preferences(context).getInstance(context)?.getHashMap("MSection")
                temphashMap?.remove(key = keys[position!!])
                Preferences(context).getInstance(context)?.storeHashMap(temphashMap,"MSection")
                hashMap!!.remove(key = keys[position!!].toString().toInt())
                tableAdapter!!.notifyDataSetChanged()
            }
            if(OPERATION=="Edit"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                numberPicker.value = keys[position!!].toString().toInt()
                etValue1.text = hashMap!!.get(keys[position]).toString()
            }
        }
        tableAdapter!!.notifyDataSetChanged()
        recyclerView.adapter = tableAdapter

        etValue1.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                chosen_value = numberPicker.value
                temp = if (etValue1.text.toString()=="") {
                    0.0f
                } else if (etValue1.text.toString()==".") {
                    0.0f
                } else {
                    etValue1.text.toString().toFloat()
                }
                if (temp != 0.0f) {
                    hashMap!![chosen_value] = temp.toString()
                }
                tableAdapter = MSectionAdapter(context,hashMap!!) { view1: View?, position: Int?, OPERATION: String? ->
                    if (OPERATION == "Delete") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        temphashMap!!.clear()
                        temphashMap = Preferences(context).getInstance(context)?.getHashMap("MSection")
                        temphashMap!!.remove(keys[position!!])
                        Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "MSection")
                        hashMap!!.remove(keys[position].toString().toInt())
                        tableAdapter!!.notifyDataSetChanged()
                    }
                    if (OPERATION == "Edit") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        numberPicker.value = keys[position!!].toString().toInt()
                        etValue1.text = hashMap!!.get(keys[position]).toString()
                    }
                }
                Preferences(context).getInstance(context)?.storeHashMap(hashMap, "MSection")
                recyclerView.adapter = tableAdapter
                etValue1.text = ""
                val number = numberPicker.value
                numberPicker.value = number + 1
                if (number == 100 && hashMap!![1] != null) {
                    etValue1.text = hashMap!![1]
                } else if (hashMap!![number + 1] != null) {
                    etValue1.text = hashMap!![number + 1]
                }
            }
            true
        }

        numberPicker.setOnValueChangedListener(object :
            NumberPicker.OnValueChangeListener {
            override fun onValueChange(
                picker: NumberPicker,
                oldVal: Int,
                newVal: Int
            ) {
                if (newVal == 1) {
                    chosen_value = 100
                }
                if (newVal != 1) {
                    chosen_value = newVal - 1
                }
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(userStoppedTyping, 200) // 2 second
            }

            var userStoppedTyping = Runnable {
                if (!TextUtils.isEmpty(etValue1.text)) {
                    hashMap!![chosen_value] = etValue1.text.toString()
                    temphashMap!!.clear()
                    temphashMap = Preferences(context).getInstance(context)?.getHashMap("MSection")
                    temphashMap!![chosen_value.toString().toInt()] = etValue1.text.toString()
                    Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "MSection")
                    tableAdapter = MSectionAdapter(context,hashMap!!) { view1: View?, position: Int?, OPERATION: String? ->
                        if (OPERATION == "Delete") {
                            val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                            temphashMap = Preferences(context).getInstance(context)?.getHashMap("MSection")
                            temphashMap!!.remove(keys[position!!])
                            Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "MSection")
                            hashMap!!.remove(keys[position].toString().toInt())
                            tableAdapter!!.notifyDataSetChanged()
                        }
                        if (OPERATION == "Edit") {
                            val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                            numberPicker.value = keys[position!!].toString().toInt()
                            etValue1.text = hashMap!!.get(keys[position]).toString()
                        }
                    }
                    Preferences(context).getInstance(context)?.storeHashMap(hashMap, "MSection")
                    tableAdapter!!.notifyDataSetChanged()
                    recyclerView.adapter = tableAdapter
                    etValue1.text = ""
                }
            }
        })


        view.viewTreeObserver.addOnGlobalLayoutListener {
            if (view.height > 1000) {
                etValue1.clearFocus()
            }
        }

        return view
    }


    override fun onPause() {
        super.onPause()
        counter = counter + 1
    }

}