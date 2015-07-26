package cn.steve.viewflipper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
import cn.steve.study.R;

public class FragmentViewFlipper extends Fragment {

  View view = null;
  private ViewFlipper viewFlipper;

  Animation leftInAnimation;
  Animation leftOutAnimation;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_viewflipper, container, false);

    this.viewFlipper = (ViewFlipper) view.findViewById(R.id.voice_rcd_viewflipper);

    // 动画效果
    leftInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.left_in);
    leftOutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.left_out);

    this.viewFlipper.setOnClickListener(new OnClickListener() {
      int i = 0;

      @Override
      public void onClick(View v) {

        // viewFlipper.setDisplayedChild(i++ % 3);
        viewFlipper.setInAnimation(leftInAnimation);
        viewFlipper.setOutAnimation(leftOutAnimation);
        viewFlipper.showNext();// 向右滑动

      }
    });

    return view;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);

  }

}
