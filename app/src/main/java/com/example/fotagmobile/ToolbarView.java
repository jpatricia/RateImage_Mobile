package com.example.fotagmobile;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import java.util.Observable;
import java.util.Observer;

public class ToolbarView extends LinearLayout implements Observer {
    private Model model;
    private Toolbar toolbar;

    public ToolbarView(Toolbar toolbar_, Context context, Model model_){
        super(context);
        toolbar = toolbar_;
        View.inflate(context, R.layout.content_main,this);
        model = model_;
        model.addObserver(this);

        ImageButton load = (ImageButton) toolbar.findViewById(R.id.load_button);
        ImageButton clear = (ImageButton) toolbar.findViewById(R.id.clear_button);
        ImageButton search = (ImageButton) toolbar.findViewById(R.id.search_button);

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

    @Override
    public void update(Observable arg0, Object arg1){
        Log.d("fotagmobile","update Toolbar view");
    }

}