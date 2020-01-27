package com.usl.usl.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.utils.Preferences
import java.util.*

class SheetViewActivity : AppCompatActivity(), View.OnClickListener{


    @BindView(R.id.tablelayout)
    lateinit var table: View
    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    lateinit var back: ImageView
    lateinit var tvHeading: TextView
    var hashMap = HashMap<String, String>()
    var sheetPosition = ArrayList<String>()

    var dSection = 0.0f; var hSection:Float = 0.0f; var mRow1:Float = 0.0f; var mRow2:Float = 0.0f; var mRow3:Float = 0.0f; var mRow4:Float = 0.0f; var mRow5:Float = 0.0f; var mRow6:Float = 0.0f; var mRow7:Float = 0.0f; var mRow8:Float = 0.0f; var mRow9:Float = 0.0f; var mRow10:Float = 0.0f
    var MCount = Array(10) { FloatArray(10) }
    var DCount = FloatArray(10)
    var HCount = FloatArray(10)
    var x:Int=1
    var y:Int=1
    lateinit var tvOne:TextView;lateinit var tvTwo:TextView;lateinit var tvThree:TextView;lateinit var tvFour:TextView;lateinit var tvFive:TextView;lateinit var tvSix:TextView;lateinit var tvSeven:TextView;lateinit var tvEight:TextView;lateinit var tvNine:TextView;lateinit var tvTen:TextView;lateinit var tvEleven:TextView;lateinit var tvTwelve:TextView;lateinit var tvThirteen:TextView;lateinit var tvFourteen:TextView;lateinit var tvFifteen:TextView;lateinit var tvSixteen:TextView;lateinit var tvSeventeen:TextView;lateinit var tvEighteen:TextView;lateinit var tvNineteen:TextView;lateinit var tvTwenty:TextView;
    lateinit var tvTwentyOne:TextView;lateinit var tvTwentyTwo:TextView;lateinit var tvTwentyThree:TextView;lateinit var tvTwentyFour:TextView;lateinit var tvTwentyFive:TextView;lateinit var tvTwentySix:TextView;lateinit var tvTwentySeven:TextView;lateinit var tvTwentyEight:TextView;lateinit var tvTwentyNine:TextView;lateinit var tvThirty:TextView;lateinit var tvThiryOne:TextView;lateinit var tvThirtyTwo:TextView;lateinit var tvThirtyThree:TextView;lateinit var tvThirtyFour:TextView;lateinit var tvThirtyFive:TextView;
    lateinit var tvThirtySix: TextView;lateinit var tvThirtySeven:TextView;lateinit var tvThirtyEight:TextView;lateinit var tvThirtyNine:TextView;lateinit var tvForty:TextView;lateinit var tvFortyOne:TextView;lateinit var tvFortyTwo:TextView;lateinit var tvFortyThree:TextView;lateinit var tvFortyFour:TextView;lateinit var tvFortyFive:TextView;lateinit var tvFortySix:TextView;lateinit var tvFortySeven:TextView;lateinit var tvFortyEight:TextView;lateinit var tvFortyNine:TextView;lateinit var tvFifty:TextView;lateinit var tvFiftyOne:TextView;lateinit var tvFiftyTwo:TextView;lateinit var tvFiftyThree:TextView;lateinit var tvFiftyFour:TextView;lateinit var tvFiftyFive:TextView;lateinit var tvFiftySix:TextView;lateinit var tvFiftySeven:TextView;lateinit var tvFiftyEight:TextView;lateinit var tvFiftyNine:TextView;lateinit var tvSixty:TextView;
    lateinit var tvSixtyOne:TextView;lateinit var tvSixtyTwo:TextView;lateinit var tvSixtyThree:TextView;lateinit var tvSixtyFour:TextView;lateinit var tvSixtyFive:TextView;lateinit var tvSixtySix:TextView;lateinit var tvSixtySeven:TextView;lateinit var tvSixtyEight:TextView;lateinit var tvSixtyNine:TextView;lateinit var tvSeventy:TextView;lateinit var tvSeventyOne:TextView;lateinit var tvSeventyTwo:TextView;lateinit var tvSeventyThree:TextView;lateinit var tvSeventyFour:TextView;lateinit var tvSeventyFive:TextView;lateinit var tvSeventySix:TextView;lateinit var tvSeventySeven:TextView;lateinit var tvSeventyEight:TextView;lateinit var tvSeventyNine:TextView;lateinit var tvEighty:TextView;lateinit var tvEightyOne:TextView;lateinit var tvEightyTwo:TextView;lateinit var tvEightyThree:TextView;lateinit var tvEightyFour:TextView;lateinit var tvEightyFive:TextView;lateinit var tvEightySix:TextView;
    lateinit var tvEightySeven: TextView;lateinit var tvEightyEight:TextView;lateinit var tvEightyNine:TextView;lateinit var tvNinety:TextView;lateinit var tvNinetyOne:TextView;lateinit var tvNinetyTwo:TextView;lateinit var tvNinetyThree:TextView;lateinit var tvNinetyFour:TextView;lateinit var tvNinetyFive:TextView;lateinit var tvNinetySix:TextView;lateinit var tvNinetySeven:TextView;lateinit var tvNinetyEight:TextView;lateinit var tvNinetyNine:TextView;lateinit var tvHundred:TextView;lateinit var tvd1:TextView;lateinit var tvd2:TextView;lateinit var tvd3:TextView;lateinit var tvd4:TextView;lateinit var tvd5:TextView;lateinit var tvd6:TextView;lateinit var tvd7:TextView;lateinit var tvd8:TextView;lateinit var tvd9:TextView;lateinit var tvd10:TextView;lateinit var tvh1:TextView;lateinit var tvh2:TextView;lateinit var tvh3:TextView;lateinit var tvh4:TextView;lateinit var tvh5:TextView;lateinit var tvh6:TextView;lateinit var tvh7:TextView;lateinit var tvh8:TextView;lateinit var tvh9:TextView;lateinit var tvh10:TextView;lateinit var tvRow1:TextView;lateinit var tvRow2:TextView;lateinit var tvRow3:TextView;lateinit var tvRow4:TextView;lateinit var tvRow5:TextView;lateinit var tvRow6:TextView;lateinit var tvRow7:TextView;lateinit var tvRow8:TextView;lateinit var tvRow9:TextView;lateinit var tvRow10:TextView;lateinit var tvRow11:TextView;lateinit var tvRow12:TextView;lateinit var tvMTotal:TextView;lateinit var tvDHTotal:TextView;lateinit var tvGrandTotal:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()
        setItemView()

