package com.example.ywb.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.activity.base.BaseActivity;
import com.example.ywb.live.entity.RegisterResultBean;
import com.example.ywb.live.http.Contants;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/15.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.live_user_register_sdv)
    SimpleDraweeView icon;
    @BindView(R.id.phone_et_res)
    EditText phoneEdit;//电话
    @BindView(R.id.nickname_et_res)
    EditText nicknameEdit;//昵称
    @BindView(R.id.password_et_res)
    EditText passwordEdit;//密码
    @BindView(R.id.password_et_res_two)
    EditText passwordTwoEdit;//再次输密码
    @BindView(R.id.sign_et_res_two)
    EditText signEdit;//个性签名
    @BindView(R.id.register_btn)
    Button registerBtn;//注册按钮
    private String phone;
    private String nickName;
    private String passWord;
    private String passWordTwo;
    private String sign;
    private String iconStr;
    private boolean request_result;//是否注册成功


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                //验证
                verify();
            }
        });

    }

    public void initData(){
        phone = phoneEdit.getText().toString();
        nickName = nicknameEdit.getText().toString();
        passWord = passwordEdit.getText().toString();
        passWordTwo = passwordTwoEdit.getText().toString();
        sign = signEdit.getText().toString();
        iconStr = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=android%20%E5%A4%B4%E5%83%8F&step_word=&hs=2&pn=11&spn=0&di=90834035820&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2682821339%2C2014300825&os=1374161100%2C2911390326&simid=3459425215%2C341980104&adpicid=0&lpn=0&ln=1982&fr=&fmq=1489548447186_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg23.photophoto.cn%2F20120423%2F0020033094229209_s.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bri5p5ri5p5_z%26e3BvgAzdH3Ffi5oAzdH3Fa0db80b9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0";
    }
    //验证
    public boolean verify(){
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher matcher=p.matcher(phone);
        if(!matcher.matches()){
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号",Toast.LENGTH_LONG).show();
            return false;
        }

        if(nickName.equals("")){
            Toast.makeText(RegisterActivity.this, "昵称不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        if (passWord.length() < 6 || passWord.length() > 16) {
            Toast.makeText(RegisterActivity.this, "请正确输入密码",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!passWord.equals(passWordTwo)) {
            Toast.makeText(RegisterActivity.this, "核对两次输入的密码", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sign.equals("")){
            Toast.makeText(RegisterActivity.this, "签名不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        //上传数据到服务器
        OkHttpUtils.post()
                .url(Contants.API.REGISTER)
                .addParams("phone",phone)
                .addParams("user_name",nickName)
                .addParams("avatar",iconStr)
                .addParams("sign",sign)
                .addParams("password",passWord)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        RegisterResultBean resultBean=gson.fromJson(response,RegisterResultBean.class);
                        Log.e("TAG",response);
                        if(resultBean.isResult()){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            request_result=true;
                            //注册成功后跳转到登录界面，并将账号和密码传过去
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            Message message=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putString("phone", phone);
                            bundle.putString("password", passWord);
                            message.setData(bundle);
                            message.what=2;
                            LoginActivity.handler.sendMessage(message);
                            startActivity(intent);
                        }
                    }
                });
        return  request_result;

    }

}
