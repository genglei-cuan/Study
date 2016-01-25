package cn.steve.edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.EditText;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/1/25.
 */
public class EditTextViewMainActivity extends AppCompatActivity {

    private android.widget.EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        this.editText = (EditText) findViewById(R.id.editText);
        this.editText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

    }
}
