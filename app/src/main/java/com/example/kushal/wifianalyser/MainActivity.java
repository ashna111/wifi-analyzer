package com.example.kushal.wifianalyser;

import android.content.Context;
import android.net.wifi.WifiInfo;
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
            WifiInfo wifiInfo=wifiManager.getConnectionInfo();
            //to check if connected to a network
            if(String.valueOf(wifiInfo.getSupplicantState()).equals("COMPLETED")){
                String content= "SSID :"+wifiInfo.getSSID()+"\nBSSID :"+wifiInfo.getBSSID();
                //get Frequency will only on specific versions of android
                content=content+"\nFrequency :"+wifiInfo.getFrequency()+"\nIP Address:"+wifiInfo.getIpAddress();

                int ss=wifiInfo.getRssi(); // get signal strength
                content=content+"\nRSSI :"+ss+" dBm\n\n";

                String status=getStatus(ss);
                content=content+"Signal Strength : "+status;
                tv.setText(content);
            }else{
                tv.setText("Connect to a network!");
            }
        }else{
            tv.setText("Enable WIFI first!");
        }
    }

    public String getStatus(int DBm){
        if(DBm >= -50) return "Excellent";
        else if (DBm < -50 && DBm >= -60) return "Good";
        else if (DBm < -60 && DBm >= -70) return "Fair";
        else return "Poor";
    }
}
