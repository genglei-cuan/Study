package cn.research.activityfragmenty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import cn.steve.study.R;

public class ActivityFragmentRelation extends AppCompatActivity {

    private static final String TAG = "RelationActivity";

    private FrameLayout relationContainer;

    private byte[] datas = new byte[10 * 1024 * 1024];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativity_fragment_relation);
        relationContainer = (FrameLayout) findViewById(R.id.relationContainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.relationContainer, new RelationFragment()).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called with: " + "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called with: " + "");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called with: " + "");
    }
}
