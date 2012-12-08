package net.takaiwa.adddynamicviewtoarrayadapter.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

/**
 * せっかくなのでスライドするアニメーション
 * @author takaiwa
 *
 */
public class CustomView3 extends View {

    private Paint mPaint;
    private int mWidth, mHeight, dx = 100;
    private boolean mAnimStartFlg = false;
    private static final int RADIUS = 20;

    public CustomView3(Context context, int width, int height) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

        mWidth = width - RADIUS;
        mHeight = height;
        setLayoutParams(new LayoutParams(width, height));
    }

    // 描画を有効にする
    public void startMyAnim() {
        mAnimStartFlg = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(true == mAnimStartFlg) {
            // 背景を透明に
            canvas.drawColor(Color.TRANSPARENT);
            // 円の描画
            canvas.drawCircle(dx, 20, RADIUS, mPaint);

            // 横へスライド
            dx+=5;
            if(dx > mWidth) {
                // 画面端で終了
                mAnimStartFlg = false;
            }
            else {
                invalidate();
            }
        }
    }
}
