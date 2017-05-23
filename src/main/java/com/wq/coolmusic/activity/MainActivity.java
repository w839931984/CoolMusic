package com.wq.coolmusic.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wq.coolmusic.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;

    private final int[] mIcons = {
            R.drawable.selector_actionbar_music,
            R.drawable.selector_actionbar_discover,
            R.drawable.selector_actionbar_friends
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setStatusBar();
    }

    private void initData() {
        mFragments = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Fragment fragment = new Fragment();
            mFragments.add(fragment);
        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++){
            View view = View.inflate(this, R.layout.tab_item, null);
            ImageView imageview = (ImageView) view.findViewById(R.id.image_view);
            imageview.setImageResource(mIcons[i]);
            mTabLayout.getTabAt(i).setCustomView(view);
        }
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.actionbar_menu_button);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        setStatusBar();
    }

    private void setStatusBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
