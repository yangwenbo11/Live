package com.example.ywb.live.entity;

/**
 * Created by Administrator on 2017/3/15.
 */

public class RegisterBean {

    /**
     * result : {"created_at":1489557573119,"updated_at":1489557573138,"id":1166284495585332,"user_data":{"phone":"18000000000","user_name":"0","avatar":"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=android%20%E5%A4%B4%E5%83%8F&step_word=&hs=2&pn=11&spn=0&di=90834035820&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2682821339%2C2014300825&os=1374161100%2C2911390326&simid=3459425215%2C341980104&adpicid=0&lpn=0&ln=1982&fr=&fmq=1489548447186_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg23.photophoto.cn%2F20120423%2F0020033094229209_s.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bri5p5ri5p5_z%26e3BvgAzdH3Ffi5oAzdH3Fa0db80b9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0","sign":"0"}}
     * error_code : 0
     */

    private ResultBean result;
    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * created_at : 1489557573119
         * updated_at : 1489557573138
         * id : 1166284495585332
         * user_data : {"phone":"18000000000","user_name":"0","avatar":"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=android%20%E5%A4%B4%E5%83%8F&step_word=&hs=2&pn=11&spn=0&di=90834035820&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2682821339%2C2014300825&os=1374161100%2C2911390326&simid=3459425215%2C341980104&adpicid=0&lpn=0&ln=1982&fr=&fmq=1489548447186_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg23.photophoto.cn%2F20120423%2F0020033094229209_s.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bri5p5ri5p5_z%26e3BvgAzdH3Ffi5oAzdH3Fa0db80b9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0","sign":"0"}
         */

        private long created_at;
        private long updated_at;
        private long id;
        private UserDataBean user_data;

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public static class UserDataBean {
            /**
             * phone : 18000000000
             * user_name : 0
             * avatar : http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=android%20%E5%A4%B4%E5%83%8F&step_word=&hs=2&pn=11&spn=0&di=90834035820&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2682821339%2C2014300825&os=1374161100%2C2911390326&simid=3459425215%2C341980104&adpicid=0&lpn=0&ln=1982&fr=&fmq=1489548447186_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg23.photophoto.cn%2F20120423%2F0020033094229209_s.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bri5p5ri5p5_z%26e3BvgAzdH3Ffi5oAzdH3Fa0db80b9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0
             * sign : 0
             */

            private String phone;
            private String user_name;
            private String avatar;
            private String sign;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
