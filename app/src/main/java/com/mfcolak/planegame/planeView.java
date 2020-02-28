package com.mfcolak.planegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import java.sql.Timestamp;
import java.util.Map;

public class planeView extends View {

    private Bitmap plane[] = new Bitmap[2];
    private int planeX = 10;
    private int planeY = 10;
    private int planeSpeed;
    private int cnvsWidth,cnvsHeight;

    private int upX,upY,upSpeed =15;


    private Bitmap bluePlane;

    private int puanX,puanY,puanSpeed = 25;
    private Paint puanPaint = new Paint();

    private int score,canCount;

    private boolean touch = false;
    private Bitmap bgimage;
    private Paint skor =new Paint();
    private Bitmap life[] =new Bitmap[2];




    public planeView(Context context) {
        super(context);



        plane[0]= BitmapFactory.decodeResource(getResources(), R.drawable.plane1);
        plane[1]= BitmapFactory.decodeResource(getResources(), R.drawable.plane2);

        bgimage=BitmapFactory.decodeResource(getResources(),R.drawable.bg);

        bluePlane=BitmapFactory.decodeResource(getResources(),R.drawable.aircraft);


        puanPaint.setColor(Color.BLUE);
        puanPaint.setAntiAlias(false);

        skor.setColor(Color.BLACK);
        skor.setTextSize(75);
        skor.setTypeface(Typeface.DEFAULT_BOLD);
        skor.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.can);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.cant);

        planeY = 550;
        score = 0;
        canCount=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cnvsWidth = canvas.getWidth();
        cnvsHeight = canvas.getHeight();

        canvas.drawBitmap(bgimage,0,0,null);

        int minPlaneY = plane[0].getHeight();
        int maxPlaneY = cnvsHeight - plane[0].getHeight()*3;
        planeY = planeY + planeSpeed;

        if (planeY< minPlaneY){
            planeY = minPlaneY;
        }
        if (planeY > maxPlaneY){
            planeY = maxPlaneY;
        }

        planeSpeed = planeSpeed+2;
        if (touch)
        {

            canvas.drawBitmap(plane[1],planeX,planeY,null);
            touch = false;
        }
        else {
            canvas.drawBitmap(plane[0],planeX,planeY,null);

        }

        upX = upX - upSpeed;
        if (topKontrol(upX,upY)){

            upX =-100;
            canCount--;
            if (canCount==0){
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent finish = new Intent(getContext(),bitisActivity.class);
                finish.putExtra("Skor",score);
                getContext().startActivity(finish);
            }

        }

        if (upX < 0)
        {
            upX =cnvsWidth +20;
            upY = (int) Math.floor(Math.random() * (maxPlaneY - minPlaneY)) + minPlaneY;
        }


        upX = upX - upSpeed;
        if (topKontrol(upX,upY)){

            upX =-100;
            canCount--;
            if (canCount==0){

                Intent finish = new Intent(getContext(),bitisActivity.class);
                finish.putExtra("Skor",score);
                getContext().startActivity(finish);
            }

        }

        if (upX < 0)
        {
            upX =cnvsWidth +20;
            upY = (int) Math.floor(Math.random() * (maxPlaneY - minPlaneY)) + minPlaneY;
        }


        puanX = puanX-puanSpeed;

        if (topKontrol(puanX,puanY))
        {
            score=score+10;
            puanX =-100;
        }
        if (puanX<0)
        {
            puanX=cnvsWidth+20;
            puanY=(int) Math.floor(Math.random() * (maxPlaneY - minPlaneY)) + minPlaneY;
        }
        canvas.drawCircle(puanX,puanY,25,puanPaint);


        canvas.drawText("Skor: "+score,20,60,skor);

        for (int i=0;i<3;i++)
        {
            int x = (int) (570 + life[0].getWidth()* 1.1*i);
            int y = 25;

            if (i<canCount)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }


        canvas.drawBitmap(bluePlane,upX,upY,null);



    }

    public boolean topKontrol(int x,int y)
    {
        if(planeX<x && x < (planeX+plane[0].getWidth()) && planeY < y && y < (planeY+plane[0].getHeight()))
        {

            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            planeSpeed =-22;

        }
        return true;
    }
}
