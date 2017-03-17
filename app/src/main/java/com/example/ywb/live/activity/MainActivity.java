package com.example.ywb.live.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.base.BaseActivity;
import com.example.ywb.live.fragment.HomeFragment;
import com.example.ywb.live.fragment.MineFragment;
import com.example.ywb.live.sky.CameraActivity;
import com.ksyun.media.streamer.encoder.VideoEncodeFormat;
import com.ksyun.media.streamer.framework.AVConst;
import com.ksyun.media.streamer.kit.StreamerConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    ViewPager contents;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbMine)
    RadioButton rbMine;
    @BindView(R.id.rgTools)
    RadioGroup rgTools;
    @BindView(R.id.live_btn)
    ImageView liveBtn;

    private FragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments = new ArrayList<>();

    private String uId;
    boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        SharedPreferences sp=getSharedPreferences("save_login",MODE_PRIVATE);
        isLogin=sp.getBoolean("login",false);
        if(isLogin){
            //isLogin值为true，登录成功

            uId=sp.getString("uid",null);

            Toast.makeText(MainActivity.this,"已登录",Toast.LENGTH_LONG).show();
        }else{
            //未登录
            Toast.makeText(MainActivity.this,"没有登录",Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        contents.setAdapter(mAdapter);
    }

    @OnClick({R.id.rbHome, R.id.rbMine,R.id.live_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                contents.setCurrentItem(0);
                break;
            case R.id.rbMine:
                contents.setCurrentItem(1);
                break;
            case R.id.live_btn:
                if(isLogin==false){
                    //如果未登录，跳转到登录界面
                    Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }else{
                    //如果已登录，进入直播界面
                    startLive();
                }

                break;
        }
    }
    private void startLive() {
        int videoResolution;
        int encodeType;
        int encodeMethod;
        int encodeScene;
        int encodeProfile;
        int orientation;
        videoResolution = StreamerConstants.VIDEO_RESOLUTION_360P;
        encodeType = AVConst.CODEC_ID_AVC;
        encodeMethod = StreamerConstants.ENCODE_METHOD_SOFTWARE;
        encodeScene = VideoEncodeFormat.ENCODE_SCENE_SHOWSELF;
        encodeProfile = VideoEncodeFormat.ENCODE_PROFILE_LOW_POWER;
        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //采集帧率 视频码率  音频码率 分辨率  方向  编码类型  编码方法  编码场景  功耗  自动推流  调试信息
        CameraActivity.startActivity(MainActivity.this, 0,
                15, 800,48, videoResolution, orientation, encodeType,encodeMethod,
                encodeScene, encodeProfile, false, true,uId);
    }

}
