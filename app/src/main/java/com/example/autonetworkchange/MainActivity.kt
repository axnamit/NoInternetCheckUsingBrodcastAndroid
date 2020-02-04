package com.example.autonetworkchange

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {
    private var connectivityReceiver: ConnectivityReceiver? = null
    var context: Context? = null

    var called: Boolean? = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        connectivityReceiver = ConnectivityReceiver()
        context = this
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        text.text = isConnected.toString()

        if (isConnected == false) {
            if (called == true) {
                called = false
                startActivity(Intent(this@MainActivity, NoInternet::class.java))
            }
        }

        Utils.setNetBool(isConnected)
    }


    override fun onResume() {
        super.onResume()
        called = true
        ConnectivityReceiver.connectivityReceiverListener = this
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        context = null
        //swipeRefreshLayout = null

        ConnectivityReceiver.connectivityReceiverListener = this

/*
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver)
        }*/

    }

    override fun onPause() {
        super.onPause()
        ConnectivityReceiver.connectivityReceiverListener = this
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver)
            //connectivityReceiver = null
        }


    }
}
