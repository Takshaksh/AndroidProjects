package com.linuxh2o.menutab;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawActivity extends Activity
{
    Paint paint;
    Point point1, point2;

    Path path = new Path();

    List<Path> paths=new ArrayList<Path>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));
    }
    class Panel extends View implements View.OnTouchListener{

        public Panel(Context context)
        {
            super(context);
            paint=new Paint();
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            this.setOnTouchListener(this);
        }
        @Override
        public void onDraw(Canvas canvas)
        {

            canvas.drawColor(Color.BLACK);
            for (Path path: paths)
            {
                canvas.drawPath(path,paint);
            }
        }
        @Override
        public boolean onTouch(View view,MotionEvent event)
        {

            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                point1=new Point();
                point1.x=(int) event.getX();
                point1.y=(int) event.getY();

                path.moveTo(point1.x, point1.y);
            }
            else if(event.getAction()==MotionEvent.ACTION_MOVE)
            {

                point2 =new Point();
                point2.x=(int) event.getX();
                point2.y=(int) event.getY();


                path.lineTo(point2.x, point2.y);
                paths.add(path);
                invalidate();
            }
            return true;
        }
    }
}