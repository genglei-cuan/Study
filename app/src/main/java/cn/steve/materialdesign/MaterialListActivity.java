package cn.steve.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * material design 的demo
 *
 * Created by yantinggeng on 2015/11/10.
 */
public class MaterialListActivity extends AppCompatActivity implements MaterialItemListFragment.Interactior {

    String[] data = {"MaterialButton", "MaterialCard", "TranslucentBarColorActivity", "TranslucentBarImageActivity"};


    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_content);
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.materialContainer, MaterialItemListFragment.newInstance(data));
        fragmentTransaction.commit();
    }


    @Override
    public void onSelectedItem(int position) {
        switch (position) {
            case 0:
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.materialContainer, FragmentMaterialButton.newInstance("", ""));
                fragmentTransaction.commit();
                break;
            case 1:
                FragmentTransaction fragmentTransaction1 = manager.beginTransaction();
                fragmentTransaction1.replace(R.id.materialContainer, FragmentMaterialCard.newInstance("", ""));
                fragmentTransaction1.commit();
                break;
            case 2:
                Intent intent = new Intent(this, TranslucentBarColorActivity.class);
                startActivity(intent);
                break;
            case 3:
                Intent intent2 = new Intent(this, TranslucentBarImageActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
