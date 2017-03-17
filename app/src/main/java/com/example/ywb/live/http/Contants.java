package com.example.ywb.live.http;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class Contants {
//    public static final String COMPAIGN_ID="campaign_id";
//    public static final String WARE="ware";
//    public static final String USER_JSON="user_json";
//    public static final String TOKEN="token";
//    public static final int REQUEST_CODE=0;

    public static class API {
       public static final String BASE_URL="http://121.42.26.175:14444";
        public static final String FAVORITEINFO=BASE_URL+"/live/find.json";//首页接口
        public static final String HOT=BASE_URL+"/live/find.json";//首页接口
        public static final String REGISTER=BASE_URL+"/live/register.json";//注册接口
        public static final String LOGIN=BASE_URL+"/live/login.json";//登录接口
        public static final String CREATELIVE=BASE_URL+"/live/create.json";//创建直播接口
        public static final String UPDATESTATE=BASE_URL+"/live/status/update.json";//更改直播状态
        public static final String PUSHURL="rtmp://cncpublish.bingdou.tv/live/";//推流地址
        public static final String PULLURL="rtmp://cncplay.bingdou.tv/live/";//拉流地址
        public static final String PRESONDATA=BASE_URL+"/live/my_info.json";//获取个人资料
        public static final String UPDATEPRESONDATA=BASE_URL+"/live/user/update.json";//更改个人资料

    }
}
