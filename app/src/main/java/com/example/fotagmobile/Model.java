package com.example.fotagmobile;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable{
    public String type;
    public boolean FirstLoad;
    public ArrayList<ImageModel> ImageList;
    public ArrayList<ImageModel> SecondList;
    public ArrayList<Integer> listRating;
    public int filterRating;

    Model(){
        type= "";
        FirstLoad = true;
        filterRating = 0;
        ImageList = new ArrayList<ImageModel>();
        SecondList = new ArrayList<ImageModel>();
    }

    public void addImage(int r, int id){
        ImageModel m = new ImageModel(r,id);
        ImageList.add(m);
        SecondList.add(m);
    }

    public void loadImage(){
        Log.d("fotagmobile", "load Image type: "+type);
        if(FirstLoad) {
            type ="load";
            FirstLoad = false;
        }
        else {
            type = ""; //to prevent loading the ten images again
        }
        setChanged();
        notifyObservers();
    }

    public void searchImage(){
        type="search";
    }

    public void clearImage(){
        type="clear";
        setChanged();
        notifyObservers();
    }

    public void updateStar(){
        Log.d("fotagmobile","update star in model");
        setChanged();
        notifyObservers();
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
