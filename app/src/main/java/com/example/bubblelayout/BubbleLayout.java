package com.example.bubblelayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleLayout extends View implements onListChangedListener{

	private List<Bubble> bubbles = new ArrayList<Bubble>();
	private Random random = new Random();//生成随机数
	private int width, height;
	private boolean starting = false;
	private Context context;
	private static int[] drawableResource = {R.drawable.luobo,
			R.drawable.kapian, R.drawable.gouwuche, R.drawable.cheche,
			R.drawable.banshou};

	public BubbleLayout(Context context){
		super(context);
		this.context = context;
	}

	public BubbleLayout(Context context, AttributeSet attrs){
		super(context, attrs);
		this.context = context;
	}

	public BubbleLayout(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		width = getWidth();
		height = getHeight();
		if(starting){
			new Thread(){
				public void run(){
					Bubble bubble = new Bubble();
					float speedY = random.nextFloat() * 8;
					while(speedY < 1){
						speedY = random.nextFloat() * 8;
					}
					bubble.setSpeedY(speedY);
					bubble.setX(width / 2 - width / 3);
					bubble.setY(height);
					bubble.setBitmap(BitmapFactory.decodeResource(
							context.getResources(),
							drawableResource[random.nextInt(5)]));
					float speedX = random.nextFloat() - 0.5f;
					while(speedX == 0){
						speedX = random.nextFloat() - 0.5f;
					}
					bubble.setSpeedX(speedX * 1);
					bubbles.add(bubble);

					starting = false;

				}

				;
			}.start();
		} Paint paint = new Paint();
		paint.reset();
		paint.setColor(0X669999);//灰白色
		paint.setAlpha(45);//设置不透明度：透明为0，完全不透明为255
		List<Bubble> list = new ArrayList<Bubble>(bubbles);
		//依次绘制气泡
		for(Bubble bubble : list){
			//碰到上边界从数组中移除
			if(bubble.getY() - bubble.getSpeedY() <= 0){
				bubbles.remove(bubble);
			}
			else{
				int i = bubbles.indexOf(bubble);
				if(bubble.getX() + bubble.getSpeedX() <= 0){
					bubble.setSpeedX(-bubble.getSpeedX());
				}else if(bubble.getX() + bubble.getSpeedX() >= width - bubble
						.getBitmap().getWidth()){
					bubble.setSpeedX(-bubble.getSpeedX());
				}else{
					bubble.setX(bubble.getX() + bubble.getSpeedX());
				}
				bubble.setY(bubble.getY() - bubble.getSpeedY());

				bubbles.set(i, bubble);
				canvas.drawBitmap(bubble.getBitmap(), bubble.getX(),
								  bubble.getY(), paint);
			}
		}
		//刷新屏幕
		invalidate();
	}

	@Override
	public void onAdd(){
		starting = true;
	}

	@Override
	public void onReomve(){
		starting = false;
	}

	private class Bubble{
		private float speedY;
		private float speedX;
		private float x;
		private float y;

		private Bitmap bitmap;

		public Bitmap getBitmap(){
			return bitmap;
		}

		public void setBitmap(Bitmap bitmap){
			this.bitmap = bitmap;
		}

		public float getX(){
			return x;
		}

		public void setX(float x){
			this.x = x;
		}

		public float getY(){
			return y;
		}

		public void setY(float y){
			this.y = y;
		}

		public float getSpeedY(){
			return speedY;
		}

		public void setSpeedY(float speedY){
			this.speedY = speedY;
		}

		public float getSpeedX(){
			return speedX;
		}

		public void setSpeedX(float speedX){
			this.speedX = speedX;
		}

	}
}