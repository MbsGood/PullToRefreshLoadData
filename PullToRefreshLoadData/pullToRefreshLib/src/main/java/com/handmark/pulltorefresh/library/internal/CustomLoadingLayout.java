package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

public class CustomLoadingLayout extends LoadingLayout{
	

	public CustomLoadingLayout(Context context, Mode mode,
			Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);
	}

	@Override
	protected int getDefaultDrawableResId() {
		return R.drawable.refresh_animation;
	}

	@Override
	protected void onLoadingDrawableSet(Drawable imageDrawable) {
		
	}

	@Override
	protected void onPullImpl(float scaleOfLayout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pullToRefreshImpl() {
		AnimationDrawable animationDrawable = (AnimationDrawable)mHeaderImage.getDrawable();
		if(animationDrawable.isRunning()){
			animationDrawable.stop();
			animationDrawable.selectDrawable(0);
		}
	}

	@Override
	protected void refreshingImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void releaseToRefreshImpl() {
		AnimationDrawable animationDrawable = (AnimationDrawable)mHeaderImage.getDrawable();
		if(!animationDrawable.isRunning())
			animationDrawable.start();
	}

	@Override
	protected void resetImpl() {
		// TODO Auto-generated method stub
		
	}

}
