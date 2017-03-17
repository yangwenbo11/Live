package com.example.ywb.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ywb.live.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LivingAdapter extends RecyclerView.Adapter<LivingAdapter.ViewHolder>  {

    private List<Integer> datas;
    private LayoutInflater inflater;

    public LivingAdapter(List<Integer> list) {
        this.datas=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.living_audience,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.img.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img1);
        }
    }
}
