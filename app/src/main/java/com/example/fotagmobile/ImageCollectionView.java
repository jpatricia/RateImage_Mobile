package com.example.fotagmobile;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
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

        createBoxes();
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

    public void createBoxes(){
        box = new ArrayList<ImageBox>();
        ImageBox b1 = new ImageBox(context,R.drawable.img1);
        ImageBox b2 = new ImageBox(context,R.drawable.img2);
        ImageBox b3 = new ImageBox(context,R.drawable.img3);
        ImageBox b4 = new ImageBox(context,R.drawable.img4);
        ImageBox b5 = new ImageBox(context,R.drawable.img5);
        ImageBox b6 = new ImageBox(context,R.drawable.img6);
        ImageBox b7 = new ImageBox(context,R.drawable.img7);
        ImageBox b8 = new ImageBox(context,R.drawable.img8);
        ImageBox b9 = new ImageBox(context,R.drawable.img9);
        ImageBox b10 = new ImageBox(context,R.drawable.img10);


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
    }

    public void loadImages(){
        Log.d("fotagmobile","box size: "+box.size());
        if(box.size()==0) {
            Log.d("fotagmobile","box size is 0");
            createBoxes();
        }

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("fotagmobile","PORTRAIT LOAD");
        }else{
            Log.d("fotagmobile", "LANDSCAPE LOAD");
        }

        for(int i=0;i<box.size();i++){
            model.addImage(box.get(i).getRating(),box.get(i).getID());
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

    }

    public void filterImages(int ratingFilter){
        Log.d("fotagmobile","in filterImages method");
        this.removeAllViews();
        ArrayList<ImageModel> list = new ArrayList<ImageModel>();
        for(int i=0;i<model.ImageList.size();i++){
            list.add(model.ImageList.get(i));
        }
        Log.d("fotagmobile","num images before filter: "+list.size());
        Log.d("fotagmobile","filter to: "+ratingFilter);
        if(ratingFilter!=0){
            for(int i=list.size()-1;i>=0;i--){
                int imgrating = list.get(i).getImgRating();
                if(imgrating < ratingFilter){
                    list.remove(i);
                }
            }
        }
        Log.d("fotagmobile","after filter,list size: "+list.size());
        model.SecondList.clear();
        for(int i=0;i<list.size();i++){
            model.SecondList.add(list.get(i));
        }
        reDraw();
    }

    public void reDraw(){
        int size = model.SecondList.size();
        if(size!=0){
            for(int j=0;j<box.size();j++){
                for(int i=0;i<model.SecondList.size();i++){
                    if(model.SecondList.get(i).imgID == box.get(j).getID()){
                        this.addView(box.get(j));
                        break;
                    }
                }
            }
        }
    }

    public void update(Observable observable, Object data) {
        Log.d("fotagmobile", "update View");
        if(model.type.equals("load")){
            Log.d("fotagmobile", "load images update");
            loadImages();
        }else if(model.type.equals("clear")){
            this.removeAllViews();
            model.FirstLoad = true;
        }else if(model.type.equals("filter")){
            if(model.ImageList.size()!=0){
                for(int i=0;i<box.size();i++){
                    if(box.get(i).getID() == model.ImageList.get(i).imgID) {
                        Log.d("fotagmobile","box ID: "+box.get(i).getID()+" rating: " + box.get(i).getRating());
                        model.ImageList.get(i).setRating(box.get(i).getRating());
                        Log.d("fotagmobile", "image ID: " + model.ImageList.get(i).imgID + " rating: " + model.ImageList.get(i).getImgRating());
                    }
                }
                filterImages(model.filterRating);
            }
        }
    }
}
