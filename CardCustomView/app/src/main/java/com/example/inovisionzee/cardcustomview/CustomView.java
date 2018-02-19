package com.example.inovisionzee.cardcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Color;
import android.graphics.Paint;


public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth() / 2;

        Path path = new Path();
        path.moveTo( w, 0);
        path.lineTo( 3 * w , 0);
        path.lineTo( 3* w , w);
        path.lineTo( w , 0);
        path.close();

        Paint p = new Paint();
        p.setColor( Color.RED );

        canvas.drawPath(path, p);
    }
}
