package cn.steve.dateCalendar.basic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yantinggeng on 2016/10/26.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontalLine(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {

        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    // 绘制水平线
    public void drawHorizontalLine(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //final int left = child.getLeft() - params.leftMargin;
            //final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            //final int top = child.getBottom() + params.bottomMargin;
            //final int bottom = top + mDivider.getIntrinsicHeight();

            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getBottom();
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        return pos >= childCount;
    }

    // 判断是否是标题，这里需要特别注意，仅仅因为标题是个textview 才这么粗鲁的判断
    private boolean isTitle(View child) {
        return child instanceof TextView;
    }

    private boolean isMonthLast(View view, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int position = parent.getChildLayoutPosition(view);
        if (position + 1 == childCount) {
            return true;
        }
        View next = parent.getChildAt(position + 1);
        return next != null && isTitle(next);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 如果是最后一行，则不需要绘制底部
        boolean isTitle = isTitle(view);

        int childAdapterPosition = parent.getChildAdapterPosition(view);

        if (isTitle) {
            System.out.println("Title:" + childAdapterPosition);
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
