package cn.steve.bottombar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;

import cn.steve.study.R;

public class BottomBarActivity extends AppCompatActivity {

  private BottomBar mBottomBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottombar);

    mBottomBar = BottomBar.attach(this, savedInstanceState);
    mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragmentContainer,
                                new com.roughike.bottombar.BottomBarFragment(BottomBarFragment.newInstance("Content for recents."), R.drawable.ic_recents, "Recents"),
                                new com.roughike.bottombar.BottomBarFragment(BottomBarFragment.newInstance("Content for favorites."), R.drawable.ic_favorites, "Favorites"),
                                new com.roughike.bottombar.BottomBarFragment(BottomBarFragment.newInstance("Content for nearby stuff."), R.drawable.ic_nearby, "Nearby"),
                                new com.roughike.bottombar.BottomBarFragment(BottomBarFragment.newInstance("Content for friends."), R.drawable.ic_friends, "Friends"),
                                new com.roughike.bottombar.BottomBarFragment(BottomBarFragment.newInstance("Content for food."), R.drawable.ic_restaurants, "Food")
    );

    // Setting colors for different tabs when there's more than three of them.
    // You can set colors for tabs in three different ways as shown below.
    mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
    mBottomBar.mapColorForTab(1, 0xFF5D4037);
    mBottomBar.mapColorForTab(2, "#7B1FA2");
    mBottomBar.mapColorForTab(3, "#FF5252");
    mBottomBar.mapColorForTab(4, "#FF9800");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    // Necessary to restore the BottomBar's state, otherwise we would
    // lose the current tab on orientation change.
    mBottomBar.onSaveInstanceState(outState);
  }
}
