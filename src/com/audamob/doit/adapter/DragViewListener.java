/**
 * 
 * @author : AUDAMOB
 *
 * Un Listener personalisé permet de drag and drop a view
 */

package com.audamob.doit.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import com.audamob.doit.utils.LayoutResizerUtil;



public class DragViewListener implements View.OnTouchListener {
	private Activity activity;
	private boolean isActionDown;
	private MarginLayoutParams mLayoutParams;
	private View view;
	private int dx = 0;
	private int dy = 0;
	private int lastMarginLeft=0;
	private int initialMarginTop;

	
	private float lastAngleDeRotation=0; 
	public DragViewListener(Activity a, View v, MarginLayoutParams marginLayoutParams) {
		// TODO Auto-generated constructor stub
		this.activity = a;
		this.view = v;
		this.mLayoutParams = marginLayoutParams;
		this.isActionDown = false;
		
		this.initialMarginTop = mLayoutParams.topMargin;
	}

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		 final int X = (int) event.getRawX();
		    final int Y = (int) event.getRawY();
		    switch (event.getAction() & MotionEvent.ACTION_MASK) {
		        case MotionEvent.ACTION_DOWN:
		        	
		            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

		            dx = X - lParams.leftMargin;
		            dy = Y - lParams.topMargin;
		            break;
		        case MotionEvent.ACTION_UP:
		            break;
		        case MotionEvent.ACTION_POINTER_DOWN:
		            break;
		        case MotionEvent.ACTION_POINTER_UP:
		            break;
		        case MotionEvent.ACTION_MOVE:
		            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		            layoutParams.leftMargin = X - dx;
		            layoutParams.topMargin = Y - dy;
		            layoutParams.rightMargin = -400;
		            layoutParams.bottomMargin = -400;
		          
		            int deltaangle=lastMarginLeft - (layoutParams.leftMargin + (layoutParams.width / 2)) ;
		            view.setLayoutParams(layoutParams);
		            /**
		             * Rotate View pour chaque variation de position
		             */
		           
		            view.setRotation(getRotationDegree(-deltaangle));
		            
		            break;
		    }
		    

		return true;
	}
	
	
	/**
	 * 
	 * @param deltaX : variation de position de view sur l'axe (OX)
	 * @return angle de rotation
	 */
	private float getRotationDegree(int deltaX){
		Log.e("DragViewListener","deltaX : "+deltaX);
		//récuperation de largeur de screen
		int screenWidth=LayoutResizerUtil.getDisplayWidthInPx(activity);
		Log.e("DragViewListener","screenwidth : "+screenWidth);
		//Calcul de pourcentage de variation de déplacement par rapport à l'axe OX 
		float pourcentage=(float) deltaX/screenWidth;
		Log.e("DragViewListener",pourcentage+"%");
		//Calcul d'angle de rotation de View
		float angleDeRotation=pourcentage*360;
		
		return (float)angleDeRotation/100;
	}
}
