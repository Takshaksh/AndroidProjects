package com.linuxh2o.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new Panel(this));

    }

    class Panel extends View {

        public Panel(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawColor(Color.BLACK);

            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(500,500,100, paint);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(20);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawRect(200,200,50,50, paint);

            paint.setColor(Color.GREEN);
            canvas.drawLine(200,200,500,500, paint);

            paint.setColor(Color.YELLOW);
            canvas.drawText("Hey", 500, 300,paint);


            super.onDraw(canvas);
        }
    }
}
