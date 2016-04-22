package com.jonathanblair.fitnessjournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

/**
 * Created by oluwafemi.bamisaye on 4/20/2016.
 */
public class Splash extends Activity {

    Animation blinkAnim, moveAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

//        startAnimations();

/*        final ImageView log = (ImageView) findViewById(R.id.logo);

        log.animate().alpha(1).setDuration(5000).setInterpolator(new DecelerateInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                log.animate().alpha(0).setDuration(2000).setInterpolator(new AccelerateInterpolator()).start();
            }
        }).start();*/


        ImageView log = (ImageView) findViewById(R.id.logo);
        TextView tvSplash = (TextView) findViewById(R.id.splash_txt);

//        blinkAnim = AnimationUtils.loadAnimation(this,R.anim.blink);
        moveAnim = AnimationUtils.loadAnimation(this, R.anim.move);

//        log.setAnimation(moveAnim);
//        tvSplash.setAnimation(blinkAnim);

        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmerAnimation();


        int delay = 3000;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                Splash.this.finish();
//                overridePendingTransition(R.anim.move, R.anim.slide_left);
            }
        }, delay);


/*
        Thread timerThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(7000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent homeIntent = new Intent(Splash.this,MainActivity.class);
                    startActivity(homeIntent);
                }
            }
        };
        timerThread.start();*/



    }




/*    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        LinearLayout l2 = (LinearLayout) findViewById(R.id.linear2);
        l2.setVisibility(View.VISIBLE);
        l2.clearAnimation();
        l2.startAnimation(anim);
    }*/






        @Override
    protected void onPause() {
        super.onPause();
    finish();
    }
}
