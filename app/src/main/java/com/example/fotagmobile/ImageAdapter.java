package com.example.fotagmobile;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<ImageBox> listImage;
    public ArrayList<Integer> checkID;
    public boolean added;
    public int counter;

    public ImageAdapter(Context c, ArrayList<ImageBox> list1){
        context = c;
        listImage = new ArrayList<>(list1);
        checkID = new ArrayList<Integer>();
        added = false;
        counter = 0;
    }

    public boolean check(int id){
        added = checkID.contains(id);
        return added;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public Object getItem(int position) {
        return listImage.get(position).getID(); //id of the image;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageBox imgBox;
        if(convertView == null) {
            Log.d("fotagmobile","IF");
            imgBox = new ImageBox(context, listImage.get(position).getID(),listImage.get(position).getBitmap());
            checkID.add(listImage.get(position).getID());
            counter++;
        }else if(!check(listImage.get(position).getID()) && counter<listImage.size()){
            Log.d("fotagmobile","ELSE IF");
            imgBox = new ImageBox(context, listImage.get(position).getID(), listImage.get(position).getBitmap());
            checkID.add(listImage.get(position).getID());
            counter++;
        }
        else{
            imgBox= (ImageBox) convertView;
            Log.d("fotagmobile","ELSE2");
        }
        return imgBox;

        //only images not imagebox down here
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(listImage.get(position).getID());
//        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setMinimumWidth(200);
//        imageView.setMinimumHeight(200);
//        imageView.setMaxWidth(600);
//        imageView.setMaxHeight(400);
//
//        imageView.setAdjustViewBounds(true);
//        imageView.setLayoutParams(new GridView.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                GridView.LayoutParams.MATCH_PARENT));
//        return imageView;
    }
}
