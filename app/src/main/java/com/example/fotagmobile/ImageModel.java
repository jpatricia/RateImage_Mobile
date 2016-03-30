package com.example.fotagmobile;

public class ImageModel {
    private int imgRating;

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
}
