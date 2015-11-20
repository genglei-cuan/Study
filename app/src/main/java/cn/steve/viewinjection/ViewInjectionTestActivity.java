package cn.steve.viewinjection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

public class ViewInjectionTestActivity extends Activity {

    @Bind(R.id.view_injection1)
    TextView view_injection1;

    // 批量注入view
    @Bind({
            R.id.view_injection2, R.id.view_injection3, R.id.view_injection4, R.id.view_injection5,
            R.id.view_injection6,
            R.id.view_injection7
    })
    List<TextView> textviews;


    @OnClick(R.id.Button_injection8)
    public void onclickevent() {
        System.out.println("is click");
    }

    // 无需声明即可为控件添加监听器
    @OnClick(R.id.Button_injection9)
    public void onclickevent2(Button button) {
        button.setText("Button_injection9");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinjection);
        ButterKnife.bind(this);
        view_injection1.setText("kkkkk");
        for (int i = 0; i < textviews.size(); i++) {
            textviews.get(i).setText("view" + i);
        }
    }

}
