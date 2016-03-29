package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends LinearLayout implements Observer {
    private Model model;
    private Context context;

    public ImageCollectionView(Context context_, Model model_){
        super(context_);
        context = context_;
        Log.d("fotagmobile", "View Constructor");
        model = model_;
        model.addObserver(this);
        this.setOrientation(LinearLayout.VERTICAL);

    }

    public void loadImages(){
        //create ImageView
        ImageView imageView1 = new ImageView(context);
        imageView1.setImageResource(R.drawable.img1);
        imageView1.setMaxHeight(500);
        imageView1.setMinimumHeight(300);
        imageView1.setMaxWidth(700);
        imageView1.setMinimumWidth(400);
        imageView1.setAdjustViewBounds(true);
        imageView1.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView1.setPadding(0, 10, 0, 50);
        this.addView(imageView1);

        ImageView imageView2 = new ImageView(context);
        imageView2.setImageResource(R.drawable.img2);
        imageView2.setMaxHeight(500);
        imageView2.setMinimumHeight(300);
        imageView2.setMaxWidth(700);
        imageView2.setMinimumWidth(400);
        imageView2.setAdjustViewBounds(true);
        imageView2.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView2.setPadding(0, 10, 0, 50);
        this.addView(imageView2);

        ImageView imageView3 = new ImageView(context);
        imageView3.setImageResource(R.drawable.img3);
        imageView3.setMaxHeight(500);
        imageView3.setMinimumHeight(300);
        imageView3.setMaxWidth(700);
        imageView3.setMinimumWidth(400);
        imageView3.setAdjustViewBounds(true);
        imageView3.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView3.setPadding(0, 10, 0, 50);
        this.addView(imageView3);

        ImageView imageView4 = new ImageView(context);
        imageView4.setImageResource(R.drawable.img4);
        imageView4.setMaxHeight(500);
        imageView4.setMinimumHeight(300);
        imageView4.setMaxWidth(700);
        imageView4.setMinimumWidth(400);
        imageView4.setAdjustViewBounds(true);
        imageView4.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView4.setPadding(0, 10, 0, 50);
        this.addView(imageView4);

        ImageView imageView5 = new ImageView(context);
        imageView5.setImageResource(R.drawable.img5);
        imageView5.setMaxHeight(500);
        imageView5.setMinimumHeight(300);
        imageView5.setMaxWidth(700);
        imageView5.setMinimumWidth(400);
        imageView5.setAdjustViewBounds(true);
        imageView5.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView5.setPadding(0, 10, 0, 50);
        this.addView(imageView5);

        ImageView imageView6 = new ImageView(context);
        imageView6.setImageResource(R.drawable.img6);
        imageView6.setMaxHeight(500);
        imageView6.setMinimumHeight(300);
        imageView6.setMaxWidth(700);
        imageView6.setMinimumWidth(400);
        imageView6.setAdjustViewBounds(true);
        imageView6.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView6.setPadding(0, 10, 0, 50);
        this.addView(imageView6);

        ImageView imageView7 = new ImageView(context);
        imageView7.setImageResource(R.drawable.img7);
        imageView7.setMaxHeight(500);
        imageView7.setMinimumHeight(300);
        imageView7.setMaxWidth(700);
        imageView7.setMinimumWidth(400);
        imageView7.setAdjustViewBounds(true);
        imageView7.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView7.setPadding(0, 10, 0, 50);
        this.addView(imageView7);

        ImageView imageView8 = new ImageView(context);
        imageView8.setImageResource(R.drawable.img8);
        imageView8.setMaxHeight(500);
        imageView8.setMinimumHeight(300);
        imageView8.setMaxWidth(700);
        imageView8.setMinimumWidth(400);
        imageView8.setAdjustViewBounds(true);
        imageView8.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView8.setPadding(0, 10, 0, 50);
        this.addView(imageView8);

        ImageView imageView9 = new ImageView(context);
        imageView9.setImageResource(R.drawable.img9);
        imageView9.setMaxHeight(500);
        imageView9.setMinimumHeight(300);
        imageView9.setMaxWidth(700);
        imageView9.setMinimumWidth(400);
        imageView9.setAdjustViewBounds(true);
        imageView9.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView9.setPadding(0, 10, 0, 50);
        this.addView(imageView9);

        ImageView imageView10 = new ImageView(context);
        imageView10.setImageResource(R.drawable.img10);
        imageView10.setMaxHeight(500);
        imageView10.setMinimumHeight(300);
        imageView10.setMaxWidth(700);
        imageView10.setMinimumWidth(400);
        imageView10.setAdjustViewBounds(true);
        imageView10.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        imageView10.setPadding(0, 10, 0, 50);
        this.addView(imageView10);

        imageView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void update(Observable observable, Object data) {
        Log.d("fotagmobile", "update View");
        if(model.type.equals("load")){
            loadImages();
        }else if(model.type.equals("clear")){
            this.removeAllViews();
        }
    }
}
