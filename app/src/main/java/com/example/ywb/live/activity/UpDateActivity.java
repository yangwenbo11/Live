package com.example.ywb.live.activity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.base.BaseActivity;
import com.example.ywb.live.entity.PersonDataBean;
import com.example.ywb.live.http.Contants;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */

public class UpDateActivity extends BaseActivity {

    @BindView(R.id.nickName_et_upd)
    EditText nickName;
    @BindView(R.id.icon_upd_sdv)
    SimpleDraweeView icon;
    @BindView(R.id.sign_et_upd)
    EditText signName;
    @BindView(R.id.update_ok_btn)
    Button updateOk;

    private String uId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        initData();

        updateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updataData();
            }
        });
    }


    //获取个人资料
    public void initData() {
        SharedPreferences sp = getSharedPreferences("save_login", MODE_PRIVATE);
        uId = sp.getString("uid", null);

        OkHttpUtils.post()
                .url(Contants.API.PRESONDATA)
                .addParams("uid", uId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(UpDateActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        PersonDataBean bean = gson.fromJson(response, PersonDataBean.class);
                        if (bean.getError_code() == 0) {
                            //获得个人资料
                            String name = bean.getResult().getList().get(id).getUser_data().getUser_name();
                            String avatar = bean.getResult().getList().get(id).getUser_data().getAvatar();
                            String sign = bean.getResult().getList().get(id).getUser_data().getSign();
                            Log.e("TAG",uId);
                            //放到EditText控件上
                            nickName.setText(name);
                            icon.setImageURI(Uri.parse(avatar));
                            signName.setText(sign);
                        }
                    }
                });

    }

    //修改资料
    public void updataData(){

        String updName=nickName.getText().toString();
        String updSign=signName.getText().toString();

        OkHttpUtils.post()
                .url(Contants.API.UPDATEPRESONDATA)
                .addParams("uid",uId)
                .addParams("user_name",updName)
                .addParams("avatar","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489725190004&di=5f2b261d178057ab58dc192058dd9c0f&imgtype=0&src=http%3A%2F%2Fpic35.nipic.com%2F20131121%2F2531170_145358633000_2.jpg")
                .addParams("sign",updSign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(UpDateActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(UpDateActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                        //返回到“我的”页面
                        finish();
                    }
                });
    }
}
