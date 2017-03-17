package com.example.ywb.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.LoginActivity;
import com.example.ywb.live.activity.RegisterActivity;
import com.example.ywb.live.activity.UpDateActivity;
import com.example.ywb.live.activity.base.MyLiveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by xiaoyuan on 17/3/8.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.login)
    Button loginBtn;
    @BindView(R.id.register)
    Button registerBtn;
    @BindView(R.id.update)
    Button updateBtn;
    @BindView(R.id.my_live)
    Button myLiveBtn;



    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frg_mine,null);
        ButterKnife.bind(this,view);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        myLiveBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.update:
                startActivity(new Intent(getActivity(), UpDateActivity.class));
                break;
            case R.id.my_live:
                startActivity(new Intent(getActivity(), MyLiveActivity.class));
                break;
        }
    }
}
