package com.example.ywb.live.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;

import com.example.ywb.live.R;
import com.example.ywb.live.adapter.base.BaseViewHolder;
import com.example.ywb.live.adapter.base.SimpleAdapter;
import com.example.ywb.live.entity.PlayInfo;
import com.example.ywb.live.fragment.LivingUnPurifiedFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class FavoriteAdapter extends SimpleAdapter<PlayInfo.ResultBean.ListBean> {

    public FavoriteAdapter(Context context,int a, List<PlayInfo.ResultBean.ListBean> datas) {
        super(context, R.layout.item_live_fav, datas);
    }


    @Override
    protected void convert(BaseViewHolder holder, final PlayInfo.ResultBean.ListBean item) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.live_header_img_sdv);
        draweeView.setImageURI(Uri.parse(item.getData().getPic()));
        SimpleDraweeView draweeView2 = (SimpleDraweeView) holder.getView(R.id.face);
        draweeView2.setImageURI(Uri.parse(item.getData().getPic()));
        holder.getTextView(R.id.live_name_tv).setText(item.getData().getLive_name());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg=new Message();
                Bundle bundle=new Bundle();
                //将头像的地址发送到直播页面
                bundle.putString("img",item.getData().getPic());
                msg.setData(bundle);
                msg.what=0;
                LivingUnPurifiedFragment.mHandler.sendMessage(msg);
            }
        }).start();

    }
}
