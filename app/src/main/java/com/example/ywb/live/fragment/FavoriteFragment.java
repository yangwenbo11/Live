package com.example.ywb.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.ywb.live.R;
import com.example.ywb.live.adapter.FavoriteAdapter;
import com.example.ywb.live.adapter.base.BaseAdapter;
import com.example.ywb.live.entity.PlayInfo;
import com.example.ywb.live.http.Contants;
import com.example.ywb.live.model.NetDbAdapter;
import com.example.ywb.live.sky.TextureVideoActivity;
import com.example.ywb.live.sky.TextureVodActivity;
import com.example.ywb.live.util.Settings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by xiaoyuan on 17/3/8.
 */

public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.favorite_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout mRefreshLayout;

    FavoriteAdapter favoriteAdapter;
    int page = 1;
    private NetDbAdapter netDb;
    private PlayInfo a;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_favorite, container, false);
        ButterKnife.bind(this, view);
        initListener();
        getData(page);
        return view;
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    private void initListener() {
        mRefreshLayout.setLoadMore(true);
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Log.e("TAG", "上拉刷新");
                refreshDate();
                mRefreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //上拉加载更多...
                Log.e("TAG", "下拉加载");
                if (page == 0) {
                    loadMoreData();
                } else {
                    Toast.makeText(getContext(), "已经没有跟多数据了", Toast.LENGTH_SHORT).show();
                }
                mRefreshLayout.finishRefreshLoadMore();
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getData(int page) {
        OkHttpUtils
                .post()
                .url(Contants.API.FAVORITEINFO)
                .addParams("type", "1")
                .addParams("page", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<List<PlayInfo.ResultBean.ListBean>>() {
                        }.getType();
                        Gson gson = new Gson();
                        a = gson.fromJson(response.toString(), PlayInfo.class);
                        List<PlayInfo.ResultBean.ListBean> list = a.getResult().getList();
                        favoriteAdapter = new FavoriteAdapter(getContext(), 0, list);
                        mRecyclerView.setAdapter(favoriteAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        favoriteAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String path = Contants.API.PULLURL+ a.getResult().getList().get(position).getIdX();
                                netDb = new NetDbAdapter(getActivity());
                                netDb.open();
                                if (netDb.getData(path)) {
                                    netDb.updateData(path);
                                } else {
                                    netDb.createDate(path);
                                }
                                netDb.close();
                                String playerType = "live";

                                //判断是直播还是点播
                                if (playerType.equals(Settings.VOD)) {

                                    //直播
                                    Intent intent = new Intent(getActivity(), TextureVodActivity.class);
                                    intent.putExtra("path", path);
                                    startActivity(intent);
                                } else {
                                    //点播
                                    Intent intent = new Intent(getActivity(), TextureVideoActivity.class);
                                    intent.putExtra("path", path);
                                    startActivity(intent);

                                }

                                //startActivity(new Intent(getActivity(), LivingActivity.class));
                            }
                        });

                    }
                });
    }

    private void refreshDate() {
        Log.e("TAG", "加载了么？");
        page = 1;
        getData(page);
    }

    private void loadMoreData() {
        page += 1;
        getData(page);
    }
}