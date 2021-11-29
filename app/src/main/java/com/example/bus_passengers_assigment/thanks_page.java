package com.example.bus_passengers_assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class thanks_page extends AppCompatActivity {
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thnks_page);

        enter=findViewById(R.id.enter_btn);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(thanks_page.this,Home_page.class);
                startActivity(i);
            }
        });
    }
}