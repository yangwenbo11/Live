package com.example.ywb.live.activity.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.adapter.MyLiveAdapter;
import com.example.ywb.live.entity.MyLiveListBean;
import com.example.ywb.live.http.Contants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyLiveActivity extends BaseActivity {

    @BindView(R.id.mylive_rv)
    RecyclerView myliveRv;
    private String uId;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylive);
        ButterKnife.bind(this);
        initLiveData();
    }

    public void initLiveData() {
        SharedPreferences sp = getSharedPreferences("save_login", MODE_PRIVATE);
        uId = sp.getString("uid", null);

        OkHttpUtils.post()
                .url(Contants.API.MYLIVELIST)
                .addParams("uid", uId+"")
                .addParams("page", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MyLiveActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        MyLiveListBean bean = gson.fromJson(response, MyLiveListBean.class);
                        MyLiveAdapter myLiveAdapter=new MyLiveAdapter(bean.getResult().getList(),MyLiveActivity.this);
                        myliveRv.setAdapter(myLiveAdapter);
                        myliveRv.setLayoutManager(new LinearLayoutManager(MyLiveActivity.this));
                        myliveRv.setItemAnimator(new DefaultItemAnimator());
                    }
                });

    }

}
