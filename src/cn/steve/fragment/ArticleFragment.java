package cn.steve.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import cn.steve.study.R;

public class ArticleFragment extends Fragment {

  private Button btn;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    System.out.println("ArticleFragmentCreate");
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    btn = (Button) getActivity().findViewById(R.id.btn);
    btn.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {
        // TODO Auto-generated method stub

        Toast.makeText(getActivity(), "f1", Toast.LENGTH_SHORT).show();

      }
    });

    System.out.println("onActivityCreated");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    System.out.println("onCreateView");
    return inflater.inflate(R.layout.article_fra1, container, false);
  }

}
