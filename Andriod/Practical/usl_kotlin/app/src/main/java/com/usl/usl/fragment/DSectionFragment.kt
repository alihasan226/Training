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
import com.usl.usl.adapter.DSectionAdapter
import com.usl.usl.utils.Preferences

class DSectionFragment(var bundle:Bundle?) : Fragment() {

    var dTableAdapter:DSectionAdapter?=null
    lateinit var numberPicker:NumberPicker
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var etValue:TextView
    var temp = 0.0f
    var choose_item = 1
    var hashMap : HashMap<Int?, String?>? = null
    var temphashMap : HashMap<Int?,String?>? = null
    val handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View=inflater.inflate(R.layout.fragment_dsection,container,false)
        numberPicker=view.findViewById(R.id.numberpickerPosition)
        etValue=view.findViewById(R.id.etValue)
        recyclerView=view.findViewById(R.id.rvData)
        linearLayoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager
        temphashMap = HashMap<Int?,String?>()
        hashMap = HashMap<Int?,String?>()

        if(bundle!=null){
            if(bundle!!.getString("GETSHEET")=="sheet_get"){
                hashMap = Preferences(context).getInstance(context)?.getNewSheet("DSHEET")
                Preferences(context).getInstance(context)?.storeHashMap(hashMap,"DColumn")
            }
        }

        numberPicker.minValue = 1
        numberPicker.value = 1
        val data = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        numberPicker.maxValue = data.size
        numberPicker.displayedValues = data
        numberPicker.isFadingEdgeEnabled = true
        numberPicker.isScrollerEnabled = true
        numberPicker.wrapSelectorWheel = true

        temphashMap = Preferences(context).getInstance(context)?.getHashMap("DColumn")
        for (i in 0 until temphashMap!!.size) {
            val keys: Array<Int?> = temphashMap!!.keys.toTypedArray()
            if (temphashMap!!.get(keys[i]).toString().toFloat() > 0.0f) {
                hashMap!![keys[i].toString().toInt()] = temphashMap!!.get(keys[i]).toString()
            }
        }

        dTableAdapter = DSectionAdapter(context,hashMap as HashMap<Int?,String?>){view:View?,position:Int?,OPERATION:String? ->
            if(OPERATION=="Delete"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                temphashMap!!.clear()
                temphashMap=Preferences(context).getInstance(context)?.getHashMap("DColumn")
                temphashMap?.remove(key = keys[position!!])
                Preferences(context).getInstance(context)?.storeHashMap(temphashMap,"DColumn")
                hashMap!!.remove(key = keys[position!!].toString().toInt())
                dTableAdapter!!.notifyDataSetChanged()
            }
            if(OPERATION=="Edit"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                numberPicker.value = keys[position!!].toString().toInt()
                etValue.text = hashMap!!.get(keys[position]).toString()
            }
        }
        dTableAdapter!!.notifyDataSetChanged()
        recyclerView.adapter=dTableAdapter


        numberPicker.setOnValueChangedListener(object :
            NumberPicker.OnValueChangeListener {
            override fun onValueChange(picker: NumberPicker,oldVal: Int,newVal: Int) {
                if (newVal == 1) {
                    choose_item = 10
                }
                if (newVal != 1) {
                    choose_item = newVal - 1
                }
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(userStoppedTyping, 200) // 2 second
            }

            var userStoppedTyping = Runnable {
                if (!TextUtils.isEmpty(etValue.text)) {
                    hashMap!![choose_item] = etValue.text.toString()
                    temphashMap!!.clear()
                    temphashMap = Preferences(context).getInstance(context)?.getHashMap("DColumn")
                    temphashMap!![choose_item.toString().toInt()] = etValue.text.toString()
                    Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "DColumn")
                    dTableAdapter = DSectionAdapter(context,hashMap!! as HashMap<Int?, String?>) { view1: View?, position: Int?, OPERATION: String? ->
                        if (OPERATION == "DElete") {
                            val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                            temphashMap = Preferences(context).getInstance(context)?.getHashMap("DColumn")
                            temphashMap!!.remove(keys[position!!])
                            Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "DColumn")
                            hashMap!!.remove(keys[position].toString().toInt())
                            dTableAdapter!!.notifyDataSetChanged()
                        }
                        if (OPERATION == "Edit") {
                            val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                            numberPicker.value = keys[position!!].toString().toInt()
                            etValue.text = hashMap!!.get(keys[position]).toString()
                        }
                    }
                    dTableAdapter!!.notifyDataSetChanged()
                    recyclerView.adapter = dTableAdapter
                    etValue.text = ""
                }
            }
        })


        etValue.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                choose_item = numberPicker.value
                temp = if (etValue.text.toString().equals("", ignoreCase = true)) {
                    0.0f
                } else if (etValue.text.toString().equals(".", ignoreCase = true)) {
                    0.0f
                } else {
                    etValue.text.toString().toFloat()
                }
                if (temp != 0.0f) {
                    hashMap!![choose_item] = temp.toString()
                }
                Preferences(context).getInstance(context)?.storeHashMap(hashMap, "DColumn")
                dTableAdapter = DSectionAdapter(context,hashMap!!as HashMap<Int?, String?>) { view1: View?, position: Int?, OPERATION: String? ->
                    if (OPERATION == "DElete") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        temphashMap =
                            Preferences(context).getInstance(context)?.getHashMap("DColumn")
                        temphashMap!!.remove(keys[position!!])
                        Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "DColumn")
                        hashMap!!.remove(keys[position].toString().toInt())
                        dTableAdapter!!.notifyDataSetChanged()
                    }
                    if (OPERATION == "Edit") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        numberPicker.value = keys[position!!].toString().toInt()
                        etValue.text = hashMap!!.get(keys[position]).toString()
                    }
                }
                recyclerView.adapter = dTableAdapter
                etValue.text = ""
                val number = numberPicker.value
                numberPicker.value = number + 1
                if (number == 10 && hashMap!![1] != null) {
                    etValue.text = hashMap!![1]
                } else if (hashMap!![number + 1] != null) {
                    etValue.text = hashMap!![number + 1]
                }
            }
            true
        }

        view.viewTreeObserver.addOnGlobalLayoutListener {
            if (view.height > 1000) {
                etValue.clearFocus()
            }
        }


        return view
    }
}