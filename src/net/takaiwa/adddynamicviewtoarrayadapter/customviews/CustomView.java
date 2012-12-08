package net.takaiwa.adddynamicviewtoarrayadapter.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
/**
 * Android 2.x系では表示されない
 * @author takaiwa
 *
 */
public class CustomView extends View {

    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 背景透明
        canvas.drawColor(Color.TRANSPARENT);
        // 円の描画
        canvas.drawCircle(100, 20, 20, mPaint);
    }
}
