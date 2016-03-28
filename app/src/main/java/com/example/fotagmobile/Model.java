package com.example.fotagmobile;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable{
    public String type;
    public ArrayList<ImageModel> ImageList;
    Model(){
        type= "";
    }

    public void loadImage(){
        Log.d("fotagmobile", "search Image");
        if(type.equals("")) type ="load";
        else type = ""; //to prevent loading the ten images again
        setChanged();
        notifyObservers();
    }

    public void searchImage(){
        type="search";
    }

    public void clearImage(){
        type="clear";
    }

    // Observer methods
    @Override
    public void addObserver(Observer observer) {
        Log.d("fotagmobile", "Model: Observer added");
        super.addObserver(observer);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    public void notifyObservers() {
        Log.d("fotagmobile", "Model: Observers notified");
        super.notifyObservers();
    }

    @Override
    protected void setChanged() {
        super.setChanged();
    }

    @Override
    protected void clearChanged() {
        super.clearChanged();
    }
}
