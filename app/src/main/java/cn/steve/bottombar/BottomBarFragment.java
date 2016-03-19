package cn.steve.bottombar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Iiro Krankka (http://github.com/roughike)
 */
public class BottomBarFragment extends Fragment {

  private static final String ARG_TEXT = "ARG_TEXT";

  public BottomBarFragment() {
  }

  public static BottomBarFragment newInstance(String text) {
    Bundle args = new Bundle();
    args.putString(ARG_TEXT, text);

    BottomBarFragment bottomBarFragment = new BottomBarFragment();
    bottomBarFragment.setArguments(args);

    return bottomBarFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    TextView textView = new TextView(getActivity());
    textView.setText(getArguments().getString(ARG_TEXT));

    return textView;
  }
}
