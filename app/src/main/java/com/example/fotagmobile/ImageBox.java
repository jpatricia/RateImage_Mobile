package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import java.util.Observable;
import java.util.Observer;

public class ImageBox extends LinearLayout implements Observer {
    private Model model;
    private Context context;
    private ImageModel im;
    private Star rating;
    private int id;

    public ImageBox(Model model_,Context context_,int id_){
        super(context_);
        context = context_;
        model = model_;
        rating = new Star(context);
        im = new ImageModel(0);
        id = id_;

        this.setOrientation(VERTICAL);

        ImageView iv = new ImageView(context);
        iv.setImageResource(id);
        iv.setMaxHeight(500);
        iv.setMinimumHeight(300);
        iv.setMaxWidth(700);
        iv.setMinimumWidth(400);
        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        iv.setPadding(0, 30, 0, 20);

        rating.setGravity(Gravity.CENTER_HORIZONTAL);
        this.addView(iv);
        this.addView(rating);
    }

    @Override
    public void update(Observable arg0, Object arg1){
        Log.d("fotagmobile","update ImageBox view");
    }

}
