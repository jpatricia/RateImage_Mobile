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
        //createBoxes();

        model.addObserver(this);

        this.setOrientation(LinearLayout.VERTICAL);

    }

    public void createBoxes(){

        ImageBox b1 = new ImageBox(context,R.drawable.img1,null);
        ImageBox b2 = new ImageBox(context,R.drawable.img2,null);
        ImageBox b3 = new ImageBox(context,R.drawable.img3,null);
        ImageBox b4 = new ImageBox(context,R.drawable.img4,null);
        ImageBox b5 = new ImageBox(context,R.drawable.img5,null);
        ImageBox b6 = new ImageBox(context,R.drawable.img6,null);
        ImageBox b7 = new ImageBox(context,R.drawable.img7,null);
        ImageBox b8 = new ImageBox(context,R.drawable.img8,null);
        ImageBox b9 = new ImageBox(context,R.drawable.img9,null);
        ImageBox b10 = new ImageBox(context,R.drawable.img10,null);

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
        this.removeAllViews();
        if(loadType.equals("loadButton")){
            Log.d("fotagmobile", "loadbButton image");
            createBoxes();
            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
            Log.d("fotagmobile","box size: "+box.size());
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
            Log.d("fotagmobile","ImageList.size: "+model.ImageList.size());
            Log.d("fotagmobile", "box size: " + box.size());
            ImageBox ib = new ImageBox(context,bmpID,bmp);
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

    public ArrayList<ImageBox> getBox(){
        return box;
    }

    public void loadGrid(){
        Log.d("fotagmobile","loadGrid function");
        if(box.size()==0) {
            Log.d("fotagmobile","box size is 0");
            createBoxes();
        }
        Log.d("fotagmobile", "box size now: " + box.size());
        for(int i=0;i<box.size();i++) {
            model.addImage(box.get(i).getRating(), box.get(i).getID(),box.get(i).getBitmap());
        }

        ImageAdapter a = new ImageAdapter(context,box);
        gr.setAdapter(null);
       // adapter.listImage.clear();
       // adapter.checkID.clear();
       // adapter.counter=0;
        //adapter.notifyDataSetInvalidated();
        adapter = a;
        //adapter.notifyDataSetChanged();
        gr.setAdapter(adapter);
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
            adapter.counter = 0;
            adapter.checkID.clear();
            adapter.listImage.clear();
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
                Log.d("fotagmobile","landscape redraw");
                box2.clear();
                for(int i=0;i<model.SecondList.size();i++) {
                    ImageBox b = new ImageBox(context, model.SecondList.get(i).imgID, null);
                    b.addRating(model.SecondList.get(i).getImgRating());
                    box2.add(b);
                    Log.d("fotagmobile", "id: " + b.getID());
                }

                ImageAdapter a = new ImageAdapter(context,box2);


                adapter = a;
                adapter.notifyDataSetChanged();
                gr.setAdapter(adapter);
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
                    }else{
                        Log.d("fotagmobile","searchlink to loadImage");
                        loadImages();
                    }
                    // imageView.setImageBitmap(bmp);
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

           // loadImages();
        }else if(model.type.equals("clear")){
            box.clear();
            box2.clear();
            model.ImageList.clear();
            model.SecondList.clear();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Log.d("fotagmobile", "LANDSCAPE CLEAR");
                adapter.counter=0;
                adapter.listImage.clear();
                adapter.checkID.clear();
                gr.setAdapter(null);
                model.FirstLoad = true;
            }else{
                Log.d("fotagmobile", "PORTRAIT CLEAR");
                this.removeAllViews();
                model.FirstLoad = true;
                bmpID = 0;
            }

        }else if(model.type.equals("filter")){
            if(model.ImageList.size()!=0){
                Log.d("fotagmobile","box size in filter: "+box.size());
                Log.d("fotagmobile", "ImageList size in filter: " + model.ImageList.size());
                for(int i=0;i<box.size();i++){
                    Log.d("fotagmobile","imagelist id: "+model.ImageList.get(i).imgID);
                    Log.d("fotagmobile","box getID: "+box.get(i).getID());
                    if(box.get(i).getID() == model.ImageList.get(i).imgID) {
                        Log.d("fotagmobile", "box ID: " + box.get(i).getID() + " rating: "
                                + box.get(i).getRating());
                        model.ImageList.get(i).setRating(box.get(i).getRating());
                    }
                }
                filterImages(model.filterRating);
            }
        }else if(model.type.equals("search")){
            loadType = "loadURI";
            searchLink(model.link);
        }
    }
}
