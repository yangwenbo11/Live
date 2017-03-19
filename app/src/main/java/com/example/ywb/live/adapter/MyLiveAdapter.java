package com.example.ywb.live.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ywb.live.R;
import com.example.ywb.live.entity.DelCodeBean;
import com.example.ywb.live.entity.MyLiveListBean;
import com.example.ywb.live.http.Contants;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyLiveAdapter extends RecyclerView.Adapter<MyLiveAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<MyLiveListBean.ResultBean.ListBean> list;
    Context mContext;
    private Button popDelBtn;
    private String uId;
    private PopupWindow window;
    private final View popView;

    public MyLiveAdapter(List list, Context context) {
        this.list = list;
        this.mContext = context;

        //popWindow的布局
        popView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_mylive, null);
        popDelBtn = (Button) popView.findViewById(R.id.pop_del_mylive);
        //点击删除
        popDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMyLive();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mylive, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获得id用于删除直播
        uId=list.get(position).getUser().getId()+"";
//        Toast.makeText(mContext,list.size(),Toast.LENGTH_LONG).show();

        holder.myLiveIconSdv.setImageURI(Uri.parse(list.get(position).getUser().getUser_data().getAvatar()));
        holder.myliveNameTv.setText(list.get(position).getData().getLive_name());//直播名
        holder.myliveFaceSdv.setImageURI(Uri.parse(list.get(position).getData().getPic()));
        if (list.get(position).getData().getStatus() == 0) {
            holder.myliveStateTv.setText("直播中...");
        } else if (list.get(position).getData().getStatus() == 1) {
            holder.myliveStateTv.setText("未直播");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView myLiveIconSdv;
        private TextView myliveNameTv;
        private SimpleDraweeView myliveFaceSdv;
        private TextView myliveStateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            myLiveIconSdv = (SimpleDraweeView) itemView.findViewById(R.id.mylive_header_img_sdv);
            myliveNameTv = (TextView) itemView.findViewById(R.id.mylive_name_tv);
            myliveFaceSdv = (SimpleDraweeView) itemView.findViewById(R.id.mylive_face);
            myliveStateTv = (TextView) itemView.findViewById(R.id.mylive_state);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(mContext,uId,Toast.LENGTH_LONG).show();
                    //创建popWindow对象，并设置布局，宽，高
                    window = new PopupWindow(popView, 440, 600);
                    //获取焦点
                    window.setFocusable(true);
                    //以下拉的方式显示，并且可以设置显示的位置
                    window.showAsDropDown(popDelBtn, 0, 20);

                    return false;
                }
            });
        }
    }

    //  删除功能未完成，不能确定点击的条目的直播id
    public void deleteMyLive() {
        OkHttpUtils.post()
                .url(Contants.API.DELETEMYLIVE)
                .addParams("live_id", uId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        DelCodeBean bean = gson.fromJson(response, DelCodeBean.class);
                        if (bean.getError_code() == 0) {

                            Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "删除失败", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}
