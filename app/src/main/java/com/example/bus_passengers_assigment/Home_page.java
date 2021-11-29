package com.example.bus_passengers_assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_page extends AppCompatActivity {
    Button insertpagebtn,view_bttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        insertpagebtn=findViewById(R.id.insert_page_bttn);
        view_bttn=findViewById(R.id.view_bttn);

        insertpagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_page.this,Enter_detail.class);
                startActivity(i);
            }
        });
        view_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_page.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}