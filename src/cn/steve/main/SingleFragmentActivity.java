package cn.steve.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import cn.steve.study.R;





/**
 * 单纯的一个界面的
 * 
 * @author steve.yan
 * 
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

	public abstract Fragment createFragment();

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_fragment);
		// 获取管理器
		FragmentManager fm = getSupportFragmentManager();
		// 获取对应的fragment
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

		// 判断fragment是否存在
		if (fragment == null) {
			fragment = createFragment();
			FragmentTransaction fragmentTS = fm.beginTransaction();
			fragmentTS.add(R.id.fragmentContainer, fragment).commit();
		}

	}

}
