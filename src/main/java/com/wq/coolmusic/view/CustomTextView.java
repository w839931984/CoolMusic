package com.wq.coolmusic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.wq.coolmusic.utils.DensityUtils;

/**
 * Created by WQ on 2017/5/22.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawable = drawables[0];

        int height = this.getLineHeight() + DensityUtils.dp2px(this.getContext(), 10);
        int width = drawable.getMinimumWidth() * height / drawable.getMinimumHeight();

        drawable.setBounds(0, 0, width, height);
        this.setCompoundDrawables(drawable, null, null, null);
    }
}
