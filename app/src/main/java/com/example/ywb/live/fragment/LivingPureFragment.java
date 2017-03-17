package com.example.ywb.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ywb.live.R;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LivingPureFragment extends BaseFragment {

    public static LivingPureFragment newInstance(){
        LivingPureFragment livingPureFragment=new LivingPureFragment();
        return livingPureFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frg_living_pure,null);
        return view;
    }

}
