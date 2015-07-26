/*
 * Copyright (C) 2008 The Android Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.steve.spinnerdroplayout;

import java.util.ArrayList;

import cn.steve.study.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * spinner的adapter
 * 
 */
public class SpinnerDropAdapter extends BaseAdapter {

  private final LayoutInflater mInflater;

  private final ArrayList<ListItem> mItems = new ArrayList<ListItem>();

  public static final int ITEM_SHORTCUT = 0;
  public static final int ITEM_APPWIDGET = 1;
  public static final int ITEM_LIVE_FOLDER = 2;
  public static final int ITEM_WALLPAPER = 3;

  /**
   * 我们的list要显示的项目的数据结构
   */
  public class ListItem {

    public final CharSequence text;
    public final Drawable image;
    public final int actionTag;

    public ListItem(Resources res, int textResourceId, int imageResourceId, int actionTag) {

      text = res.getString(textResourceId);

      if (imageResourceId != -1) {

        image = res.getDrawable(imageResourceId);

      } else {

        image = null;
      }

      this.actionTag = actionTag;

    }

  }


  // 构造函数中为组件添加数据
  public SpinnerDropAdapter(SpinnerDropActivity context) {
    super();

    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    Resources res = context.getResources();

    ListItem listItem1 =
        new ListItem(res, R.string.group_shortcuts, R.drawable.ic_launcher_shortcut, ITEM_SHORTCUT);

    ListItem listItem2 =
        new ListItem(res, R.string.group_widgets, R.drawable.ic_launcher_appwidget, ITEM_APPWIDGET);

    ListItem listItem3 =
        new ListItem(res, R.string.group_live_folders, R.drawable.ic_launcher_folder_live,
            ITEM_LIVE_FOLDER);

    ListItem listItem4 =
        new ListItem(res, R.string.group_wallpapers, R.drawable.ic_launcher_gallery, ITEM_WALLPAPER);

    mItems.add(listItem1);

    mItems.add(listItem2);

    mItems.add(listItem3);

    mItems.add(listItem4);

  }


  // 选中下拉列表后，在spinner上显示的界面
  public View getView(int position, View convertView, ViewGroup parent) {

    ListItem item = (ListItem) getItem(position);

    if (convertView == null) {

      convertView = mInflater.inflate(R.layout.spinner_label, parent, false);

    }

    TextView textView = (TextView) convertView;

    textView.setTag(item);

    textView.setText(item.text);

    textView.setCompoundDrawablesWithIntrinsicBounds(item.image, null, null, null);

    return textView;

  }

  public int getCount() {

    return mItems.size();

  }

  public Object getItem(int position) {

    return mItems.get(position);

  }

  public long getItemId(int position) {

    return position;
  }

  // 返回的是下拉列表项中的视图
  @Override
  public View getDropDownView(int position, View convertView, ViewGroup parent) {

    ListItem item = (ListItem) getItem(position);

    if (convertView == null) {

      convertView = mInflater.inflate(R.layout.spinner_drop_list_item, parent, false);
    }

    TextView textView = (TextView) convertView;

    textView.setTag(item);

    textView.setText(item.text + "内容");

    textView.setCompoundDrawablesWithIntrinsicBounds(item.image, null, null, null);

    return textView;

  }

}
