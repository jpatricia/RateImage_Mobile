package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends LinearLayout implements Observer {
    private Model model;
    private Context context;
    private ArrayList<Integer> loadList;
    private ArrayList<ImageBox> box;

    public ImageCollectionView(Context context_, Model model_){
        super(context_);
        context = context_;
        Log.d("fotagmobile", "View Constructor");
        model = model_;

        box = new ArrayList<ImageBox>();
        ImageBox b1 = new ImageBox(model,context,R.drawable.img1);
        ImageBox b2 = new ImageBox(model,context,R.drawable.img2);
        ImageBox b3 = new ImageBox(model,context,R.drawable.img3);
        ImageBox b4 = new ImageBox(model,context,R.drawable.img4);
        ImageBox b5 = new ImageBox(model,context,R.drawable.img5);
        ImageBox b6 = new ImageBox(model,context,R.drawable.img6);
        ImageBox b7 = new ImageBox(model,context,R.drawable.img7);
        ImageBox b8 = new ImageBox(model,context,R.drawable.img8);
        ImageBox b9 = new ImageBox(model,context,R.drawable.img9);
        ImageBox b10 = new ImageBox(model,context,R.drawable.img10);

        box.add(b1);
        box.add(b2);
        box.add(b3);
        box.add(b4);
        box.add(b5);
        box.add(b6);
        box.add(b7);
        box.add(b8);
        box.add(b9);
        box.add(b10);
//        loadList = new ArrayList<Integer>();
//        loadList.add(R.drawable.img1);
//        loadList.add(R.drawable.img2);
//        loadList.add(R.drawable.img3);
//        loadList.add(R.drawable.img4);
//        loadList.add(R.drawable.img5);
//        loadList.add(R.drawable.img6);
//        loadList.add(R.drawable.img7);
//        loadList.add(R.drawable.img8);
//        loadList.add(R.drawable.img9);
//        loadList.add(R.drawable.img10);
        model.addObserver(this);
        this.setOrientation(LinearLayout.VERTICAL);

    }

    public void loadImages(){
        for(int i=0;i<box.size();i++){
            this.addView(box.get(i));
        }
        //create ImageView
//        for(int i=0;i<loadList.size();i++){
//            ImageView iv = new ImageView(context);
//            iv.setImageResource(loadList.get(i));
//            iv.setMaxHeight(500);
//            iv.setMinimumHeight(300);
//            iv.setMaxWidth(700);
//            iv.setMinimumWidth(400);
//            iv.setAdjustViewBounds(true);
//            iv.setLayoutParams(new LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    LayoutParams.MATCH_PARENT));
//            iv.setPadding(0, 10, 0, 20);
//            this.addView(iv);

//            Star s = new Star(context);
//            this.addView(s);

 //       }


//
//        ImageView imageView1 = new ImageView(context);
//        imageView1.setImageResource(R.drawable.img1);
//        imageView1.setMaxHeight(500);
//        imageView1.setMinimumHeight(300);
//        imageView1.setMaxWidth(700);
//        imageView1.setMinimumWidth(400);
//        imageView1.setAdjustViewBounds(true);
//        imageView1.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView1.setPadding(0, 10, 0, 50);
//        this.addView(imageView1);
//
//        ImageView imageView2 = new ImageView(context);
//        imageView2.setImageResource(R.drawable.img2);
//        imageView2.setMaxHeight(500);
//        imageView2.setMinimumHeight(300);
//        imageView2.setMaxWidth(700);
//        imageView2.setMinimumWidth(400);
//        imageView2.setAdjustViewBounds(true);
//        imageView2.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView2.setPadding(0, 10, 0, 50);
//        this.addView(imageView2);
//
//        ImageView imageView3 = new ImageView(context);
//        imageView3.setImageResource(R.drawable.img3);
//        imageView3.setMaxHeight(500);
//        imageView3.setMinimumHeight(300);
//        imageView3.setMaxWidth(700);
//        imageView3.setMinimumWidth(400);
//        imageView3.setAdjustViewBounds(true);
//        imageView3.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView3.setPadding(0, 10, 0, 50);
//        this.addView(imageView3);
//
//        ImageView imageView4 = new ImageView(context);
//        imageView4.setImageResource(R.drawable.img4);
//        imageView4.setMaxHeight(500);
//        imageView4.setMinimumHeight(300);
//        imageView4.setMaxWidth(700);
//        imageView4.setMinimumWidth(400);
//        imageView4.setAdjustViewBounds(true);
//        imageView4.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView4.setPadding(0, 10, 0, 50);
//        this.addView(imageView4);
//
//        ImageView imageView5 = new ImageView(context);
//        imageView5.setImageResource(R.drawable.img5);
//        imageView5.setMaxHeight(500);
//        imageView5.setMinimumHeight(300);
//        imageView5.setMaxWidth(700);
//        imageView5.setMinimumWidth(400);
//        imageView5.setAdjustViewBounds(true);
//        imageView5.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView5.setPadding(0, 10, 0, 50);
//        this.addView(imageView5);
//
//        ImageView imageView6 = new ImageView(context);
//        imageView6.setImageResource(R.drawable.img6);
//        imageView6.setMaxHeight(500);
//        imageView6.setMinimumHeight(300);
//        imageView6.setMaxWidth(700);
//        imageView6.setMinimumWidth(400);
//        imageView6.setAdjustViewBounds(true);
//        imageView6.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView6.setPadding(0, 10, 0, 50);
//        this.addView(imageView6);
//
//        ImageView imageView7 = new ImageView(context);
//        imageView7.setImageResource(R.drawable.img7);
//        imageView7.setMaxHeight(500);
//        imageView7.setMinimumHeight(300);
//        imageView7.setMaxWidth(700);
//        imageView7.setMinimumWidth(400);
//        imageView7.setAdjustViewBounds(true);
//        imageView7.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView7.setPadding(0, 10, 0, 50);
//        this.addView(imageView7);
//
//        ImageView imageView8 = new ImageView(context);
//        imageView8.setImageResource(R.drawable.img8);
//        imageView8.setMaxHeight(500);
//        imageView8.setMinimumHeight(300);
//        imageView8.setMaxWidth(700);
//        imageView8.setMinimumWidth(400);
//        imageView8.setAdjustViewBounds(true);
//        imageView8.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView8.setPadding(0, 10, 0, 50);
//        this.addView(imageView8);
//
//        ImageView imageView9 = new ImageView(context);
//        imageView9.setImageResource(R.drawable.img9);
//        imageView9.setMaxHeight(500);
//        imageView9.setMinimumHeight(300);
//        imageView9.setMaxWidth(700);
//        imageView9.setMinimumWidth(400);
//        imageView9.setAdjustViewBounds(true);
//        imageView9.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView9.setPadding(0, 10, 0, 50);
//        this.addView(imageView9);
//
//        ImageView imageView10 = new ImageView(context);
//        imageView10.setImageResource(R.drawable.img10);
//        imageView10.setMaxHeight(500);
//        imageView10.setMinimumHeight(300);
//        imageView10.setMaxWidth(700);
//        imageView10.setMinimumWidth(400);
//        imageView10.setAdjustViewBounds(true);
//        imageView10.setLayoutParams(new LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        imageView10.setPadding(0, 10, 0, 50);
//        this.addView(imageView10);

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
