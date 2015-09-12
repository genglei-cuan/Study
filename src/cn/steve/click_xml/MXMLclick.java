
package cn.steve.click_xml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.steve.study.R;

/**
 * 在XML文件中设置相应的方法
 * 
 * @author Steve
 */
public class MXMLclick extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_click);

    }

    // 对应了按扭在文件中对应的函数名称
    public void onClick(View v) {
        // 根据相应的控件的ID进行相应
        if (v.getId() == R.id.textView) {
            Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
        }
    }

}
