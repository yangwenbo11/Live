package com.example.ywb.live.adapter;

import android.content.Context;
import android.net.Uri;

import com.example.ywb.live.R;
import com.example.ywb.live.adapter.base.BaseViewHolder;
import com.example.ywb.live.adapter.base.SimpleAdapter;
import com.example.ywb.live.entity.PlayInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class HotAdapter extends SimpleAdapter<PlayInfo.ResultBean.ListBean> {

    public HotAdapter(Context context,int a, List<PlayInfo.ResultBean.ListBean> datas) {
        super(context, R.layout.item_live_hot, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, final PlayInfo.ResultBean.ListBean item) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.hot_header_img_sdv);
        draweeView.setImageURI(Uri.parse(item.getData().getPic()));
        SimpleDraweeView draweeView2 = (SimpleDraweeView) holder.getView(R.id.hot_face);
        draweeView2.setImageURI(Uri.parse(item.getData().getPic()));
        holder.getTextView(R.id.hot_name_tv).setText(item.getData().getLive_name());

    }
}
