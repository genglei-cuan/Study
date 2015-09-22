package cn.steve.bottomMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import cn.steve.study.R;

/**
 * 类似于UC浏览器似的底部的菜单
 *
 * @author steve.yan
 */
public class BottomMenuActivity extends Activity {

    private List<String> titles;
    private List<List<String>> item_names; // 选项名称
    private List<List<Integer>> item_images; // 选项图标
    private MyPopupMenu myPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottommenu);
        initMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 系统菜单必须要加一个，才有效果
         */
        menu.add("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (myPopupMenu.isShowing()) {
            myPopupMenu.dismiss();
        } else {
            /**
             * 这句代码可以使菜单栏如对话框一样弹出的效果
             * myPopupMenu.setAnimationStyle(android.R.style.Animation_Dialog);
             */
            // 设置菜单栏显示位置
            myPopupMenu.showAtLocation(findViewById(R.id.layout), Gravity.BOTTOM, 0, 0);
            myPopupMenu.isShowing();
        }
        return false;
    }

    private void initMenu() {

        /**
         * 菜单栏分类标题
         */
        titles = new ArrayList<String>();
        titles = array2list(new String[]{
            "常用", "设置", "工具"
        });

        /**
         * 选项图标
         */
        item_images = new ArrayList<List<Integer>>();

        // 第一组
        Integer[] item_drawble =
            new Integer[]{
                R.drawable.ic_action_call, R.drawable.ic_action_camera,
                R.drawable.ic_action_copy, R.drawable.ic_action_crop,
                R.drawable.ic_action_cut,
                R.drawable.ic_action_discard, R.drawable.ic_action_download,
                R.drawable.ic_action_edit
            };
        item_images.add(addItems(item_drawble));

        // 第二组
        item_drawble =
            new Integer[]{
                R.drawable.ic_action_email, R.drawable.ic_action_full_screen,
                R.drawable.ic_action_help, R.drawable.ic_action_important,
                R.drawable.ic_action_map,
                R.drawable.ic_action_mic, R.drawable.ic_action_picture,
                R.drawable.ic_action_place
            };
        item_images.add(addItems(item_drawble));

        // 第三组
        item_drawble =
            new Integer[]{
                R.drawable.ic_action_refresh, R.drawable.ic_action_save,
                R.drawable.ic_action_search, R.drawable.ic_action_share,
                R.drawable.ic_action_switch_camera, R.drawable.ic_action_video,
                R.drawable.ic_action_web_site, R.drawable.ic_action_screen_rotation
            };
        item_images.add(addItems(item_drawble));

        /**
         * 选项名称
         */
        item_names = new ArrayList<List<String>>();

        // 第一组
        String[] item_name = new String[]{
            "电话", "相机", "复制", "裁剪", "剪切", "删除", "下载", "编辑"
        };
        item_names.add(array2list(item_name));

        item_name = new String[]{
            "邮件", "全屏", "帮助", "收藏", "地图", "语音", "图片", "定位"
        };
        item_names.add(array2list(item_name));

        item_name = new String[]{
            "刷新", "保存", "搜索", "分享", "切换", "录像", "浏览器", "旋转屏幕"
        };
        item_names.add(array2list(item_name));

        myPopupMenu = new MyPopupMenu(this, titles, item_names, item_images);

        /**
         * 设置菜单栏推拉动画效果 res/anim中的xml文件与styles.xml中的style配合使用
         */
        myPopupMenu.setAnimationStyle(R.style.PopupAnimation);

    }

    /**
     * 转换为List<String> 用于菜单栏中的菜单项图标赋值
     */
    private List<String> array2list(String[] values) {

        List<String> list = new ArrayList<String>();
        for (String var : values) {
            list.add(var);
        }

        return list;
    }

    /**
     * 转换为List<Integer> 用于菜单栏中的标题赋值
     */
    private List<Integer> addItems(Integer[] values) {

        List<Integer> list = new ArrayList<Integer>();
        for (Integer var : values) {
            list.add(var);
        }

        return list;
    }

}
