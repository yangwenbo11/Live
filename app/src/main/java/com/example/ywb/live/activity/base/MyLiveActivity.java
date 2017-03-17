package com.example.ywb.live.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ywb.live.R;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyLiveActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylive);
    }

    public void initLiveData(){
        
    }
}
