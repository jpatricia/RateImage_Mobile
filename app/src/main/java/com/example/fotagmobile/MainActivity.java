package com.example.fotagmobile;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    Model model;
    Toolbar toolbar;
    GridView grid;
    ImageAdapter imgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FotagMobile", "onCreate");
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("fotagmobile","PORTRAIT");
        }else{
            Log.d("fotagmobile","LANDSCAPE");
        }
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        grid = (GridView)findViewById(R.id.grid_view);

        model = new Model();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Log.d("MVC", "onPostCreate");
        // can only get widgets by id in onPostCreate for activity xml res

        ToolbarView toolbarView = new ToolbarView(toolbar, this,model);
        ImageCollectionView content = new ImageCollectionView(this,model,grid,imgAdapter);
        ViewGroup v = (ViewGroup) findViewById(R.id.mainactivity_1);
//        ScrollView v = (ScrollView) findViewById(R.id.view);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("fotagmobile", "LANDSCAPE POSTCREATE");
            imgAdapter = new ImageAdapter(this,content.getBox());
            grid.setAdapter(imgAdapter);
        }else{
            v.addView(toolbarView);
            v.addView(content);
        }



        // initialize views
        model.setChanged();
        model.notifyObservers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("Fotag Mobile", "save state");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("Fotag Mobile", "restore state");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
