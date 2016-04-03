package com.example.fotagmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(0);
                ratingToolbar.clearRating();
                updateStarToolbar(ratingToolbar.drawStar());
                model.updateStar();


            }
        });

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "search button clicked");
                createDialogPrompt();
            }
        });

    }

    public void createDialogPrompt(){
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = userInput.getText().toString();
                                Log.d("fotagmobile","result: "+result);
                                model.searchImage(result);
                                //http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void addListenerRating(){
        ratingToolbar.StarList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile", "toolbar star 1 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(1);
                model.type = "filter";
                model.filterRating = 1;
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 2 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(2);
                model.type = "filter";
                model.filterRating = 2;
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 3 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(3);
                model.type = "filter";
                model.filterRating = 3;
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 4 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(4);
                model.type = "filter";
                model.filterRating = 4;
                model.updateStar();
            }
        });

        ratingToolbar.StarList.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fotagmobile","toolbar star 5 clicked");
                ratingToolbar.removeAllViews();
                toolbar.removeView(ratingToolbar);
                ratingToolbar.setRatingStar(5);
                model.type = "filter";
                model.filterRating = 5;
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
                model.filterRating = 0;
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
