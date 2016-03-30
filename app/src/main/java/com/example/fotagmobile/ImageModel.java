package com.example.fotagmobile;

import android.util.Log;

import java.util.Observable;

public class ImageModel extends Observable {
    private int imgRating;
    public boolean updateImgStar;

    ImageModel(int prevRating){
        if(prevRating == 0){
            imgRating=0;
        }else{
            imgRating = prevRating;
        }
    }

    public void setRating(int r){
        imgRating = r;
    }

    public void updateStar(){
        Log.d("fotagmobile", "update star in model");
        updateImgStar = true;
        setChanged();
        notifyObservers();
    }

    public int getImgRating(){
        return imgRating;
    }
}
