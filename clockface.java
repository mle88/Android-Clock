package com.example.michellele.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Calendar;

import static android.graphics.Color.rgb;

public class clockface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasView cv = new CanvasView(this);
        setContentView(cv);
        //setContentView(R.layout.activity_clockface);
    }

    private class CanvasView extends View{

        boolean init=false;
        Paint pt;

        //all three constructors are needed here
        public CanvasView(Context context) {
            super(context);
        }

        public CanvasView(Context context, AttributeSet attrs)
        {
            super(context,attrs);
        }

        public CanvasView(Context context, AttributeSet attrs, int defStyleAttr)
        {
            super(context, attrs, defStyleAttr);
        }

        void init()
        {

        }

        int r=255, g=255, b=255;

        int left, right, top, bottom;
        int left_min, right_min, top_min, bottom_min;
        int left_sec, right_sec, top_sec, bottom_sec;

        int counter = 0;

        @Override
        protected void onDraw (Canvas cv)
        {

            super.onDraw(cv);
            Paint pt = new Paint ();
            pt.setStyle(Paint.Style.FILL);
            pt.setColor(Color.DKGRAY);

            cv.drawPaint(pt);

            //get the canvas dimensions
            DisplayMetrics dm = this.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            int height = dm.heightPixels-200;

            if(!init){
                init();
                init=true;
            }



            //get the time
            Calendar ctime = Calendar.getInstance();

            //set background for am
            if ((ctime.get(Calendar.HOUR_OF_DAY)) >=0  && (ctime.get(Calendar.HOUR_OF_DAY) <=12))
            {
                cv.drawColor(Color.BLUE);
            }

            //set colour
            //for day
            if((ctime.get(Calendar.HOUR_OF_DAY))>= 9 && (ctime.get(Calendar.HOUR_OF_DAY) <= 17))
            {
                r=255;
                g=200;
                b=100;

            }

            //for night
            else if ((ctime.get(Calendar.HOUR_OF_DAY))<= 3 && (ctime.get(Calendar.HOUR_OF_DAY) >= 17))
            {
                r=150;
                g=120;
                b=255;

            }

            //for sunrise
            else
            {
                r=200;
                g=120;
                b=50;

            }

            //set incrementing time
            left_sec = width /2 + 200;
            top_sec = height/2+200-(ctime.get(Calendar.SECOND)*10);
            right_sec = width/2 + 400;
            bottom_sec = height/2+200;

            left_min = width / 2 - 100;
            top_min = height/2+200-(ctime.get(Calendar.MINUTE)*10);
            right_min = width/2+100;
            bottom_min = height/2+200;

            left = width/2-400;
            top = height/2+200 - (ctime.get(Calendar.HOUR)*50);
            right = width/2-200;
            bottom = height/2+200;


            pt.setColor(rgb(r,g,b));

            cv.drawRect(left, top, right,bottom, pt);
            cv.drawRect(left_sec, top_sec, right_sec,bottom_sec, pt);
            cv.drawRect(left_min, top_min, right_min,bottom_min, pt);

            //postInvalidateDelayed(10);
            invalidate();
        }
    }
}

