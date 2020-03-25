package vgame.ir.view.component.round;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import vgame.ir.utils.Common;

public class RoundTextView extends AppCompatTextView {
    private RoundViewDelegate delegate;

    public RoundTextView(Context context) {
        this(context, null);
        setCustomFont();
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setCustomFont();
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(this, context, attrs);
        setCustomFont();
    }

    /** use delegate to set attr */
    public RoundViewDelegate getDelegate() {
        return delegate;
    }

    private void setCustomFont() {
        int style = getTypeface().getStyle();
        if (style == Typeface.BOLD) {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR_BOLD);
            setTypeface(myTypeface);
        } else {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR);
            setTypeface(myTypeface);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate.isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate.isRadiusHalfHeight()) {
            delegate.setCornerRadius(getHeight() / 2);
        } else {
            delegate.setBgSelector();
        }
    }
}
