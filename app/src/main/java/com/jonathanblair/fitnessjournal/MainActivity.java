package com.jonathanblair.fitnessjournal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jonathanblair.fitnessjournal.adapter.JournalPagerAdapter;
import com.jonathanblair.fitnessjournal.fragment.ExerciseFragment;
import com.jonathanblair.fitnessjournal.fragment.FoodFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements FoodFragment.Listener, ExerciseFragment.Listener{

    private ViewPager viewPager;
    private TabLayout tabs;
    private Toolbar toolbar;
    private FloatingActionButton mFab;

    public HorizontalScrollView aView;
    public ScrollView bView;

    public HorizontalScrollView aView_2;
    public ScrollView bView_2;
    static String FRAG_CODE = "";
    View mV;
    int mW, mH;

    FoodFragment foodFragment;
    ExerciseFragment exerciseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodFragment = new FoodFragment();
        exerciseFragment =new ExerciseFragment();

        //initialize all variables
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        //set ToolBar as actionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            mFab.setEnabled(false);
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolderWithImage();

                View vv = getLayoutInflater().inflate(R.layout.fragment_food,null);
                Toast tst = new Toast(MainActivity.this);
                tst.setView(vv);
                tst.setDuration(Toast.LENGTH_SHORT);
                tst.show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        JournalPagerAdapter adapter = new JournalPagerAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(foodFragment, "Food");
        adapter.addFragment(exerciseFragment, "Exercise");
        viewPager.setAdapter(adapter);
    }

    public static Bitmap getBitmapFromView(View view, int width, int height) {

        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }



    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public void createFolderWithImage(){

        int mm = viewPager.getCurrentItem();

        if (mm == 0) {
             mV = this.aView;
             mW = aView.getChildAt(0).getWidth();
             mH = aView.getChildAt(0).getHeight();
        } else if (mm == 1){

            mV = this.aView_2;
            mW = aView_2.getChildAt(0).getWidth();
            mH = aView_2.getChildAt(0).getHeight();
        }

        //save the created image files
        File mFolder = new File(Environment.getExternalStorageDirectory(),"Fitness Journal");
        Bitmap b = getBitmapFromView(mV, mW, mH);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());

        if (!mFolder.exists()){
            mFolder.mkdir();
        }else {
            File f = new File(mFolder+File.separator+"journal_" + timeStamp +".jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                fo.close();
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
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
    public void getView(HorizontalScrollView v, ScrollView v2, String fragmentCode) {
        aView=v;
        bView = v2;
        FRAG_CODE = fragmentCode;
        Log.e("TAG", "first frag" + FRAG_CODE);
//        createFolderWithImage(v);
    }

    @Override
    public void passView(HorizontalScrollView view, ScrollView view2, String fragmentCode) {
        aView_2 = view;
        bView_2 = view2;
        FRAG_CODE = fragmentCode;
    }
}
