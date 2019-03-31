package com.example.kushal.wifianalyser;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    boolean isWifi;
    WifiManager wifiManager;
    ToggleButton toggleButton;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables
        wifiManager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        tv=(TextView) findViewById(R.id.displayStrength);
        toggleButton=(ToggleButton) findViewById(R.id.toggleButton);

        //Get Wifi Status
        if(wifiManager.isWifiEnabled()){
            isWifi=true;
            toggleButton.setChecked(isWifi);
        }else{
            isWifi=false;
            toggleButton.setChecked(isWifi);
        }
    }

    //Toggle wifi
    public void toggleWifi(View v){
        if(isWifi){
            isWifi=false;
            wifiManager.setWifiEnabled(false);
            toggleButton.setChecked(isWifi);
            Toast.makeText(this,"WIFI turned OFF!",Toast.LENGTH_SHORT).show();
        }else{
            isWifi=true;
            wifiManager.setWifiEnabled(true);
            toggleButton.setChecked(isWifi);
            Toast.makeText(this,"WIFI turned ON!",Toast.LENGTH_SHORT).show();
        }
    }

    //getWifiStrength
    public void getStrength(View v){
        if(isWifi){
            tv.setText("This will display wifi strength!");
        }else{
            tv.setText("Enable WIFI first!");
        }
    }
}
