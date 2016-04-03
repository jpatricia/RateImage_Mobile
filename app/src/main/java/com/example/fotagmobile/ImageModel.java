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
        if(prevRating == 0){
            imgRating=0;
        }else{
            imgRating = prevRating;
        }
        bm = bm_;
        imgID = imageID;

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
