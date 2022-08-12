package com.example.ia.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ia.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goAddMed(View V) {
        Intent intent = new Intent(this, AddMedActivity.class);
        startActivity(intent);
    }

    public void goMedInfo(View v) {
        Intent intent = new Intent(this, MedInfoActivity.class);
        startActivity(intent);
    }
}