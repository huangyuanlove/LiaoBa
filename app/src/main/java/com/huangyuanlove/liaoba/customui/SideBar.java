package com.huangyuanlove.liaoba.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View {  
	// 触摸事件  
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;  
	// 26个字母  
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",  
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
		"W", "X", "Y", "Z"};  
	private int choose = -1;// 选中  
	private Paint paint = new Paint();  

	private TextView mTextDialog;  

	/** 
	 * 为SideBar设置显示字母的TextView 
	 * @param mTextDialog 
	 */  
	public void setTextView(TextView mTextDialog) {  
		this.mTextDialog = mTextDialog;  
	}  


	public SideBar(Context context, AttributeSet attrs, int defStyle) {  
		super(context, attrs, defStyle);  
	}  

	public SideBar(Context context, AttributeSet attrs) {  
		super(context, attrs);  
	}  

	public SideBar(Context context) {  
		super(context);  
	}  

	/** 
	 * 重写这个方法 
	 */  
	 protected void onDraw(Canvas canvas) {  
		 super.onDraw(canvas);  
		 // 获取焦点改变背景颜色.  
		 int height = getHeight();// 获取对应高度  
		 int width = getWidth(); // 获取对应宽度  
		 int singleHeight = height / b.length;// 获取每一个字母的高度  

		 for (int i = 0; i < b.length; i++) {  
//			 paint.setColor(Color.rgb(33, 65, 98));
			 paint.setColor(Color.parseColor("#88abcdef"));
			 paint.setTypeface(Typeface.DEFAULT_BOLD);
			 paint.setAntiAlias(true);  
			 paint.setTextSize(singleHeight-4);

			 // x坐标等于中间-字符串宽度的一半.  
			 float xPos = width / 2 - paint.measureText(b[i]) / 2;
			 float yPos = singleHeight * i + singleHeight;
			 // 选中的状态  
//			 if (i == choose) { 
//				 //jhd  绘制方块
//				 float wid=width*2/3;
//				 float hei=singleHeight*4/5;
//				 //jhd 方块位置
//				 float rectXPos=width / 2 - wid / 2;
//				 float rectYPos=singleHeight * i + singleHeight;
//				//绘制选中的时候的方块
//				 canvas.drawRect(rectXPos, rectYPos-hei, rectXPos+wid, rectYPos, paint);
//				 
//				 paint.setColor(Color.parseColor("#3399ff"));  
//				 paint.setFakeBoldText(true); 
//
//			 }  
			 canvas.drawText(b[i], xPos, yPos, paint);  
			 paint.reset();// 重置画笔  
		 }  

	 }  

	 @Override  
	 public boolean dispatchTouchEvent(MotionEvent event) {  
		 final int action = event.getAction();  
		 final float y = event.getY();// 点击y坐标  
		 final int oldChoose = choose;  
		 final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;  
		 final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.  

		 switch (action) {  
		 case MotionEvent.ACTION_UP:  
			 setBackgroundResource(android.R.color.transparent);//设置透明 
			 choose = -1;//  
			 invalidate();  //应用起来
			 if (mTextDialog != null) {  
				 mTextDialog.setVisibility(View.GONE);  
			 }  
			 break;  

		 default:  
//			 setBackgroundResource(R.drawable.sidebar_background);  
			 if (oldChoose != c) {  
				 if (c >= 0 && c < b.length) {  
					 if (listener != null) {  
						 listener.onTouchingLetterChanged(b[c]);
						 Log.e("jhd", "onTouchingLetterChanged 正在触发");
					 }  
					 if (mTextDialog != null) {  
						 Log.e("jhd", "mTextDialog 不为 null");
						 mTextDialog.setText(b[c]);  
						 mTextDialog.setVisibility(View.VISIBLE);  
					 }  

					 choose = c;  
					 invalidate();  
				 }  
			 }  

			 break;  
		 }  
		 return true;  
	 }  

	 /** 
	  * 向外公开的方法 
	  *  
	  * @param onTouchingLetterChangedListener 
	  */  
	 public void setOnTouchingLetterChangedListener(  
			 OnTouchingLetterChangedListener onTouchingLetterChangedListener) {  
		 this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;  
	 }  

	 /** 
	  * 接口 
	  *  
	  * @author coder 
	  *  
	  */  
	 public interface OnTouchingLetterChangedListener {  
		 public void onTouchingLetterChanged(String s);  
	 }  

} 