package com.example.fotagmobile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Observable;
import java.util.Observer;

public class ImageBox extends LinearLayout implements Observer {
    //private Model model;
    private Context context;
    private ImageModel im;
    private Star rating;
    private int id;
    private Bitmap bm;
    private int ImageBoxRating;
    public boolean RatingChange;

    public ImageBox(final Context context_, int imgBoxRating, int id_, Bitmap bm_){
        super(context_);
        context = context_;
        //model = model_;
        rating = new Star(context);
        bm = bm_;
        id=id_;
        ImageBoxRating = imgBoxRating;
        RatingChange = false;

        if(bm==null){
           // Log.d("fotagmobile","ImageBox image id");
            loadImageWithID(id);
        }else{
            Log.d("fotagmobile","ImageBox bitmap");
            loadImageWithBitmap(bm);
        }

        im.addObserver(this);
    }

    public void loadImageWithID(int id_){
        id = id_;
        im = new ImageModel(ImageBoxRating,id,bm);

        this.setOrientation(VERTICAL);
        ImageView iv = new ImageView(context);
        //iv.setBackgroundResource(id);

        iv.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), id, 200, 200)
        );

        iv.setMinimumWidth(200);
        iv.setMinimumHeight(200);
        iv.setMaxWidth(600);
        iv.setMaxHeight(400);

        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        iv.setPadding(0, 30, 0, 20);

        rating.setGravity(Gravity.CENTER_HORIZONTAL);
        this.addView(iv);
        this.addView(rating);

        addStarListener();

        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","image is clicked");
                Intent i = new Intent(context, FullImageActivity.class);
                i.putExtra("id", id);
                byte[] bytes = null;
                i.putExtra("bitmap", bytes);
                context.startActivity(i);
            }
        });
    }

    public void loadImageWithBitmap(final Bitmap bitmap){
        Log.d("fotagmobile","loading bitmap");
        Log.d("fotagmobile","id: "+id);
        im = new ImageModel(ImageBoxRating,id,bitmap);
        this.setOrientation(VERTICAL);
        ImageView iv = new ImageView(context);

        iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 200, false));
        iv.setMinimumWidth(200);
        iv.setMinimumHeight(200);
        iv.setMaxWidth(600);
        iv.setMaxHeight(400);

        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        iv.setPadding(0, 30, 0, 20);

        rating.setGravity(Gravity.CENTER_HORIZONTAL);
        this.addView(iv);
        this.addView(rating);

        addStarListener();

        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image is clicked");
                Intent i = new Intent(context, FullImageActivity.class);
                i.putExtra("id", id);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                i.putExtra("bitmap", bytes);
                context.startActivity(i);
            }
        });
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
//
//            Log.d("fotagmobile","halfHeight: "+halfHeight);
//            Log.d("fotagmobile","halfWidth: "+halfWidth);

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int getID(){
        return id;
    }

    public int getRating(){
        //Log.d("fotagmobile","return imgRating from img box: "+im.getImgRating());
        return im.getImgRating();
    }

    public Bitmap getBitmap(){
        return bm;
    }

    public void addStarListener(){
        rating.StarList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 1 clicked");
                rating.removeAllViews();
                im.setRating(1);
                rating.setRatingStar(1);
                im.updateStar();
            }
        });
        rating.StarList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 2 clicked");
                rating.removeAllViews();
                im.setRating(2);
                rating.setRatingStar(2);
                im.updateStar();
            }
        });
        rating.StarList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 3 clicked");
                rating.removeAllViews();
                im.setRating(3);
                rating.setRatingStar(3);
                im.updateStar();
            }
        });
        rating.StarList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 4 clicked");
                rating.removeAllViews();
                im.setRating(4);
                rating.setRatingStar(4);
                im.updateStar();
            }
        });
        rating.StarList.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image star 5 clicked");
                rating.removeAllViews();
                im.setRating(5);
                rating.setRatingStar(5);
                im.updateStar();
            }
        });


        rating.clearStar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "image clearRating clicked");
                rating.removeAllViews();
                im.setRating(0);
                rating.clearRating();
                im.updateStar();
            }
        });
    }

    public void updateStarImage(){
        Log.d("fotagmobile", "update star image");
        rating.drawStar();
        this.addView(rating);
        addStarListener();
    }

    public void addRating(int r){
        rating.removeAllViews();
        im.setRating(r);
        rating.setRatingStar(r);
        im.updateStar();
    }

    @Override
    public void update(Observable arg0, Object arg1){
        Log.d("fotagmobile","update ImageBox view");
        if(im.updateImgStar){
            RatingChange = true;
            Log.d("fotagmobile","update image star to:"+im.getImgRating());
            Log.d("fotagmobile", "RatingChange:" + RatingChange);
            im.updateImgStar = false;
            this.removeView(rating);
            updateStarImage();
        }
    }

}
