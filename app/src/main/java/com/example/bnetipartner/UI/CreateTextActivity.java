package com.example.bnetipartner.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bnetipartner.R;
import com.example.bnetipartner.ViewModel.ViewModelData;

public class CreateTextActivity extends AppCompatActivity {
    EditText editText;
    ViewModelData model;
    Intent intent;
    String session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text);
        session = getIntent().getStringExtra("Session");

        editText = findViewById(R.id.createText);
        editText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.save_text:
                Log.d("q", "onOptionsItemSelected: " + session);
                Log.d("q", "onOptionsItemSelected: " + editText.getText().toString());
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Введите текст", Toast.LENGTH_LONG).show();

                } else {
                    model = new ViewModelProvider(this).get(ViewModelData.class);
                    model.addEntry(session, editText.getText().toString());
                    startActivity(new Intent(this, MainActivity.class));
                }


                return true;
            case R.id.delete_text:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}

