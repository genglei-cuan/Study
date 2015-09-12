
package cn.steve.bottomtext;

import java.util.ArrayList;
import java.util.List;

import cn.steve.study.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;

/**
 * 修改的UC菜单式的界面 将菜单换成了一个个textview textview可以进行内容上下滚动
 * 
 * @author steve.yan
 */
public class BottomTextActivity extends Activity {

    private List<String> titles;

    private BottomPopupMenu myPopupMenu;
    MypopMenuTitleListner listner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_text);
        initMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 系统菜单必须要加一个，才有效果
         */
        menu.add("");
        myPopupMenu.setDefaultContent();
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

        listner = new MypopMenuTitleListner();
        /**
         * 菜单栏分类标题
         */
        titles = new ArrayList<String>();
        titles = array2list(new String[] {
                "驾车", "公交", "步行"
        });

        myPopupMenu = new BottomPopupMenu(this, titles);

        /**
         * 设置菜单栏推拉动画效果 res/anim中的xml文件与styles.xml中的style配合使用
         */
        myPopupMenu.setAnimationStyle(R.style.PopupAnimation);
        myPopupMenu.setListner(listner);

    }

    /**
     * 转换为List<String> 用于菜单栏中的菜单项图标赋值
     * 
     * @param values
     * @return
     */
    private List<String> array2list(String[] values) {

        List<String> list = new ArrayList<String>();
        for (String var : values) {
            list.add(var);
        }

        return list;
    }

    class MypopMenuTitleListner implements BottomPopupMenu.TitleChangeListener {
        @Override
        public String getContent(int i) {
            switch (i) {
                case 0:
                    // return titles.get(0);
                    return "aaaaaaaaaaaaaaaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx亚麻贴  哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa这是结尾";
                case 1:

                    // return titles.get(1);
                    return "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";

                case 2:
                    // return titles.get(2);
                    return "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuccccccccccc";
                default:
                    return titles.get(0);
            }

        }

    }

}
