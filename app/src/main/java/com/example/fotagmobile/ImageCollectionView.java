package com.example.fotagmobile;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends LinearLayout implements Observer {
    private Model model;
    private Context context;
    private ArrayList<ImageBox> box;
    private ArrayList<ImageBox> box2;
    private GridView gr;
    private ImageAdapter adapter;
    private Bitmap bmp;
    private String loadType;
    public Integer bmpID;

    public ImageCollectionView(Context context_, Model model_,GridView gridv,ImageAdapter ia){
        super(context_);
        context = context_;
        Log.d("fotagmobile", "View Constructor");
        model = model_;
        gr = gridv;
        adapter = ia;
        loadType ="";
        bmpID =0;

        box = new ArrayList<ImageBox>();
        box2 = new ArrayList<ImageBox>(box);

        model.addObserver(this);

        this.setOrientation(LinearLayout.VERTICAL);

    }

    public void createBoxes(){

        ImageBox b1 = new ImageBox(context,0,R.drawable.img1,null);
        ImageBox b2 = new ImageBox(context,0,R.drawable.img2,null);
        ImageBox b3 = new ImageBox(context,0,R.drawable.img3,null);
        ImageBox b4 = new ImageBox(context,0,R.drawable.img4,null);
        ImageBox b5 = new ImageBox(context,0,R.drawable.img5,null);
        ImageBox b6 = new ImageBox(context,0,R.drawable.img6,null);
        ImageBox b7 = new ImageBox(context,0,R.drawable.img7,null);
        ImageBox b8 = new ImageBox(context,0,R.drawable.img8,null);
        ImageBox b9 = new ImageBox(context,0,R.drawable.img9,null);
        ImageBox b10 = new ImageBox(context,0,R.drawable.img10,null);

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
        Log.d("fotagmobile","loadImages portrait function");
        this.removeAllViews();
        if(loadType.equals("loadButton")){
            Log.d("fotagmobile", "loadbButton image");
            createBoxes();
//            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
//            Log.d("fotagmobile","box size: "+box.size());
            int start =0;
            if(model.ImageList.size()!=0){
                start= model.ImageList.size();
                for(int i=0;i<start;i++){
                    this.addView(box.get(i));
                }
            }
            for(int i=start;i<box.size();i++){
                model.addImage(box.get(i).getRating(),box.get(i).getID(),box.get(i).getBitmap());
                this.addView(box.get(i));
            }

            loadType ="";
        }else if(loadType.equals("loadURI")){
            Log.d("fotagmobile", "load URI image");
//            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
//            Log.d("fotagmobile", "box size: " + box.size());
            ImageBox ib = new ImageBox(context,0,bmpID,bmp);
            box.add(ib);
            bmpID++;
            int start=0;
            if(model.ImageList.size()!=0){
                start= model.ImageList.size();
                for(int i=0;i<start;i++){
                    this.addView(box.get(i));
                }

            }
            for(int i=start;i<box.size();i++){
                model.addImage(box.get(i).getRating(),box.get(i).getID(),box.get(i).getBitmap());
                this.addView(box.get(i));
            }
            loadType ="";
        }

    }

//    public ArrayList<ImageBox> getBox(){
//        return box;
//    }

    public void loadGrid(){
        Log.d("fotagmobile","loadGrid function");
        if(loadType.equals("loadButton")){
            Log.d("fotagmobile","loadButton image");
            createBoxes();
            int start =0;
            if(model.ImageList.size()!=0){
                start = model.ImageList.size();
            }
            for(int i=start;i<box.size();i++) {
                model.addImage(box.get(i).getRating(), box.get(i).getID(),box.get(i).getBitmap());
            }
            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
            Log.d("fotagmobile", "box size: " + box.size());
            loadType="";
        }else if(loadType.equals("loadURI")){
            Log.d("fotagmobile","loadURI image");

            ImageBox ib = new ImageBox(context,0,bmpID,bmp);
            box.add(ib);
            bmpID++;

            int start=0;
            if(model.ImageList.size()!=0){
                start = model.ImageList.size();
            }
            for(int i=start;i<box.size();i++) {
                model.addImage(box.get(i).getRating(), box.get(i).getID(),box.get(i).getBitmap());
            }
            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
            Log.d("fotagmobile", "box size: " + box.size());
            loadType="";
        }

        //clear everything before redraw
//        gr.setAdapter(null);
//        adapter.listImage.clear();
//        adapter.checkID.clear();
//        adapter.counter=0;

        gr.setAdapter(adapter);
        if(adapter==null){
            Log.d("fotagmobile","adapter is null");
            //adapter = new ImageAdapter(context,box);
            adapter.updateList(box);
        }else{
            Log.d("fotagmobile","adapter is not null");
            adapter.updateList(box);
        }
        //adapter.updateList(box);
        //redraw ORIGINAL
//        ImageAdapter a = new ImageAdapter(context,box);
//        adapter = a;
//        adapter.notifyDataSetChanged();
//        gr.setAdapter(adapter);
    }

    public void filterImages(int ratingFilter){
        Log.d("fotagmobile","in filterImages method");
        this.removeAllViews();
        ArrayList<ImageModel> list = new ArrayList<ImageModel>();
        for(int i=0;i<model.ImageList.size();i++){
            list.add(model.ImageList.get(i));
        }
        Log.d("fotagmobile","num images before filter: "+list.size());
        Log.d("fotagmobile", "filter to: " + ratingFilter);
        if(ratingFilter!=0){
            for(int i=list.size()-1;i>=0;i--){
                int imgrating = list.get(i).getImgRating();
                if(imgrating < ratingFilter){
                    list.remove(i);
                }
            }
        }
        Log.d("fotagmobile", "after filter,list size: " + list.size());
        model.SecondList.clear();
        box2.clear();
        for(int i=0;i<list.size();i++){
            model.SecondList.add(list.get(i));
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            adapter.clearAdapter();
            gr.setAdapter(null);
        }

        reDraw();
    }

    public void reDraw(){
        int size = model.SecondList.size();
        Log.d("fotagmobile", "redraw size: " + size);
        if(size!=0){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Log.d("fotagmobile","portrait redraw");
                Log.d("fotagmobile","box size: "+box.size());
                for(int j=0;j<box.size();j++){
                    for(int i=0;i<model.SecondList.size();i++){
                        if(model.SecondList.get(i).imgID == box.get(j).getID()){
                            this.addView(box.get(j));
                            break;
                        }
                    }
                }
            }
            else{
                Log.d("fotagmobile", "landscape redraw");
                box2.clear();
                for(int i=0;i<model.SecondList.size();i++) {
                    ImageBox b = new ImageBox(context,model.SecondList.get(i).getImgRating(), model.SecondList.get(i).imgID, model.SecondList.get(i).getBitmap());
                    box2.add(b);
                    Log.d("fotagmobile", "id: " + b.getID());
                    Log.d("fotagmobile","list rating: "+model.SecondList.get(i).getImgRating());
                    Log.d("fotagmobile", "b rating: " + b.getRating());
                }

                adapter.updateList(box2);
              //  ImageAdapter a = new ImageAdapter(context,box2);
             //   adapter = a;

//                gr.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }
        }
    }

    public void searchLink(final String link){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = new URL(link).openStream();
                    Log.d("fotagmobile","searchLink");
                    //http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg
                    bmp = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.d("fotagmobile","ERROR URL");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (bmp != null){
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        Log.d("fotagmobile","searchlink to loadgrid");
                        loadGrid();
                    }else {
                        Log.d("fotagmobile", "searchlink to loadImage");
                        loadImages();
                    }
                }
            }
        }.execute();
    }
    public void update(Observable observable, Object data) {
        Log.d("fotagmobile", "update View");
        if(model.type.equals("load")) {
            Log.d("fotagmobile", "load images update");
            loadType = "loadButton";
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Log.d("fotagmobile", "LANDSCAPE LOADIMAGES");
                loadGrid();
            }else{
                Log.d("fotagmobile", "PORTRAIT LOADIMAGES");
                loadImages();
            }
            model.type="";
           // loadImages();
        }else if(model.type.equals("clear")){
            box.clear();
            box2.clear();
            model.ImageList.clear();
            model.SecondList.clear();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Log.d("fotagmobile", "LANDSCAPE CLEAR");
                adapter.clearAdapter();
                gr.setAdapter(null);
                model.FirstLoad = true;
                bmpID = 0;
            }else{
                Log.d("fotagmobile", "PORTRAIT CLEAR");
                this.removeAllViews();
                model.FirstLoad = true;
                bmpID = 0;
            }
            model.type="";
        }else if(model.type.equals("filter")){
//            for(int i=0;i<box.size();i++) {
//                Log.d("fotagmobile", "Box RatingChange:" + box.get(i).RatingChange);
//                if (box.get(i).RatingChange) {
//                    Log.d("fotagmobile", "RATING CHANGED");
//                   // adapter.notifyDataSetChanged();
//                }
//            }
            for(int i=0;i<model.ImageList.size();i++){
                Log.d("fotagmobile","rating: "+model.ImageList.get(i).getImgRating());
            }
            Log.d("fotagmobile","--------------BOX");
            for(int i=0;i<box.size();i++){
                Log.d("fotagmobile", "rating: "+ box.get(i).getRating());
            }
            if(model.ImageList.size()!=0){
                Log.d("fotagmobile","box size in filter: "+box.size());
                Log.d("fotagmobile", "ImageList size in filter: " + model.ImageList.size());
                for(int i=0;i<box.size();i++){
//                    Log.d("fotagmobile","imagelist id: "+model.ImageList.get(i).imgID);
//                    Log.d("fotagmobile","box getID: "+box.get(i).getID());
                    if(box.get(i).getID() == model.ImageList.get(i).imgID) {
                        Log.d("fotagmobile", "box ID: " + box.get(i).getID() + " rating: "
                                + box.get(i).getRating());
                        model.ImageList.get(i).setRating(box.get(i).getRating());
                    }
                }
                filterImages(model.filterRating);
            }
            model.type="";
        }else if(model.type.equals("search")){
            loadType = "loadURI";
            searchLink(model.link);
            model.type="";
        }
//        else{
//            for(int i=0;i<box.size();i++){
//                if(box.get(i).RatingChange){
//                    //there's an update on the star
//                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                        Log.d("fotagmobile","STAR CHANGE ON ID: "+box.get(i).getID());
//                    }
//                }
//            }
//        }
    }
}
