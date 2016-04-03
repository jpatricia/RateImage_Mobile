package com.example.fotagmobile;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Observable;

public class ImageModel extends Observable {
    private int imgRating;
    public boolean updateImgStar;
    public int imgID;
    private Bitmap bm;

    ImageModel(int prevRating, int imageID, Bitmap bm_){

        imgRating = prevRating;
        bm = bm_;
        imgID = imageID;
//        Log.d("fotagmobile","IMAGE MODEL CONSTRUCTOR");
//        Log.d("fotagmobile","imgRating: "+imgRating);
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

    public Bitmap getBitmap(){
        return bm;
    }

    public int getImgRating(){
        return imgRating;
    }
}
