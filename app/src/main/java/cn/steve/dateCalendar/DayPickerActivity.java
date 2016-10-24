package cn.steve.dateCalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DayPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datecalendar);
        DayPickerView dayPickerView = (DayPickerView) findViewById(R.id.daypicker);
        ArrayMap<String, DatePriceVO> datas = new ArrayMap<>();

        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < 20; i++) {
            DatePriceVO vo = new DatePriceVO();
            String date = formatDate(cal);
            vo.setDate(date);
            vo.setPrice("100");
            vo.setStock(100);
            datas.put(date, vo);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        dayPickerView.setData(datas);
    }


    private String formatDate(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = cal.getTime();
        return sdf.format(date);
    }
}
