package cn.steve.contextmean;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListener(context);
    }

    public MyTextView(Context context) {
        super(context);
        setListener(context);
    }

    private void setListener(final Context context) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContextActivity_2.class);
                context.startActivity(intent);
                getContext().startActivity(intent);
            }
        });
    }

}
