package cn.steve.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.steve.study.R;

/**
 * 邮件列表中已读和未读
 *
 * Created by yantinggeng on 2015/12/25.
 */

public class MessageListItem extends RelativeLayout {

    private static final int[] STATE_MESSAGE_READED = {R.attr.state_message_readed};
    private boolean mMessageReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageReaded(boolean readed) {
        if (this.mMessageReaded != readed) {
            mMessageReaded = readed;
            //Call this to force a view to update its drawable state.
            refreshDrawableState();
        }
    }


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mMessageReaded) {
            //添加 X 个额外的状态就加 X
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            //将自定义的状态值合并
            mergeDrawableStates(drawableState,STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
}