        hashMap = Preferences(applicationContext).getInstance(applicationContext)?.getSheet("SHEET")!!
        for (i in 0 until hashMap.size) {
            val keys: Array<Any> = hashMap.keys.toTypedArray()
            sheetPosition.add(keys[i].toString())
        }

        for (i in 0..9) {
            for (j in 0..9) {
                MCount[i][j] = 0.0f
            }
            DCount[i] = 0.0f
            HCount[i] = 0.0f
        }


        for (i in sheetPosition.indices) {
            x = 1
            y = 1
            for (j in 0..9) {
                y = x
                for (k in 0..11) {
                    if (j == 0) {
                        if (k == 0 && y == 1) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvOne.getText())) {
                                    MCount[j][k] = tvOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 11) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvEleven.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvEleven.getText())) {
                                    MCount[j][k] = tvEleven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 21) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwentyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwentyOne.getText())) {
                                    MCount[j][k] =
                                        tvTwentyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 31) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThiryOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThiryOne.getText())) {
                                    MCount[j][k] = tvThiryOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 41) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFortyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFortyOne.getText())) {
                                    MCount[j][k] = tvFortyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 51) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFiftyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFiftyOne.getText())) {
                                    MCount[j][k] = tvFiftyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 61) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSixtyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSixtyOne.getText())) {
                                    MCount[j][k] = tvSixtyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 71) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSeventyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSeventyOne.getText())) {
                                    MCount[j][k] =
                                        tvSeventyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 81) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvEightyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvEightyOne.getText())) {
                                    MCount[j][k] =
                                        tvEightyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 91) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvNinetyOne.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvNinetyOne.getText())) {
                                    MCount[j][k] =
                                        tvNinetyOne.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 101) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvd1.setText(hashMap[sheetPosition[i]].toString())
                                if (!TextUtils.isEmpty(tvd1.getText())) {
                                    DCount[j] = tvd1.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 111) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvh1.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvh1.getText())) {
                                    HCount[j] = tvh1.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 1) {
                        if (k == 0 && y == 2) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwo.getText())) {
                                    MCount[j][k] = tvTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 12) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwelve.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwelve.getText())) {
                                    MCount[j][k] = tvTwelve.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 22) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwentyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwentyTwo.getText())) {
                                    MCount[j][k] =
                                        tvTwentyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 32) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThirtyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThirtyTwo.getText())) {
                                    MCount[j][k] =
                                        tvThirtyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 42) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFortyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFortyTwo.getText())) {
                                    MCount[j][k] = tvFortyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 52) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFiftyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFiftyTwo.getText())) {
                                    MCount[j][k] = tvFiftyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 62) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSixtyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSixtyTwo.getText())) {
                                    MCount[j][k] = tvSixtyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 72) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSeventyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSeventyTwo.getText())) {
                                    MCount[j][k] =
                                        tvSeventyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 82) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvEightyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvEightyTwo.getText())) {
                                    MCount[j][k] =
                                        tvEightyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 92) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvNinetyTwo.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvNinetyTwo.getText())) {
                                    MCount[j][k] =
                                        tvNinetyTwo.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 102) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvd2.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvd2.getText())) {
                                    DCount[j] = tvd2.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 112) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvh2.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvh2.getText())) {
                                    HCount[j] = tvh2.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 2) {
                        if (k == 0 && y == 3) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThree.getText())) {
                                    MCount[j][k] = tvThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 13) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThirteen.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThirteen.getText())) {
                                    MCount[j][k] = tvThirteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 23) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwentyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwentyThree.getText())) {
                                    MCount[j][k] =
                                        tvTwentyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 33) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThirtyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThirtyThree.getText())) {
                                    MCount[j][k] =
                                        tvThirtyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 43) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFortyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFortyThree.getText())) {
                                    MCount[j][k] =
                                        tvFortyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 53) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFiftyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFiftyThree.getText())) {
                                    MCount[j][k] =
                                        tvFiftyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 63) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSixtyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSixtyThree.getText())) {
                                    MCount[j][k] =
                                        tvSixtyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 73) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSeventyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSeventyThree.getText())) {
                                    MCount[j][k] =
                                        tvSeventyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 83) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvEightyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvEightyThree.getText())) {
                                    MCount[j][k] =
                                        tvEightyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 93) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvNinetyThree.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvNinetyThree.getText())) {
                                    MCount[j][k] =
                                        tvNinetyThree.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 103) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvd3.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvd3.getText())) {
                                    DCount[j] = tvd3.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 113) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvh3.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvh3.getText())) {
                                    HCount[j] = tvh3.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 9) {
                        if (k == 0 && y == 10) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTen.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTen.getText())) {
                                    MCount[j][k] = tvTen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 20) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvTwenty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvTwenty.getText())) {
                                    MCount[j][k] = tvTwenty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 30) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvThirty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvThirty.getText())) {
                                    MCount[j][k] = tvThirty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 40) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvForty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvForty.getText())) {
                                    MCount[j][k] = tvForty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 50) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvFifty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvFifty.getText())) {
                                    MCount[j][k] = tvFifty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 60) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSixty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSixty.getText())) {
                                    MCount[j][k] = tvSixty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 70) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvSeventy.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvSeventy.getText())) {
                                    MCount[j][k] = tvSeventy.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 80) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvEighty.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvEighty.getText())) {
                                    MCount[j][k] = tvEighty.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 90) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvNinety.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvNinety.getText())) {
                                    MCount[j][k] = tvNinety.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 100) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvHundred.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvHundred.getText())) {
                                    MCount[j][k] = tvHundred.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 110) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvd10.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvd10.getText())) {
                                    DCount[j] = tvd10.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 120) {
                            if (y.toString().equals(sheetPosition[i], ignoreCase = true)) {
                                tvh10.setText(hashMap[sheetPosition[i]])
                                if (!TextUtils.isEmpty(tvh10.getText())) {
                                    HCount[j] = tvh10.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 3) {
                        if (k == 0 && y == 4) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFour.getText())) {
                                    MCount[j][k] = tvFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 14) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFourteen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFourteen.getText())) {
                                    MCount[j][k] = tvFourteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 24) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentyFour.getText())) {
                                    MCount[j][k] = tvTwentyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 34) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtyFour.getText())) {
                                    MCount[j][k] = tvThirtyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 44) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortyFour.getText())) {
                                    MCount[j][k] = tvFortyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 54) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftyFour.getText())) {
                                    MCount[j][k] =
                                        tvFiftyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 64) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtyFour.getText())) {
                                    MCount[j][k] =
                                        tvSixtyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 74) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventyFour.getText())) {
                                    MCount[j][k] =
                                        tvSeventyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 84) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightyFour.getText())) {
                                    MCount[j][k] =
                                        tvEightyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 94) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetyFour.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetyFour.getText())) {
                                    MCount[j][k] =
                                        tvNinetyFour.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 104) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd4.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd4.getText())) {
                                    DCount[j] = tvd4.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 114) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh4.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh4.getText())) {
                                    HCount[j] = tvh4.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 4) {
                        if (k == 0 && y == 5) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFive.getText())) {
                                    MCount[j][k] = tvFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 15) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFifteen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFifteen.getText())) {
                                    MCount[j][k] = tvFifteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 25) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentyFive.getText())) {
                                    MCount[j][k] =
                                        tvTwentyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 35) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtyFive.getText())) {
                                    MCount[j][k] =
                                        tvThirtyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 45) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortyFive.getText())) {
                                    MCount[j][k] =
                                        tvFortyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 55) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftyFive.getText())) {
                                    MCount[j][k] =
                                        tvFiftyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 65) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtyFive.getText())) {
                                    MCount[j][k] =
                                        tvSixtyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 75) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventyFive.getText())) {
                                    MCount[j][k] =
                                        tvSeventyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 85) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightyFive.getText())) {
                                    MCount[j][k] =
                                        tvEightyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 95) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetyFive.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetyFive.getText())) {
                                    MCount[j][k] =
                                        tvNinetyFive.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 105) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd5.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd5.getText())) {
                                    DCount[j] = tvd5.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 115) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh5.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh5.getText())) {
                                    HCount[j] = tvh5.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 5) {
                        if (k == 0 && y == 6) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSix.getText())) {
                                    MCount[j][k] = tvSix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 16) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixteen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixteen.getText())) {
                                    MCount[j][k] = tvSixteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 26) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentySix.getText())) {
                                    MCount[j][k] =
                                        tvTwentySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 36) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtySix.getText())) {
                                    MCount[j][k] =
                                        tvThirtySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 46) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortySix.getText())) {
                                    MCount[j][k] = tvFortySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 56) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftySix.getText())) {
                                    MCount[j][k] = tvFiftySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 66) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtySix.getText())) {
                                    MCount[j][k] = tvSixtySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 76) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventySix.getText())) {
                                    MCount[j][k] =
                                        tvSeventySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 86) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightySix.getText())) {
                                    MCount[j][k] =
                                        tvEightySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 96) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetySix.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetySix.getText())) {
                                    MCount[j][k] =
                                        tvNinetySix.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 106) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd6.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd6.getText())) {
                                    DCount[j] = tvd6.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 116) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh6.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh6.getText())) {
                                    HCount[j] = tvh6.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 6) {
                        if (k == 0 && y == 7) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeven.getText())) {
                                    MCount[j][k] = tvSeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 17) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventeen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventeen.getText())) {
                                    MCount[j][k] =
                                        tvSeventeen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 27) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentySeven.getText())) {
                                    MCount[j][k] =
                                        tvTwentySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 37) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtySeven.getText())) {
                                    MCount[j][k] =
                                        tvThirtySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 47) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortySeven.getText())) {
                                    MCount[j][k] =
                                        tvFortySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 57) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftySeven.getText())) {
                                    MCount[j][k] =
                                        tvFiftySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 67) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtySeven.getText())) {
                                    MCount[j][k] =
                                        tvSixtySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 77) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventySeven.getText())) {
                                    MCount[j][k] =
                                        tvSeventySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 87) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightySeven.getText())) {
                                    MCount[j][k] =
                                        tvEightySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 97) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetySeven.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetySeven.getText())) {
                                    MCount[j][k] =
                                        tvNinetySeven.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 107) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd7.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd7.getText())) {
                                    DCount[j] = tvd7.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 117) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh7.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh7.getText())) {
                                    HCount[j] = tvh7.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 7) {
                        if (k == 0 && y == 8) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEight.getText())) {
                                    MCount[j][k] = tvEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 18) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEighteen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEighteen.getText())) {
                                    MCount[j][k] = tvEighteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 28) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentyEight.getText())) {
                                    MCount[j][k] =
                                        tvTwentyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 38) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtyEight.getText())) {
                                    MCount[j][k] =
                                        tvThirtyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 48) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortyEight.getText())) {
                                    MCount[j][k] =
                                        tvFortyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 58) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftyEight.getText())) {
                                    MCount[j][k] =
                                        tvFiftyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 68) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtyEight.getText())) {
                                    MCount[j][k] =
                                        tvSixtyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 78) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventyEight.getText())) {
                                    MCount[j][k] =
                                        tvSeventyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 88) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightyEight.getText())) {
                                    MCount[j][k] =
                                        tvEightyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 98) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetyEight.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetyEight.getText())) {
                                    MCount[j][k] =
                                        tvNinetyEight.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 108) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd8.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd8.getText())) {
                                    DCount[j] = tvd8.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 118) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh8.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh8.getText())) {
                                    HCount[j] = tvh8.getText().toString().toFloat()
                                }
                            }
                        }
                    } else if (j == 8) {
                        if (k == 0 && y == 9) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNine.getText())) {
                                    MCount[j][k] = tvNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 1 && y == 19) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNineteen.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNineteen.getText())) {
                                    MCount[j][k] = tvNineteen.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 2 && y == 29) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvTwentyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvTwentyNine.getText())) {
                                    MCount[j][k] =
                                        tvTwentyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 3 && y == 39) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvThirtyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvThirtyNine.getText())) {
                                    MCount[j][k] =
                                        tvThirtyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 4 && y == 49) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFortyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFortyNine.getText())) {
                                    MCount[j][k] =
                                        tvFortyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 5 && y == 59) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvFiftyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvFiftyNine.getText())) {
                                    MCount[j][k] =
                                        tvFiftyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 6 && y == 69) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSixtyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSixtyNine.getText())) {
                                    MCount[j][k] =
                                        tvSixtyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 7 && y == 79) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvSeventyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvSeventyNine.getText())) {
                                    MCount[j][k] =
                                        tvSeventyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 8 && y == 89) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvEightyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvEightyNine.getText())) {
                                    MCount[j][k] =
                                        tvEightyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 9 && y == 99) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvNinetyNine.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvNinetyNine.getText())) {
                                    MCount[j][k] =
                                        tvNinetyNine.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 10 && y == 109) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvd9.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvd9.getText())) {
                                    DCount[j] = tvd9.getText().toString().toFloat()
                                }
                            }
                        }
                        if (k == 11 && y == 119) {
                            if (y.toString().equals(sheetPosition.get(i), ignoreCase = true)) {
                                tvh9.setText(hashMap.get(sheetPosition.get(i)))
                                if (!TextUtils.isEmpty(tvh9.getText())) {
                                    HCount[j] = tvh9.getText().toString().toFloat()
                                }
                            }
                        }
                    }
                    y = y + 10
                }
                x = x + 1
            }
        }

        for (i in 0..9) {
            for (j in 0..9) {
                if (j == 0) {
                    if (MCount[i][j] > 0.0) {
                        mRow1 += MCount[i][j]
                    }
                } else if (j == 1) {
                    if (MCount[i][j] > 0.0) {
                        mRow2 += MCount[i][j]
                    }
                } else if (j == 2) {
                    if (MCount[i][j] > 0.0) {
                        mRow3 += MCount[i][j]
                    }
                } else if (j == 3) {
                    if (MCount[i][j] > 0.0) {
                        mRow4 += MCount[i][j]
                    }
                } else if (j == 4) {
                    if (MCount[i][j] > 0.0) {
                        mRow5 += MCount[i][j]
                    }
                } else if (j == 5) {
                    if (MCount[i][j] > 0.0) {
                        mRow6 += MCount[i][j]
                    }
                } else if (j == 6) {
                    if (MCount[i][j] > 0.0) {
                        mRow7 += MCount[i][j]
                    }
                } else if (j == 7) {
                    if (MCount[i][j] > 0.0) {
                        mRow8 += MCount[i][j]
                    }
                } else if (j == 8) {
                    if (MCount[i][j] > 0.0) {
                        mRow9 += MCount[i][j]
                    }
                } else if (j == 9) {
                    if (MCount[i][j] > 0.0) {
                        mRow10 += MCount[i][j]
                    }
                }
            }
            dSection += DCount[i]
            hSection += HCount[i]
        }

        if (mRow1 > 0.0f) {
            tvRow1.text = String.format("%.1f", mRow1.toString().toDouble())
        }
        if (mRow2 > 0.0f) {
            tvRow2.text = String.format("%.1f", mRow2.toString().toDouble())
        }
        if (mRow3 > 0.0f) {
            tvRow3.text = String.format("%.1f", mRow3.toString().toDouble())
        }
        if (mRow4 > 0.0f) {
            tvRow4.text = String.format("%.1f", mRow4.toString().toDouble())
        }
        if (mRow5 > 0.0f) {
            tvRow5.text = String.format("%.1f", mRow5.toString().toDouble())
        }
        if (mRow6 > 0.0f) {
            tvRow6.text = String.format("%.1f", mRow6.toString().toDouble())
        }
        if (mRow7 > 0.0f) {
            tvRow7.text = String.format("%.1f", mRow7.toString().toDouble())
        }
        if (mRow8 > 0.0f) {
            tvRow8.text = String.format("%.1f", mRow8.toString().toDouble())
        }
        if (mRow9 > 0.0f) {
            tvRow9.text = String.format("%.1f", mRow9.toString().toDouble())
        }
        if (mRow10 > 0.0f) {
            tvRow10.text = String.format("%.1f", mRow10.toString().toDouble())
        }

        tvRow11.text = String.format("%.1f", dSection.toString().toDouble())
        tvRow12.text = String.format("%.1f", hSection.toString().toDouble())

        tvMTotal.text = String.format("%.1f", (mRow1 + mRow2 + mRow3 + mRow4 + mRow5 + mRow6 + mRow7 + mRow8 + mRow9 + mRow10).toString().toDouble()
        )
        tvDHTotal.text = String.format("%.1f",(tvRow11.text.toString().toFloat() + tvRow12.text.toString().toFloat()).toString().toDouble()
        )
        tvGrandTotal.text = String.format("%.1f", (tvMTotal.text.toString().toFloat() + tvDHTotal.text.toString().toFloat()).toString().toDouble()
        )

    }


    fun settoolbar() {
        val bundle = intent.extras
        back = toolbar!!.findViewById(R.id.ivBack)
        back.setImageResource(R.drawable.ic_arrow_back)
        back.setOnClickListener(this)
        tvHeading = toolbar!!.findViewById(R.id.tvHeading)
        if (bundle != null) {
            tvHeading.setText(bundle.getString("GameName"))
        } else {
            tvHeading.setText("Sheet")
        }
    }

    fun setItemView() {
        tvOne = table!!.findViewById(R.id.tvOne)
        tvTwo = table!!.findViewById(R.id.tvTwo)
        tvThree = table!!.findViewById(R.id.tvThree)
        tvFour = table!!.findViewById(R.id.tvFour)
        tvFive = table!!.findViewById(R.id.tvFive)
        tvSix = table!!.findViewById(R.id.tvSix)
        tvSeven = table!!.findViewById(R.id.tvSeven)
        tvEight = table!!.findViewById(R.id.tvEight)
        tvNine = table!!.findViewById(R.id.tvNine)
        tvTen = table!!.findViewById(R.id.tvTen)
        tvEleven = table!!.findViewById(R.id.tveleven)
        tvTwelve = table!!.findViewById(R.id.tvtwlve)
        tvThirteen = table!!.findViewById(R.id.tvthiteen)
        tvFourteen = table!!.findViewById(R.id.tvFourteen)
        tvFifteen = table!!.findViewById(R.id.tvFifteen)
        tvSixteen = table!!.findViewById(R.id.tvSixteen)
        tvSeventeen = table!!.findViewById(R.id.tvSeventeen)
        tvEighteen = table!!.findViewById(R.id.tvEighteen)
        tvNineteen = table!!.findViewById(R.id.tvNinteen)
        tvTwenty = table!!.findViewById(R.id.tvTwenty)
        tvTwentyOne = table!!.findViewById(R.id.tvtevone)
        tvTwentyTwo = table!!.findViewById(R.id.tvtwentytwo)
        tvTwentyThree = table!!.findViewById(R.id.tvtwentythree)
        tvTwentyFour = table!!.findViewById(R.id.tvtwentyFour)
        tvTwentyFive = table!!.findViewById(R.id.tvtwentyFive)
        tvTwentySix = table!!.findViewById(R.id.tvtwentySix)
        tvTwentySeven = table!!.findViewById(R.id.tvtwentySeven)
        tvTwentyEight = table!!.findViewById(R.id.tvtwentyEight)
        tvTwentyNine = table!!.findViewById(R.id.tvtwentyNine)
        tvThirty = table!!.findViewById(R.id.tvThirty)
        tvThiryOne = table!!.findViewById(R.id.tvthiryone)
        tvThirtyTwo = table!!.findViewById(R.id.tvthirtytwo)
        tvThirtyThree = table!!.findViewById(R.id.tvthirtythree)
        tvThirtyFour = table!!.findViewById(R.id.tvthirtyFour)
        tvThirtyFive = table!!.findViewById(R.id.tvthirtyFive)
        tvThirtySix = table!!.findViewById(R.id.tvthirtySix)
        tvThirtySeven = table!!.findViewById(R.id.tvthirtySeven)
        tvThirtyEight = table!!.findViewById(R.id.tvthirtyEight)
        tvThirtyNine = table!!.findViewById(R.id.tvthirtyNine)
        tvForty = table!!.findViewById(R.id.tvFourty)
        tvFortyOne = table!!.findViewById(R.id.tvFourtyone)
        tvFortyTwo = table!!.findViewById(R.id.tvFortytwo)
        tvFortyThree = table!!.findViewById(R.id.tvFourtythree)
        tvFortyFour = table!!.findViewById(R.id.tvFourtyFour)
        tvFortyFive = table!!.findViewById(R.id.tvFourtyFive)
        tvFortySix = table!!.findViewById(R.id.tvFourtySix)
        tvFortySeven = table!!.findViewById(R.id.tvFourtySeven)
        tvFortyEight = table!!.findViewById(R.id.tvFortyEight)
        tvFortyNine = table!!.findViewById(R.id.tvFourtyNine)
        tvFifty = table!!.findViewById(R.id.tvFifty)
        tvFiftyOne = table!!.findViewById(R.id.tvFiftyone)
        tvFiftyTwo = table!!.findViewById(R.id.tvFiftytwo)
        tvFiftyThree = table!!.findViewById(R.id.tvFiftythree)
        tvFiftyFour = table!!.findViewById(R.id.tvFiftyFour)
        tvFiftyFive = table!!.findViewById(R.id.tvFiftyFive)
        tvFiftySix = table!!.findViewById(R.id.tvFiftySix)
        tvFiftySeven = table!!.findViewById(R.id.tvFiftySeven)
        tvFiftyEight = table!!.findViewById(R.id.tvFiftyEight)
        tvFiftyNine = table!!.findViewById(R.id.tvFiftyNine)
        tvSixty = table!!.findViewById(R.id.tvSixty)
        tvSixtyOne = table!!.findViewById(R.id.tvSixtyone)
        tvSixtyTwo = table!!.findViewById(R.id.tvSixtyTwo)
        tvSixtyThree = table!!.findViewById(R.id.tvSixtyThree)
        tvSixtyFour = table!!.findViewById(R.id.tvSixtyFour)
        tvSixtyFive = table!!.findViewById(R.id.tvSixtyFive)
        tvSixtySix = table!!.findViewById(R.id.tvSixtySix)
        tvSixtySeven = table!!.findViewById(R.id.tvSixtySeven)
        tvSixtyEight = table!!.findViewById(R.id.tvSixtyEight)
        tvSixtyNine = table!!.findViewById(R.id.tvSixtyNine)
        tvSeventy = table!!.findViewById(R.id.tvSeventy)
        tvSeventyOne = table!!.findViewById(R.id.tvSeventyone)
        tvSeventyTwo = table!!.findViewById(R.id.tvSeventytwo)
        tvSeventyThree = table!!.findViewById(R.id.tvSeventythree)
        tvSeventyFour = table!!.findViewById(R.id.tvSeventyFour)
        tvSeventyFive = table!!.findViewById(R.id.tvSeventyFive)
        tvSeventySix = table!!.findViewById(R.id.tvSeventySix)
        tvSeventySeven = table!!.findViewById(R.id.tvSeventySeven)
        tvSeventyEight = table!!.findViewById(R.id.tvSeventyEight)
        tvSeventyNine = table!!.findViewById(R.id.tvSeventyNine)
        tvEighty = table!!.findViewById(R.id.tvEighty)
        tvEightyOne = table!!.findViewById(R.id.tvEightyOne)
        tvEightyTwo = table!!.findViewById(R.id.tvEightyTwo)
        tvEightyThree = table!!.findViewById(R.id.tvEightyThree)
        tvEightyFour = table!!.findViewById(R.id.tvEightyFour)
        tvEightyFive = table!!.findViewById(R.id.tvEightyFive)
        tvEightySix = table!!.findViewById(R.id.tvEightySix)
        tvEightySeven = table!!.findViewById(R.id.tvEightySeven)
        tvEightyEight = table!!.findViewById(R.id.tvEightyEight)
        tvEightyNine = table!!.findViewById(R.id.tvEightyNine)
        tvNinety = table!!.findViewById(R.id.tvNinty)
        tvNinetyOne = table!!.findViewById(R.id.tvNintyone)
        tvNinetyTwo = table!!.findViewById(R.id.tvNintytwo)
        tvNinetyThree = table!!.findViewById(R.id.tvNintythree)
        tvNinetyFour = table!!.findViewById(R.id.tvNintyFour)
        tvNinetyFive = table!!.findViewById(R.id.tvNintyFive)
        tvNinetySix = table!!.findViewById(R.id.tvNintySix)
        tvNinetySeven = table!!.findViewById(R.id.tvNintySeven)
        tvNinetyEight = table!!.findViewById(R.id.tvNintyEight)
        tvNinetyNine = table!!.findViewById(R.id.tvNintyNine)
        tvHundred = table!!.findViewById(R.id.tvHundrad)
        tvd1 = table!!.findViewById(R.id.tvd1)
        tvd2 = table!!.findViewById(R.id.tvd2)
        tvd3 = table!!.findViewById(R.id.tvd3)
        tvd4 = table!!.findViewById(R.id.tvd4)
        tvd5 = table!!.findViewById(R.id.tvd5)
        tvd6 = table!!.findViewById(R.id.tvd6)
        tvd7 = table!!.findViewById(R.id.tvd7)
        tvd8 = table!!.findViewById(R.id.tvd8)
        tvd9 = table!!.findViewById(R.id.tvd9)
        tvd10 = table!!.findViewById(R.id.tvd10)
        tvh1 = table!!.findViewById(R.id.tvh1)
        tvh2 = table!!.findViewById(R.id.tvh2)
        tvh3 = table!!.findViewById(R.id.tvh3)
        tvh4 = table!!.findViewById(R.id.tvh4)
        tvh5 = table!!.findViewById(R.id.tvh5)
        tvh6 = table!!.findViewById(R.id.tvh6)
        tvh7 = table!!.findViewById(R.id.tvh7)
        tvh8 = table!!.findViewById(R.id.tvh8)
        tvh9 = table!!.findViewById(R.id.tvh9)
        tvh10 = table!!.findViewById(R.id.tvh10)
        tvRow1 = table!!.findViewById(R.id.tvRow1)
        tvRow2 = table!!.findViewById(R.id.tvRow2)
        tvRow3 = table!!.findViewById(R.id.tvRow3)
        tvRow4 = table!!.findViewById(R.id.tvRow4)
        tvRow5 = table!!.findViewById(R.id.tvRow5)
        tvRow6 = table!!.findViewById(R.id.tvRow6)
        tvRow7 = table!!.findViewById(R.id.tvRow7)
        tvRow8 = table!!.findViewById(R.id.tvRow8)
        tvRow9 = table!!.findViewById(R.id.tvRow9)
        tvRow10 = table!!.findViewById(R.id.tvRow10)
        tvRow11 = table!!.findViewById(R.id.tvRow11)
        tvRow12 = table!!.findViewById(R.id.tvRow12)
        tvMTotal = table!!.findViewById(R.id.tvMTotal)
        tvDHTotal = table!!.findViewById(R.id.tvDHTotal)
        tvGrandTotal = table!!.findViewById(R.id.tvGrandTotal)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivBack -> finish()
            else -> {
            }
        }
    }
}

