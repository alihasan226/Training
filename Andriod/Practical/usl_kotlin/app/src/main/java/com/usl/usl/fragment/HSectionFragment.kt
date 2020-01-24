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
import com.usl.usl.adapter.HSectionAdapter
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Preferences
import kotlinx.android.synthetic.main.fragment_hsection.*

class HSectionFragment(var bundle: Bundle?) : Fragment() {

    var hTableAdapter: HSectionAdapter?=null
    lateinit var linearLayoutManager:LinearLayoutManager
    lateinit var numberPicker:NumberPicker
    lateinit var recyclerView: RecyclerView
    lateinit var etValue:TextView
    var hashMap : HashMap<Int?,String?>?=null
    var temp:Float= 0.0f
    var choosen_item:Int= 1
    var appUser = AppUser()
    var temphashMap: HashMap<Int?, String?>? = null
    val handler = Handler()
    var counter:Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view:View=inflater.inflate(R.layout.fragment_hsection,container,false)
        numberPicker=view.findViewById(R.id.numberpickerPosition)
        recyclerView=view.findViewById(R.id.rvData)
        etValue=view.findViewById(R.id.etValue)

        linearLayoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager
        temphashMap = HashMap<Int?,String?>()
        hashMap = HashMap<Int?,String?>()
        Preferences(context).getInstance(context)?.setHCounter(1)

        if(bundle!=null && counter==0){
            if (bundle!!.getString("GETSHEET")=="sheet_get") {
                hashMap = Preferences(context).getInstance(context)?.getNewSheet("HSHEET")
                Preferences(context).getInstance(context)?.storeHashMap(hashMap, "HColumn")
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

        temphashMap=Preferences(context).getInstance(context)?.getHashMap("HColumn")
        for (i in 0 until temphashMap!!.size) {
            val keys: Array<Int?> = temphashMap!!.keys.toTypedArray()
            if (temphashMap!!.get(keys[i]).toString().toFloat() > 0.0f) {
                hashMap!![keys[i].toString().toInt()] = temphashMap!!.get(keys[i]).toString()
            }
        }

        hTableAdapter= HSectionAdapter(context,hashMap as HashMap<Int?,String?>){view:View?,position:Int?,OPERATION:String? ->
            if(OPERATION=="Delete"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                temphashMap!!.clear()
                temphashMap=Preferences(context).getInstance(context)?.getHashMap("HColumn")
                temphashMap?.remove(key = keys[position!!])
                Preferences(context).getInstance(context)?.storeHashMap(temphashMap,"HColumn")
                hashMap!!.remove(key = keys[position!!].toString().toInt())
                hTableAdapter!!.notifyDataSetChanged()
            }
            if(OPERATION=="Edit"){
                val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                numberPicker.value = keys[position!!].toString().toInt()
                etValue.text = hashMap!!.get(keys[position]).toString()
            }
        }
        hTableAdapter!!.notifyDataSetChanged()
        recyclerView.adapter=hTableAdapter


        numberPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
            override fun onValueChange(picker: NumberPicker, oldVal: Int, newVal: Int) {
                if (newVal == 1) {
                    choosen_item = 10
                }
                if (newVal != 1) {
                    choosen_item = newVal - 1
                }
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(userStoppedTyping, 200) // 2 second
            }
            var userStoppedTyping = Runnable {
                if (!TextUtils.isEmpty(etValue.text)) {
                    hashMap!![choosen_item] = etValue.text.toString()
                    temphashMap!!.clear()
                    temphashMap = Preferences(context).getInstance(context)?.getHashMap("HColumn")
                    temphashMap!![choosen_item.toString().toInt()] = etValue.text.toString()
                    Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "HColumn")
                    hTableAdapter = HSectionAdapter(context, hashMap!!) { view1: View?, position: Int?, OPERATION: String? ->
                        if (OPERATION == "Delete") {
                            val keys: Array<Any?> = hashMap!!.keys.toTypedArray()
                            temphashMap = Preferences(context).getInstance(context)?.getHashMap("HColumn")
                            temphashMap!!.remove(keys[position!!])
                            Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "HColumn")
                            hashMap!!.remove(keys[position].toString().toInt())
                            hTableAdapter!!.notifyDataSetChanged()
                        }
                        if (OPERATION == "Edit") {
                            val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                            numberPicker.value = keys[position!!].toString().toInt()
                            etValue.text = hashMap!!.get(keys[position]).toString()
                        }
                    }
                    hTableAdapter!!.notifyDataSetChanged()
                    recyclerView.adapter = hTableAdapter
                    etValue.text = ""
                }
            }
        })

        etValue.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                choosen_item = numberPicker.value
                temp = if (etValue.text.toString()=="") {
                    0.0f
                } else if (etValue.text.toString()==".") {
                    0.0f
                } else {
                    etValue.text.toString().toFloat()
                }
                if (temp != 0.0f) {
                    hashMap!![choosen_item] = temp.toString()
                }
                Preferences(context).getInstance(context)?.storeHashMap(hashMap, "HColumn")
                hTableAdapter = HSectionAdapter(context,hashMap!!) { view1: View?, position: Int?, OPERATION: String? ->
                    if (OPERATION == "Delete") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        temphashMap = Preferences(context).getInstance(context)?.getHashMap("HColumn")
                        temphashMap!!.remove(keys[position!!])
                        Preferences(context).getInstance(context)?.storeHashMap(temphashMap, "HColumn")
                        hashMap!!.remove(keys[position].toString().toInt())
                        hTableAdapter!!.notifyDataSetChanged()
                    }
                    if (OPERATION == "Edit") {
                        val keys: Array<Int?> = hashMap!!.keys.toTypedArray()
                        numberPicker.value = keys[position!!].toString().toInt()
                        etValue.text = hashMap!!.get(keys[position]).toString()
                    }
                }
                recyclerView.adapter = hTableAdapter
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

    override fun onPause() {
        super.onPause()
        counter = counter + 1
    }

}