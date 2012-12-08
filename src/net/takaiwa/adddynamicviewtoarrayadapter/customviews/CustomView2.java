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
 * Android 2.xでも表示されるようCustomViewを修正
 * @author takaiwa
 *
 */
public class CustomView2 extends View {

    private Paint mPaint;

    public CustomView2(Context context, int width, int height) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

        // 追加
        setLayoutParams(new LayoutParams(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 背景を透明に
        canvas.drawColor(Color.TRANSPARENT);
        // 円の描画
        canvas.drawCircle(100, 20, 20, mPaint);
    }
}
