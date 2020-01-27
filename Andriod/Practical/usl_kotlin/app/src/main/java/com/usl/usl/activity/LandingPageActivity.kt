package com.usl.usl.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.adapter.GameAdapter
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.upcominggame.UpcomingGameResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Helper
import com.usl.usl.utils.LocalRepositories
import com.usl.usl.utils.Preferences
import org.greenrobot.eventbus.Subscribe
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LandingPageActivity : RegisterAbstractActivity() ,View.OnClickListener{



    var toggle: ActionBarDrawerToggle? = null
    @BindView(R.id.ivLogOut)
    lateinit var ivLogOut: ImageView
    @BindView(R.id.tvPlayerID)
    lateinit var tvPlayerID: TextView
    @BindView(R.id.llSheet)
    lateinit var llSheet: LinearLayout
    @BindView(R.id.drawer_layout)
    lateinit var drawerLayout: DrawerLayout
    @BindView(R.id.nav)
    lateinit var navigation: ImageView
    @BindView(R.id.llMatchedBet)
    lateinit var llMatchedBet: LinearLayout
    @BindView(R.id.rvGame)
    lateinit var rvGame: RecyclerView
    @BindView(R.id.tvLimit)
    lateinit var tvLimit: TextView
    @BindView(R.id.tvNOGame)
    lateinit var tvNOGame: TextView
    @BindView(R.id.llAccount)
    lateinit var llAccount: LinearLayout
    @BindView(R.id.llHistory)
    lateinit var llHistory: LinearLayout
    @BindView(R.id.llContactUs)
    lateinit var llContactUs: LinearLayout

    private var listGameName=ArrayList<String>()
    private var listGameId=ArrayList<Int>()
    private val listGameDate=ArrayList<String>()
    var appUser = AppUser()

    private val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
    val calendar = Calendar.getInstance()
    var mTime: String? = null
    var mHour:Int= 0
    var mMinute:Int = 0
    var gridLayoutManager: GridLayoutManager? = null
    var gameAdapter: GameAdapter? = null
    val ACTION_UPCOMINGGAME:String="upcoming_game"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)

        LocalRepositories().saveAppUser(applicationContext, appUser)
        llSheet.setOnClickListener(this)
        ivLogOut.setOnClickListener(this)
        llMatchedBet.setOnClickListener(this)
        llAccount.setOnClickListener(this)
        llHistory.setOnClickListener(this)
        llContactUs.setOnClickListener(this)


        gridLayoutManager = GridLayoutManager(this@LandingPageActivity, 2, GridLayoutManager.VERTICAL, false)
        rvGame.layoutManager = gridLayoutManager
        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle!!)
        toggle!!.syncState()

        navigation.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        tvPlayerID.text = "User ID  " + Preferences(applicationContext).getInstance(applicationContext)?.getuserID().toString()
        apiUpcominggame()
    }

    private fun apiUpcominggame(){
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog.show()
            ApiCallService.action(applicationContext,ACTION_UPCOMINGGAME)
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Preferences(applicationContext).getInstance(applicationContext)?.setHCounter(1)
        mTime = dateFormat.format(calendar.time).toString()
        val mTimeArry = mTime!!.split(":").toTypedArray()
        mHour = mTimeArry[0].toInt()
        mMinute = mTimeArry[1].toInt()
        Preferences(applicationContext).getInstance(applicationContext)?.setHour(mHour)
        Preferences(applicationContext).getInstance(applicationContext)?.setMinute(mMinute)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        tvLimit.text = "Limit : " + String.format("%.1f",Preferences(applicationContext).getInstance(applicationContext)?.getLimit().toString().toDouble())
    }


    @Subscribe
    fun upcominggame(response: UpcomingGameResponse) {
        //progressDialog.dismiss()
        if (response.status === 200) {
            if (response.games!!.size > 0) {
                for (i in 0 until response.games!!.size) {
                    if (response.games!!.get(i).size > 0) {
                        for (j in 0 until response.games!!.get(i).size) {
                            if (response.games!!.get(i).size> 0) {
                                listGameDate.add(response.dates!!.get(i))
                                listGameName.add(response.games!!.get(i).get(j).start_time!!.substring(11,16).toString() + " to " + response.games!!.get(i).get(j).end_time!!.substring(11,16) + " " + response.games!!.get(i).get(j).name)
                                listGameId.add(response.games!!.get(i).get(j).id)
                            }
                        }
                    }
                }
            }
            upcomingGames(listGameDate, listGameName, listGameId)
        } else {
            Helper().alert(this, response.message, "USL")
        }
    }

    fun upcomingGames(gameDate:ArrayList<String>,gameName:ArrayList<String>,gameId:ArrayList<Int>) {
        val tempgameName=ArrayList<String>()
        val tempgameId=ArrayList<Int>()
        val tempgameDate=ArrayList<String>()
        for (i in gameDate.indices) {
            if (gameName[i].substring(0, 2).toInt() < Preferences(applicationContext).getInstance(applicationContext)?.getHour()!!) {
                if (gameName[i].substring(9,11).toInt() <= Preferences(applicationContext).getInstance(applicationContext)?.getHour()!!) {
                    if (gameName[i].substring(9,11).toInt() == Preferences(applicationContext).getInstance(applicationContext)?.getHour() && Preferences(applicationContext).getInstance(applicationContext)?.getMinute()!! > gameName[i].substring(12, 14).toInt()) {
                    } else if (gameName[i].substring(9,11).toInt() == Preferences(applicationContext).getInstance(applicationContext)?.getHour() && Preferences(applicationContext).getInstance(applicationContext)?.getMinute()!! <= gameName[i].substring(12, 14).toInt()) {
                        tempgameName.add(gameName[i])
                        tempgameDate.add(gameDate[i])
                        tempgameId.add(gameId[i])
                    } else if (gameName[i].substring(9,11).toInt() < Preferences(applicationContext).getInstance(applicationContext)?.getHour()!!) {}
                } else if (gameName[i].substring(9,11).toInt() > Preferences(applicationContext).getInstance(applicationContext)?.getHour()!!) {
                    tempgameName.add(gameName[i])
                    tempgameDate.add(gameDate[i])
                    tempgameId.add(gameId[i])
                }
            } else {
                tempgameName.add(gameName[i])
                tempgameDate.add(gameDate[i])
                tempgameId.add(gameId[i])
            }
        }
        gameAdapter = GameAdapter(this@LandingPageActivity, tempgameDate, tempgameName, tempgameId)
        rvGame.adapter = gameAdapter
        if (tempgameDate.size == 0 && tempgameName.size == 0 && tempgameId.size == 0) {
            tvNOGame.visibility = View.VISIBLE
        } else {
            tvNOGame.visibility = View.GONE
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_landing_page
    }

    @Subscribe
    fun timeout(msg: String?) {
        //progressDialog.dismiss()
        Helper().alert(this, msg, "USL")
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.llSheet -> startActivity(Intent(this@LandingPageActivity,SheetsActivity::class.java))
            R.id.llMatchedBet -> startActivity(Intent(this@LandingPageActivity,MatchedBetActivity::class.java))
            R.id.llAccount -> startActivity(Intent(this@LandingPageActivity,AccountActivity::class.java))
            R.id.llHistory -> startActivity(Intent(this@LandingPageActivity,HistoryActivity::class.java))
            R.id.llContactUs -> startActivity(Intent(this@LandingPageActivity, ContactUsActivity::class.java))
            R.id.ivLogOut -> AlertDialog.Builder(this@LandingPageActivity)
                .setTitle("Logout")
                .setMessage("Are you sure want to Logout?")
                .setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                    Preferences(applicationContext).getInstance(applicationContext)?.setId(0)
                    Preferences(applicationContext).getInstance(applicationContext)?.setUserID("")
                    Preferences(applicationContext).getInstance(applicationContext)?.setPassword("")
                    Preferences(applicationContext).getInstance(applicationContext)?.setAuthToken("")
                    Preferences(applicationContext).getInstance(applicationContext)?.setLimit(0.0f)
                    startActivity(Intent(this@LandingPageActivity, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
            else -> {
            }
        }
    }
}