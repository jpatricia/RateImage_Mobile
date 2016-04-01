package com.example.fotagmobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Janice on 4/1/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
  //  private ArrayList<ImageModel> listImage;

    public ImageAdapter(Context c){
        context = c;
//        listImage = new ArrayList<>();
//        if(list1.size()!=0){
//            for(int i=0;i<list1.size();i++){
//                listImage.add(list1.get(i));
//            }
//        }

    }

    @Override
    public int getCount() {
        return 0;
     //   return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return position; //id of the image;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
}
