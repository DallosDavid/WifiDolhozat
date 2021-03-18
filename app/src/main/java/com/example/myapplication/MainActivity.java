package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

     TextView textView;
     BottomNavigationView bottomNavigationView;

     WifiInfo wifiInfo;
     WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.wifion:
                        wifiManager.setWifiEnabled(true);
                        textView.setText("Wifi bekapcsolva");
                        break;
                    case R.id.wifioff:
                        wifiManager.setWifiEnabled(false);
                        textView.setText("Wifi kikapcsolva");
                        break;
                    case R.id.wifiInfo:
                        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                        if (netInfo.isConnected())
                        {
                            int ip= wifiInfo.getIpAddress();
                            textView.setText("Csatlakozik wifi hálózathoz");
                            /*String a = Formatter.formatIpAddres
                            s(ip);
                            textView.setText(a);*/
                        }else
                        {
                            textView.setText("Nem csatlakozik wifi hálózathoz");
                        }


                        break;
                }

                return true;
            }
        });

    }

    private void init() {
        textView = (TextView)findViewById(R.id.tesxtwifi);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
    }
}