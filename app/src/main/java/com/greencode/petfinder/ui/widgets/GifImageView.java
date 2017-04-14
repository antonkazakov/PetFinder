package com.greencode.petfinder.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RawRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class GifImageView extends View {

    private InputStream inputStream;
    private Movie movie;
    private Context context;
    private int heigth;
    private int width;

    public GifImageView(Context context) {
        super(context);
        this.context = context;
    }

    public GifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GifImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(width, heigth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setImageResource(@RawRes int resId){
        inputStream = context.getResources().openRawResource(resId);
        movie = Movie.decodeStream(inputStream);
        heigth = movie.height();
        width = movie.width();
        requestLayout();
    }

}
