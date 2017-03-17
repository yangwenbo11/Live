package com.example.ywb.live.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.ywb.live.R;
import com.example.ywb.live.adapter.LivingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yifeiyuan.library.PeriscopeLayout;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LivingUnPurifiedFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.living_re)
    RecyclerView recyclerView;
    @BindView(R.id.living_talk)
    Button talkBtn;
    @BindView(R.id.living_send)
    Button sendBtn;
    @BindView(R.id.living_like)
    Button likeBtn;
    @BindView(R.id.living_edit)
    EditText edit;
    @BindView(R.id.living_bottom_btn)
    RelativeLayout relativeLayout;
    @BindView(R.id.periscope)
    PeriscopeLayout periscopeLayout;
    @BindView(R.id.living_header_img_sdv)
    SimpleDraweeView simpleDraweeView;
    private static Bundle bundle;
    public static String imgAddress;//发送过来的头像地址


    private List<Integer> list = new ArrayList<>();

    public static LivingUnPurifiedFragment newInstance() {
        LivingUnPurifiedFragment livingUnPurifiedFragment = new LivingUnPurifiedFragment();

        return livingUnPurifiedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_living_unpurified, null);
        ButterKnife.bind(this, view);
        initData();
        initView();
        talkBtn.setOnClickListener(this);
        likeBtn.setOnClickListener(this);

        return view;
    }

    private void initData() {
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
        list.add(R.mipmap.default_head);
    }
public Context mContext=getActivity();
    private void initView() {
        //给RecyclerView设置适配器并设置成横向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        LivingAdapter livingAdapter = new LivingAdapter(list);
        recyclerView.setAdapter(livingAdapter);

        //设置头像
        simpleDraweeView.setImageURI(Uri.parse(imgAddress));

        timer.schedule(task,700,700);//700毫秒后执行task,经过700毫秒再次执行
    }

    public static Handler mHandler=new Handler(Looper.getMainLooper()){

        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    bundle=msg.getData();
                    imgAddress=bundle.getString("img");

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.living_talk:
                //将底部的三个按钮隐藏
                relativeLayout.setVisibility(View.GONE);
                //将输入框显示
                edit.setVisibility(View.VISIBLE);
                //获取焦点
                edit.requestFocus();
                openKeybord(edit, getActivity());
                break;
            case R.id.living_like:
                periscopeLayout.addHeart();
                break;
        }
    }

    //传递消息
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                periscopeLayout.addHeart();
            }
            super.handleMessage(msg);
        };
    };

    Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                // 需要做的事:发送消息
                // 不能将periscopeLayout.addHeart(); 直接写到run方法中，更新ui必须在主线程
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };





    /**
     * 打卡软键盘
     *
     * mEditText输入框
     * mContext上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    /**
     * 关闭软键盘
     *
     * mEditText输入框
     * mContext上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }



}
