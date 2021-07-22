package com.example.bnetipartner.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnetipartner.Adapter.AdapterForDataOutput;
import com.example.bnetipartner.Adapter.RecyclerViewClickListener;
import com.example.bnetipartner.NetWork.NetworkWork;
import com.example.bnetipartner.NetWork.NotLan;
import com.example.bnetipartner.R;
import com.example.bnetipartner.ViewModel.ViewModelData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import API.Entry;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    final String SAVED_SESSION = "saveSession";
    SharedPreferences sPref;
    String numberSession = "not_session";
    ViewModelData model;
    AdapterForDataOutput adapter;
    DialogFragment dialogFragment;
    boolean lanWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lanWork = NetworkWork.isOnline(this);
        if (lanWork) {

            model = new ViewModelProvider(this).get(ViewModelData.class);
            numberSession = loadSession();

            if (numberSession.equals("not_session")) {
                saveSassion(model.getSession());
                numberSession = loadSession();
            } else {
                initRecycle();
            }
            Log.d("q", "onCreate: " + numberSession);

            FloatingActionButton floatingActionButton = findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(MainActivity.this, CreateTextActivity.class);
                    intent.putExtra("Session", numberSession);
                    startActivity(intent);
                }
            });
        } else {
            dialogFragment = new NotLan();
            dialogFragment.show(getSupportFragmentManager(), "frag");
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    private void initRecycle() {

        RecyclerView recyclerView = findViewById(R.id.list);
        adapter = new AdapterForDataOutput(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        LiveData<List<Entry>> liveDataListEntry = model.getEntry(numberSession);
        liveDataListEntry.observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                adapter.setData(entries);

            }
        });
    }

    private String loadSession() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedNumberSession = sPref.getString(SAVED_SESSION, "not_session");
        return savedNumberSession;

    }

    private void saveSassion(String session) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_SESSION, session);
        ed.commit();
    }


    @Override
    public void recyclerViewListClicked(String text, String date) {
        Intent intent = new Intent(this, OutputTextActivity.class);

        intent.putExtra("OutText", text);
        intent.putExtra("OutDate", date);
        startActivity(intent);
    }
}
