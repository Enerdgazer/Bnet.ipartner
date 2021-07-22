package com.example.bnetipartner.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.bnetipartner.Repository.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import API.Entry;

public class ViewModelData extends androidx.lifecycle.ViewModel {
    Repository repository = Repository.getInstance();
    LiveData<List<Entry>> entry;


    public String getSession() {
        AtomicReference<String> atomicString = new AtomicReference<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                atomicString.set(repository.startSession());
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicString.get();
    }

    public LiveData<List<Entry>> getEntry(String session) {
        if (entry == null) {
            entry = repository.getEntries(session);
        }
        return entry;


    }

    public void addEntry(String session, String body) {
        repository.addEntry(body, session);
    }


}



