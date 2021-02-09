package com.example.openadministratordialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.openadministratordialog.services.MyAdmin;

public class MainActivity extends AppCompatActivity {
    Button btnGetAdmin;
    DevicePolicyManager deviceManger;
    ActivityManager activityManager;
    ComponentName compName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetAdmin = findViewById(R.id.btn_get_admin);
        btnGetAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compName = new ComponentName(MainActivity.this, MyAdmin.class);
                deviceManger = (DevicePolicyManager)getSystemService(
                        Context.DEVICE_POLICY_SERVICE);
                activityManager = (ActivityManager)getSystemService(
                        Context.ACTIVITY_SERVICE);
                boolean active = deviceManger.isAdminActive(compName);
                if(active){
                    Toast.makeText(MainActivity.this, "we are active", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent().setComponent(new ComponentName("com.android.settings", "com.android.settings.DeviceAdminSettings")));
                }
            }
        });
    }
}