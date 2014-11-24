package cn.steve.viewflipper;

import cn.steve.study.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class AcitvityViewFlipper extends FragmentActivity {
	
	private FragmentManager FM = null;
	private FragmentTransaction FTS = null;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		super.setContentView(R.layout.activity_fragment);

		FM = getSupportFragmentManager();

		FTS = FM.beginTransaction();

		FTS.add(R.id.fragmentContainer, new FragmentViewFlipper());

		FTS.commit();

	}
}
