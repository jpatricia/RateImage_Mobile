package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
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
    }

    public void loadImages(){
        //create ImageView
        ImageView imageView1 = new ImageView(context);
        imageView1.setImageResource(R.drawable.img1);
        imageView1.getAdjustViewBounds();
        imageView1.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        this.addView(imageView1);

        ImageView imageView2 = new ImageView(context);
        imageView2.setImageResource(R.drawable.img2);
        imageView2.getAdjustViewBounds();
        imageView2.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        this.addView(imageView2);

        ImageView imageView3 = new ImageView(context);
        imageView3.setImageResource(R.drawable.img3);
        imageView3.getAdjustViewBounds();
        imageView3.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        this.addView(imageView3);
    }

    public void update(Observable observable, Object data) {
        Log.d("fotagmobile", "update View");
        if(model.type == "load"){
            loadImages();
        }
    }
}
