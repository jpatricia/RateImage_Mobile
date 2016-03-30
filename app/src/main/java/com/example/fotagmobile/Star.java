package com.example.fotagmobile;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
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
        clearStar = new ImageButton(context);
        clearStar.setBackgroundResource(R.drawable.close_icon);
        clearStar.setMinimumWidth(10);
        clearStar.setMinimumHeight(10);
        clearStar.setMaxHeight(20);
        clearStar.setMaxWidth(30);
        clearStar.setAdjustViewBounds(true);
        clearStar.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        clearStar.setPadding(15,10,0,0);
        this.addView(clearStar);


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
}
