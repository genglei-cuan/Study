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
 * 这里的分割线处理的不好，目前的处理方案是对group设置上下两条分割线，对于一组item结束设置分割线的情况，目前发现可能是由于复用的原因，似乎不能正常的显示。
 *
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
            //final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
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

            //bottom = child.getTop();
            //top = bottom - mDivider.getIntrinsicHeight();
            //mDivider.setBounds(left, top, right, bottom);
            //mDivider.draw(c);
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
        int childCount = parent.getAdapter().getItemCount();

        int position = parent.getChildAdapterPosition(view);
        if (position + 1 == childCount || position + 2 == childCount || position + 3 == childCount || position + 4 == childCount
            || position + 5 == childCount || position + 6 == childCount || position + 7 == childCount) {
            return true;
        }

        BaseDatePriceAdapter adapter = (BaseDatePriceAdapter) parent.getAdapter();
        for (int i = 1; i < 8; i++) {
            boolean isGroup = adapter.isGroupTitle(position + i);
            if (isGroup) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 如果是最后一行，则不需要绘制底部
        boolean isTitle = isTitle(view);
        if (isTitle || isMonthLast(view, parent)) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
