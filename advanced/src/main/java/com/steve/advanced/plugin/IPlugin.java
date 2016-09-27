package com.steve.advanced.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by steveyan on 16-9-27.
 */

public interface IPlugin {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void attach(Activity proxyActivity, PluginPackage pluginPackage);

    void onSaveInstanceState(Bundle outState);

    void onNewIntent(Intent intent);

    void onRestoreInstanceState(Bundle savedInstanceState);

    boolean onTouchEvent(MotionEvent event);

    boolean onKeyUp(int keyCode, KeyEvent event);

    void onWindowAttributesChanged(WindowManager.LayoutParams params);

    void onWindowFocusChanged(boolean hasFocus);

    void onBackPressed();

    boolean onCreateOptionsMenu(Menu menu);

    boolean onOptionsItemSelected(MenuItem item);
}
