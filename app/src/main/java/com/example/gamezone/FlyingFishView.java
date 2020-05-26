package com.example.gamezone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View {
    private Bitmap fish[]=new Bitmap[2];
private boolean touch=false;
private int fishx=10,fishy,fishspeed=12;
private Bitmap background,bck2;
private int canvaswidth,canvasheight;
private Bitmap life[]=new Bitmap[2];
private Paint scorepaint=new Paint();
private int sc=0;
private Paint yellowball=new Paint();

private int yellowx=100,yellowy=100,yellowspeed=15 ;
    private Paint greenball=new Paint();

    private int greenx=100,greeny=100,greenspeed=20 ;

    private Paint redball=new Paint();

    private int redx=100,redy=100,redspeed=30 ;
    int lifeline=3;
    private Bitmap otherfish;
    int level=1;

    MediaPlayer ring= MediaPlayer.create(getContext(),R.raw.killed);
    MediaPlayer waves= MediaPlayer.create(getContext(),R.raw.river);
    MediaPlayer level2= MediaPlayer.create(getContext(),R.raw.winn);



    public FlyingFishView(Context context) {
        super(context);

fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
fish[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
bck2=BitmapFactory.decodeResource(getResources(),R.drawable.level2);
otherfish=BitmapFactory.decodeResource(getResources(),R.drawable.other);
    background=BitmapFactory.decodeResource(getResources(),R.drawable.underwater);
    scorepaint.setColor(Color.WHITE);
    scorepaint.setTextSize(40);
    scorepaint.setAntiAlias(true);
    scorepaint.setTypeface(Typeface.SANS_SERIF);

    yellowball.setColor(getResources().getColor(R.color.yellow));
    yellowball.setAntiAlias(false);
        greenball.setColor(getResources().getColor(R.color.darkgreen));
        greenball.setAntiAlias(false);
        redball.setColor(Color.RED);
        redball.setAntiAlias(true);

fishy=440;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight();

waves.start();
        fishy=fishy+fishspeed;
        int minh=fish[0].getHeight();
        int maxh=canvasheight-fish[0].getHeight()*3;
        if(fishy<minh)
            fishy=minh;
        if(fishy>maxh)
        {
            fishy=maxh;
        }

        fishspeed+=2;
if(level==1)
{ if(sc>=100) {
    Toast.makeText(getContext(), "LEVEL 2", Toast.LENGTH_SHORT).show();
    level = 2;
    MediaPlayer newpl=MediaPlayer.create(getContext(),R.raw.level2);
    newpl.start();
    //level2.start();
this.invalidate();

}}
if(level==1)
    canvas.drawBitmap(background, 0, 0, null);
else {
    canvas.drawBitmap(bck2, 0, 0, null);


redspeed+=1;


}if(touch==true)
        {
            canvas.drawBitmap(fish[1],fishx,fishy,null);

            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishx,fishy,null);
        }
        if(hitballcheck(yellowx,yellowy))
        {level2.start();
            sc+=10;
            yellowx=-100;
        }
        yellowx=yellowx-yellowspeed;
        //yellowy=minh;
        if(yellowx<0)
        {
yellowx=canvaswidth+21;
yellowy=(int)Math.floor(Math.random()*(maxh-minh))+minh;

        }

        canvas.drawCircle(yellowx,yellowy,20,yellowball);
        if(hitballcheck(greenx,greeny))
        {level2.start();
            sc+=20;
            greenx=-100;
        }
        greenx=greenx-greenspeed;
        //yellowy=minh;
        if(greenx<0)
        {
            greenx=canvaswidth+30;
            greeny=(int)Math.floor(Math.random()*(maxh-minh))+(minh*2);

        }

        canvas.drawCircle(greenx,greeny,40,greenball);
        canvas.drawBitmap(otherfish,canvaswidth-greenx,canvasheight-greeny,null);

        if(hitballcheck(redx,redy))
        {
            ring.start();
           redx=-100;
           lifeline--;
           sc=sc-10;
           if(lifeline==0)
           { Toast.makeText(getContext(),"GAME OVER",Toast.LENGTH_SHORT).show();
        waves.stop();
        Intent intent=new Intent(getContext(),GameOver.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("SCORE",sc);
getContext().startActivity(intent);

           }}
        redx=redx-redspeed;
        //yellowy=minh;
        if(redx<0)
        {
            redx=canvaswidth+30;
            redy=(int)Math.floor(Math.random()*(maxh-minh));

        }

        canvas.drawCircle(redx,redy,60,redball);





        canvas.drawText("SCORE : "+sc,0,60,scorepaint);

        for(int i=1;i<=3;i++)
        {
            int x=canvaswidth-i*100;
            if(i<=lifeline)
            {
                canvas.drawBitmap(life[0],x,12,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,12,null);
            }
        }




    }
public boolean hitballcheck(int x,int y)
{
    if(fishx<x&&x<(fishx+fish[0].getWidth())&& fishy<y&&y<(fishy+fish[0].getHeight()))
        return  true;
    return  false;
}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {fishspeed=-22;
            touch=true;
        }

return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        waves.stop();
    }
}
