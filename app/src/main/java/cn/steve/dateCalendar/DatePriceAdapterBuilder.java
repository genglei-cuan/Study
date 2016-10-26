package cn.steve.dateCalendar;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import cn.steve.dateCalendar.basic.BaseBuilder;
import cn.steve.dateCalendar.basic.BaseDatePriceAdapter;
import cn.steve.dateCalendar.basic.DayItem;
import cn.steve.dateCalendar.basic.DayPickerBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yantinggeng on 2016/10/24.
 */

public class DatePriceAdapterBuilder extends BaseBuilder {

    private ArrayMap<String, String> vacations;
    private ArrayMap<String, DatePriceVO> datas;
    private String selectedDate = "";

    public DatePriceAdapterBuilder() {
        init();
    }

    private void init() {
        vacations = new ArrayMap<>();
    }

    private void setDatas(ArrayMap<String, DatePriceVO> datas) {
        this.datas = datas;
    }

    public DatePriceAdapterBuilder withSelected(String date) {
        this.selectedDate = date;
        return this;
    }

    public void getDayAdapter(final ArrayMap<String, DatePriceVO> datas, Subscriber<BaseDatePriceAdapter> subscriber) {
        Observable.create(new Observable.OnSubscribe<BaseDatePriceAdapter>() {
            @Override
            public void call(Subscriber<? super BaseDatePriceAdapter> subscriber) {
                setDatas(datas);
                ArrayList<AdapterItem> adapterItems = generateAdapterDatas();
                final DatePriceAdapter adapter = new DatePriceAdapter();
                adapter.setOnItemClickListener(new BaseDatePriceAdapter.OnAdapterItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        Toast.makeText(view.getContext(), adapter.getItem(position).getDate(), Toast.LENGTH_SHORT).show();
                    }
                });
                adapter.setDatas(adapterItems);
                subscriber.onNext(adapter);
            }
        })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
    }


    private ArrayList<AdapterItem> generateAdapterDatas() {
        ArrayList<AdapterItem> adapterItems = new ArrayList<>();

        // 1. get the all year and months
        ArrayList<DateYearMonth> dateYearMonthArrayList = new ArrayList<>();
        Set<String> datesHasDatas = datas.keySet();
        for (String datesHasData : datesHasDatas) {
            DateYearMonth yearMonth = getYearMonth(datesHasData);
            if (yearMonth == null) {
                continue;
            }
            if (dateYearMonthArrayList.contains(yearMonth)) {
                continue;
            }
            dateYearMonthArrayList.add(yearMonth);
        }

        // 2. get all the DayItems
        DayPickerBuilder builder = new DayPickerBuilder();
        for (DateYearMonth dateYearMonth : dateYearMonthArrayList) {

            // group
            AdapterItem item = new AdapterItem();
            int year = dateYearMonth.getYear();
            int month = dateYearMonth.getMonth();
            item.setGroup(year + "年" + month + "月");
            item.setAdapterItemType(AdapterItem.TYPE_GROUP);
            adapterItems.add(item);

            // items
            ArrayList<DayItem> c = builder.generateMonth(year, month);
            adapterItems.addAll(convert(c));

        }

        return adapterItems;
    }


    private ArrayList<AdapterItem> convert(ArrayList<DayItem> dayItemArrayList) {
        ArrayList<AdapterItem> adapterItems = new ArrayList<>();

        for (DayItem dayItem : dayItemArrayList) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.setAdapterItemType(AdapterItem.TYPE_ITEM);

            String date = dayItem.getDate();

            if (date != null) {
                if (date.equals(selectedDate)) {
                    adapterItem.setSelected(true);
                }
            }
            adapterItem.setDate(date);
            adapterItem.setDay(dayItem.getDay());

            DatePriceVO datePriceVO = datas.get(date);
            if (datePriceVO == null) {
                adapterItems.add(adapterItem);
                continue;
            }
            String festival = vacations.get(date);

            adapterItem.setPrice(datePriceVO.getPrice());
            adapterItem.setFestival(festival);
            adapterItem.setStock(datePriceVO.getStock());

            adapterItems.add(adapterItem);
        }

        return adapterItems;
    }

}
