package com.example.bnetipartner.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import API.AddedNoteResponse;
import API.Entry;
import API.EntryResponse;
import API.MessageApi;
import API.SessionResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static Repository INSTANCE = null;
    Retrofit retrofit;
    MessageApi messagesApi;
    String session;


    private MutableLiveData<List<Entry>> entryResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<String> sessionResponseLiveData = new MutableLiveData<>();


    private String URL = "http://bnet.i-partner.ru/testAPI/";


    private Repository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messagesApi = retrofit.create(MessageApi.class);

    }

    public static Repository getInstance() {
        return INSTANCE;
    }

    public static void initialize() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
    }

    public String startSession() {
        String s = "";
        Call<SessionResponse> messages = messagesApi.getSession("new_session");
        try {
            s = messages.execute().body().getSession().getNumberSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public void addEntry(String text, String numberSession) {
        Log.d("q", "addEntry: " + text + "" + numberSession);

        Call<AddedNoteResponse> addEntry = messagesApi.addEntry(numberSession, "add_entry", text);
        addEntry.enqueue(new Callback<AddedNoteResponse>() {
            @Override
            public void onResponse(Call<AddedNoteResponse> call, Response<AddedNoteResponse> response) {


            }

            @Override
            public void onFailure(Call<AddedNoteResponse> call, Throwable t) {

                Log.d("q", " " + t);

            }
        });
    }

    public LiveData<List<Entry>> getEntries(String numberSession) {
        messagesApi = retrofit.create(MessageApi.class);
        Call<EntryResponse> getEntry = messagesApi.getEntries(numberSession, "get_entries");

        getEntry.enqueue(new Callback<EntryResponse>() {
            @Override
            public void onResponse(Call<EntryResponse> call, Response<EntryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().get(0) != null) {
                        entryResponseLiveData.setValue(response.body().getData().get(0));
                    }
                } else {

                    Log.d("q", " getEntry" + response.code());

                }

            }

            @Override
            public void onFailure(Call<EntryResponse> call, Throwable t) {

                Log.d("q", " " + t);

            }
        });
        return entryResponseLiveData;
    }


}