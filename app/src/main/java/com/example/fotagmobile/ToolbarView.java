package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import java.util.Observable;
import java.util.Observer;

public class ToolbarView extends LinearLayout implements Observer {
    private Model model;
    private Toolbar toolbar;
    private Star ratingToolbar;
    private Context context;

    public ToolbarView(Toolbar toolbar_, Context context_, Model model_){
        super(context_);
        context = context_;
        toolbar = toolbar_;
        View.inflate(context, R.layout.content_main,this);
        model = model_;
        model.addObserver(this);

        ImageButton load = (ImageButton) toolbar.findViewById(R.id.load_button);
        ImageButton clear = (ImageButton) toolbar.findViewById(R.id.clear_button);
        ImageButton search = (ImageButton) toolbar.findViewById(R.id.search_button);

        final Star s = new Star(context);
        s.setGravity(Gravity.END);
        ratingToolbar = s;
        toolbar.addView(ratingToolbar);
        Log.d("fotagmobile", "size: " + s.StarList.size());

        addListenerRating();

//        s.StarList.get(1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fotagmobile", "toolbar star 1 clicked");
//                ratingToolbar.removeAllViews();
//                toolbar.removeView(ratingToolbar);
//                s.setRating(1);
//                model.type = "filter";
//                model.updateStar();
//            }
//        });
//
//        s.StarList.get(2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fotagmobile","toolbar star 2 clicked");
//                ratingToolbar.removeAllViews();
//                toolbar.removeView(ratingToolbar);
//                s.setRating(2);
//                model.type = "filter";
//                model.updateStar();
//            }
//        });
//
//        s.StarList.get(3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fotagmobile","toolbar star 3 clicked");
//                ratingToolbar.removeAllViews();
//                toolbar.removeView(ratingToolbar);
//                s.setRating(3);
//                model.type = "filter";
//                model.updateStar();
//            }
//        });
//
//        s.StarList.get(4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fotagmobile","toolbar star 4 clicked");
//                ratingToolbar.removeAllViews();
//                toolbar.removeView(ratingToolbar);
//                s.setRating(4);
//                model.type = "filter";
//                model.updateStar();
//            }
//        });
//
//        s.StarList.get(5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fotagmobile","toolbar star 5 clicked");
//                ratingToolbar.removeAllViews();
//                toolbar.removeView(ratingToolbar);
//                s.setRating(5);
//                model.type = "filter";
//                model.updateStar();
//            }
//        });

        load.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "load button clicked");
                model.loadImage();
            }
        });

        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "clear button clicked");
                model.clearImage();
            }
        });

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "search button clicked");
                model.searchImage();
            }
        });

    }

    public void addListenerRating(){
        ratingToolbar.StarList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "toolbar star 1 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRating(1);
                model.type = "filter";
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 2 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRating(2);
                model.type = "filter";
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 3 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRating(3);
                model.type = "filter";
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 4 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRating(4);
                model.type = "filter";
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 5 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRating(5);
                model.type = "filter";
                model.updateStar();
            }
        });

        ratingToolbar.clearStar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar clearRating clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.clearRating();
                model.type = "filter";
                model.updateStar();
            }
        });
    }

    public void updateStarToolbar(Star updatedBar){
        Log.d("fotagmobile", "ratingToolbar.StarList.size(): " + updatedBar.StarList.size());
//        for(int i=1; i <ratingToolbar.StarList.size();i++){
//            ImageButton s = new ImageButton(context);
//            s = ratingToolbar.StarList.get(i);
//             ratingToolbar.addView(s);
//        }
        ratingToolbar = updatedBar;
        toolbar.addView(ratingToolbar);
        addListenerRating();
    }

    @Override
    public void update(Observable arg0, Object arg1){
        Log.d("fotagmobile","update Toolbar view");
        if(model.type.equals("filter")) {
            updateStarToolbar(ratingToolbar.drawStar());
        }
    }

}
