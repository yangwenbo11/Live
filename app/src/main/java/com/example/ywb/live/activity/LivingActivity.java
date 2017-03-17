package com.example.ywb.live.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.base.BaseActivity;
import com.example.ywb.live.fragment.LivingPureFragment;
import com.example.ywb.live.fragment.LivingUnPurifiedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LivingActivity extends BaseActivity {
    @BindView(R.id.living_viewpager)
    ViewPager viewpager;
    private List<Fragment> fragments=new ArrayList<>();
    private FragmentPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        LivingPureFragment pureFragment=new LivingPureFragment();
        LivingUnPurifiedFragment unPurefiedFragment=new LivingUnPurifiedFragment();
        fragments.add(pureFragment);//第0页
        fragments.add(unPurefiedFragment);//第1页

        pagerAdapter=new FragmentPagerAdapter(LivingActivity.this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(1);//默认加载第一页
    }


}
