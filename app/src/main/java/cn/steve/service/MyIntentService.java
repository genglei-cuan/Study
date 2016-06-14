package cn.steve.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * date 2016/6/14
 *
 * created by yantinggeng
 *
 * Description :
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    private static final String ACTION_FOO = "cn.steve.service.action.FOO";
    private static final String ACTION_BAZ = "cn.steve.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "cn.steve.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "cn.steve.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionFoo(String param1, String param2) {
        Log.d(TAG, "handleActionFoo() called with: " + "param1 = [" + param1 + "], param2 = [" + param2 + "]");
    }

    private void handleActionBaz(String param1, String param2) {
        Log.d(TAG, "handleActionBaz() called with: " + "param1 = [" + param1 + "], param2 = [" + param2 + "]");
    }
}
