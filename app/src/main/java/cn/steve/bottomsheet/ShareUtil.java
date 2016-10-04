package cn.steve.bottomsheet;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by steveyan on 16-10-4.
 */

public class ShareUtil {

    public static void share(AppCompatActivity context) {
        BottomSheetShareFragment shareFragment = new BottomSheetShareFragment();
        shareFragment.show(context.getSupportFragmentManager(), "shareFragment");
    }

}
