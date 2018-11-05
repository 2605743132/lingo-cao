package com.example.caoyuan.zhoukaocaoyuan_20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class LuckDraw extends View implements View.OnClickListener {
    private Paint mapint;
    private Paint startpaint;
    private int mPadding;
    private int mWidth;
    private Boolean isStart = false;

    private RectF rectF;
    private String str="start";
    private String[] contents = {"一等奖","二等奖","三等奖","四等奖","参与奖","谢谢参与"};
    //这是一个颜色
    public  int[] colors = new int[]{Color.parseColor("#8EE5EE"),Color.parseColor("#FFD700"),Color.parseColor("#FFD39B"),Color.parseColor("#FF8247"),Color.parseColor("#FF34B3"),Color.parseColor("#F0E68C")};
    private Path path;
    private int width;
    private RotateAnimation rotateAnimation;
    public ClickLine clickLine;
    public LuckDraw(Context context) {
        this(context,null);
    }

    public LuckDraw(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public LuckDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
              //画笔工具
                initPaint();
                init(context);
                initAnim();
                setOnClickListener(this);
    }

    private void init(Context context) {

      
    }

    private void initAnim() {

        rotateAnimation = new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
                );
        rotateAnimation.setRepeatCount(-1);

        rotateAnimation.setFillAfter(true);
    }

    //初始化画笔工具
    private void initPaint() {
        //设置内圆画笔
        startpaint = new Paint();
        startpaint.setColor(Color.WHITE);
        startpaint.setStyle(Paint.Style.STROKE);
        startpaint.setAntiAlias(true);
        startpaint.setStrokeWidth(5);
        //设置外圆画笔
       mapint = new Paint();
       mapint.setColor(Color.RED);
       mapint.setStyle(Paint.Style.STROKE);
       mapint.setAntiAlias(true);
       mapint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(300,300);
        mWidth = getMeasuredWidth();
        mPadding = 5 ;
        initRect();
    }

    private void initRect() {
        
        rectF = new RectF(0,0,mWidth,mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制大圆
        mapint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2-mPadding,mapint);
        //绘制6个椭圆
        mapint.setStyle(Paint.Style.FILL);
         initArc(canvas);
         //绘制小圆
        mapint.setColor(Color.RED);
        mapint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth/2,mWidth/2,50,mapint);

        mapint.setColor(Color.WHITE);
        mapint.setTextSize(32);
        Rect rect = new Rect();
        mapint.getTextBounds(str,0,str.length(),rect);
        int strWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(str,mWidth/2-25+25-strWidth/2,mWidth/2+textHeight/2,mapint);
    }
     //绘制圆弧和文字
    private void initArc(Canvas canvas) {
     //设置颜色
        for (int i =0 ; i<6;i++){
            mapint.setColor(colors[i]);
            canvas.drawArc(rectF,(i-1)*60+60,60,true,mapint);

    }
        //设置对应文字
        for (int j =0 ; j<6; j++){
         mapint.setColor(Color.BLACK);
        path =new Path();

        path.addArc(rectF,(j-1)*60+60,60);

        canvas.drawTextOnPath(contents[j],path,60,60,mapint);

        }
    }

//点击事件
    @Override
    public void onClick(View v) {
       if (!isStart){

           isStart = true;

            rotateAnimation.setDuration(1000);

            rotateAnimation.setInterpolator( new LinearInterpolator());
          startAnimation(rotateAnimation);
  }else {
           isStart = false;
           stopAnim();

       }

    }
//停止转盘
    private void stopAnim() {

        clearAnimation();
    }





    public void clickLine(ClickLine clickLine) {
        this.clickLine = clickLine;
    }

    //接口回调
    public interface ClickLine{
        void Click(View v);

    }
}
