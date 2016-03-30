package com.example.fotagmobile;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Star extends LinearLayout{
    private boolean[] filled;
    private Context context;
    public ArrayList<ImageButton> StarList;
    public ImageButton star;
    public ImageButton clearStar;

    Star(Context context_){
        super(context_);
        context = context_;
        this.setOrientation(HORIZONTAL);

        StarList = new ArrayList<ImageButton>(6);
        filled = new boolean[6];

        for (int i = 0; i < 6; i++) {
            Log.d("fotagmobile", "init star");
            filled[i] = false; //empty star

            star = new ImageButton(context);
            star.setBackgroundResource(R.drawable.star_empty);
            star.setMinimumWidth(70);
            star.setMinimumHeight(50);
            star.setMaxHeight(80);
            star.setMaxWidth(50);
            star.setAdjustViewBounds(true);
            star.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            StarList.add(star);
            if(i>0){ //dont want to print when i=0
                this.addView(star);
            }
        }
        addClearButton();


        //display the star
//        for(int i=0;i<6;i++){
//            StarList.get(i).setMaxHeight(50);
//            StarList.get(i).setMinimumHeight(30);
//            StarList.get(i).setMaxWidth(50);
//            StarList.get(i).setMinimumWidth(30);
//            StarList.get(i).setAdjustViewBounds(true);
//            StarList.get(i).setLayoutParams(new LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    LayoutParams.MATCH_PARENT));
//            StarList.get(i).setPadding(0, 10, 0, 50);
//            this.addView(StarList.get(i));
//        }
    }

    public void addClearButton(){
        clearStar = new ImageButton(context);
        clearStar.setBackgroundResource(R.drawable.close_icon);
        clearStar.setMinimumWidth(30);
        clearStar.setMinimumHeight(30);
        clearStar.setMaxHeight(40);
        clearStar.setMaxWidth(40);
        clearStar.setAdjustViewBounds(true);
        clearStar.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        clearStar.setPadding(15,10,0,0);
        this.addView(clearStar);
    }

    public Star drawStar(){
        StarList.clear();
        for(int i=0;i<6;i++){
            star = new ImageButton(context);
            if(filled[i]){
                Log.d("fotagmobile","filled is true");
                star.setBackgroundResource(R.drawable.star_full);
            }else{
                Log.d("fotagmobile","filled is false");
                star.setBackgroundResource(R.drawable.star_empty);
            }
            star.setMinimumWidth(70);
            star.setMinimumHeight(50);
            star.setMaxHeight(80);
            star.setMaxWidth(50);
            star.setAdjustViewBounds(true);
            star.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            StarList.add(star);
            if(i>0){ //dont want to print when i=0
                Log.d("fotagmobile","not zero");
                this.addView(star);
            }
        }
        addClearButton();
        return this;
    }

    public void setRating(int rate){
        Log.d("fotagmobile", "setRating: " + rate);
        if(filled[rate]){
            //rating is changed from full to empty
            for(int i = rate+1;i<filled.length;i++){
                filled[i] = false;
            }
        }else{
            for(int i=1;i<=rate;i++){
                filled[i] = true;
            }
        }
        //drawStar();
    }

    public void clearRating(){
        Log.d("fotagmobile", "clearRating");
        for(int i=1;i<6;i++){
            filled[i] = false;
        }
    }
}
