package cn.steve.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/10/12.
 */
public class MyGallery extends LinearLayout {

    private Context mContext;
    private Gallery gallery;
    private Calendar mCalendar;
    private MyGalleryAdapter myGalleryAdapter;
    private TextView galleryTextViewYearMonth;
    private ArrayList<MyGalleryModel> datas = new ArrayList<MyGalleryModel>();

    private int currentYear = 2015;
    private int currentMonth = 10;
    private int currentDay = 13;


    public MyGallery(Context context, Context mContext) {
        super(context);
        this.mContext = mContext;
    }

    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mCalendar = Calendar.getInstance();


        myGalleryAdapter = new MyGalleryAdapter(datas, mContext);
        getData(mCalendar);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_layout, this);

        galleryTextViewYearMonth = (TextView) view.findViewById(R.id.galleryTextViewYearMonth);
        gallery = (Gallery) view.findViewById(R.id.myGallery);
        gallery.setAdapter(myGalleryAdapter);
        gallery.setUnselectedAlpha(0.3f);
        gallery.setSpacing(40);
        gallery.setSelection(myGalleryAdapter.getCount() - 1);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyGalleryAdapter adapter = (MyGalleryAdapter) parent.getAdapter();
                MyGalleryModel model = adapter.getItem(position);
                galleryTextViewYearMonth.setText(model.getYear() + "年" + model.getMonth() + "月");

                //更新前一年的信息
                if (model.getDay() == 10) {
                    getData(mCalendar);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        gallery.setOnItemSelectedListener(listener);
    }

    //从当天往前数出一年的数据来，当到达最前面10天的时候，进行上年的更新
    private void getData(Calendar calendar) {

        currentYear = calendar.get(Calendar.YEAR);

        ArrayList<MyGalleryModel> temps = new ArrayList<MyGalleryModel>();

        int year = 2015;
        int month = 10;
        int day = 12;
        int week = 1;

        int days = calendar.get(Calendar.DAY_OF_YEAR);

        for (int i = 0; i < days; i++) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            week = calendar.get(Calendar.DAY_OF_WEEK);
            temps.add(new MyGalleryModel(year, month, day, week));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        Collections.reverse(temps);

        datas.addAll(0,temps);

        myGalleryAdapter.notifyDataSetChanged();

    }

}
