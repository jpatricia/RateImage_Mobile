package com.example.fotagmobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FullImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("fotagmobile","in fullimageactivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data and set up imageview
        Intent i = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        // Selected image id
        int position = i.getExtras().getInt("id");
        byte[] bytes = i.getByteArrayExtra("bitmap");
        if(bytes!=null){
            Log.d("fotagmobile","bm is not null");
            Bitmap bm = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            imageView.setImageBitmap(bm);
        }else{
            Log.d("fotagmobile","bm is null");
            imageView.setImageResource(position);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
