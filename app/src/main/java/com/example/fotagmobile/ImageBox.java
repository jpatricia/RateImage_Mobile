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

        addStarListener();

        im.addObserver(this);

    }

    public void addStarListener(){
        rating.StarList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 1 clicked");
                rating.removeAllViews();
                im.setRating(1);
                rating.setRating(1);
                im.updateStar();
            }
        });
        rating.StarList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 2 clicked");
                rating.removeAllViews();
                im.setRating(2);
                rating.setRating(2);
                im.updateStar();
            }
        });
        rating.StarList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 3 clicked");
                rating.removeAllViews();
                im.setRating(3);
                rating.setRating(3);
                im.updateStar();
            }
        });
        rating.StarList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 4 clicked");
                rating.removeAllViews();
                im.setRating(4);
                rating.setRating(4);
                im.updateStar();
            }
        });
        rating.StarList.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 5 clicked");
                rating.removeAllViews();
                im.setRating(5);
                rating.setRating(5);
                im.updateStar();
            }
        });


        rating.clearStar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","image clearRating clicked");
                rating.removeAllViews();
                im.setRating(0);
                rating.clearRating();
                im.updateStar();
            }
        });
    }

    public void updateStarImage(){
        Log.d("fotagmobile","update star image");
        rating.drawStar();
        this.addView(rating);
        addStarListener();
    }

    @Override
    public void update(Observable arg0, Object arg1){
        Log.d("fotagmobile","update ImageBox view");
        if(im.updateImgStar){
            Log.d("fotagmobile","update image star to:"+im.getImgRating());
            im.updateImgStar = false;
            this.removeView(rating);
            updateStarImage();
        }
    }

}
