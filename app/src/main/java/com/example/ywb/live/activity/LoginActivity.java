package com.example.ywb.live.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.base.BaseActivity;
import com.example.ywb.live.entity.LoginResultBean;
import com.example.ywb.live.http.Contants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/15.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    public static boolean isLogin=false;//是否 是已经登录  默认为false

    private static String phone;
    private static String password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //将注册之后传过来的数据设置到控件上
        loginPhone.setText(phone);
        loginPassword.setText(password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                login();
            }
        });

    }

    public static Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            if (msg.what == 2) {
                Bundle bundle = msg.getData();
                phone = bundle.getString("phone");
                password = bundle.getString("password");

            }
        }
    };

    public void initData() {
        phone = loginPhone.getText().toString();
        password = loginPassword.getText().toString();


    }

    public void login() {
        OkHttpUtils.post()
                .url(Contants.API.LOGIN)
                .addParams("phone", phone)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        LoginResultBean bean = gson.fromJson(response, LoginResultBean.class);
                        if (bean.getError_code() == 0) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                            //如果登录成功，将值,id用sp保存
                            String uid=bean.getResult().getId()+"";//将long类型变为String
                            isLogin=true;
                            save(isLogin,uid);

                            //跳转到主页面
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void save(boolean log,String uid){
        //初始化sp
        SharedPreferences sp=getSharedPreferences("save_login", Activity.MODE_PRIVATE);
        //初始化SharedPreferences.Editor
        SharedPreferences.Editor editor=sp.edit();
        //保存数据
        editor.putBoolean("login",log);
        editor.putString("uid",uid);
        //提交
        editor.commit();

    }
}
