package com.jjasan2.application_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String PERMISSION = "edu.uic.cs478.fall22.mp3";
    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view){
        action = (view.getId() == R.id.button_1) ? "Attractions" : "Hotels";
        checkPermissionAndBroadcast();
    }

    public void checkPermissionAndBroadcast(){
        if(checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED)
            sendBroadcast();
        else
            requestPermissions(new String[] {PERMISSION}, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendBroadcast();
        } else {
            Toast.makeText(this,"Grant permission to send broadcast", Toast.LENGTH_LONG).show();
        }
    }

    public void sendBroadcast(){
        // Toast to show the type of broadcast
        Toast.makeText(getApplicationContext(), action, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }
}