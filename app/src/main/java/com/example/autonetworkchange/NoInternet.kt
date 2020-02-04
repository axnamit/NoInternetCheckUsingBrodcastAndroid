package com.example.autonetworkchange

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_no_internet.*

class NoInternet : AppCompatActivity() {

    private var connectivityReceiver: ConnectivityReceiver? = null
    var context: Context? = null

    var called: Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        connectivityReceiver = ConnectivityReceiver()
        context = this
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        Utils.getNetBool()?.observe(this, Observer { v ->
            text.text = "jdjdjd" + v.toString()
            if (v==true){
                finish()
            }
        })

    }
}