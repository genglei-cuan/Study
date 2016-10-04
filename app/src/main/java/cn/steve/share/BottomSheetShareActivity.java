package cn.steve.share;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.steve.study.R;

public class BottomSheetShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_share);
        FloatingActionButton floatingActionButtonShare = (FloatingActionButton) findViewById(R.id.floatingActionButtonShare);
        if (floatingActionButtonShare != null) {
            floatingActionButtonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickActionButton();
                }
            });
        }
    }

    private void onClickActionButton() {
        ShareUtil shareUtil = new ShareUtil(this);
        shareUtil.share();
    }

}
