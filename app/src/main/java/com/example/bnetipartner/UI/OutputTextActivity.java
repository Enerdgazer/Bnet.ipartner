package com.example.bnetipartner.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bnetipartner.R;

public class OutputTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_text);
        TextView text = (TextView) findViewById(R.id.TextOutput);
        TextView textd = (TextView) findViewById(R.id.dateOutput);
        Intent intent = getIntent();
        text.setText(intent.getStringExtra("OutText"));
        textd.setText(intent.getStringExtra("OutDate"));
    }
}