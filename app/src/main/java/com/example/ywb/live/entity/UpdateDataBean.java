package com.example.ywb.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class UpdateDataBean {

    /**
     * result : [{"created_at":1489557573119,"updated_at":1489716164879,"id":1166284495585332,"user_data":{"user_name":"杨","avatar":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489725190004&di=5f2b261d178057ab58dc192058dd9c0f&imgtype=0&src=http%3A%2F%2Fpic35.nipic.com%2F20131121%2F2531170_145358633000_2.jpg","sign":"啊啊啊啊啊啊啊"}}]
     * error_code : 0
     */

    private int error_code;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * created_at : 1489557573119
         * updated_at : 1489716164879
         * id : 1166284495585332
         * user_data : {"user_name":"杨","avatar":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489725190004&di=5f2b261d178057ab58dc192058dd9c0f&imgtype=0&src=http%3A%2F%2Fpic35.nipic.com%2F20131121%2F2531170_145358633000_2.jpg","sign":"啊啊啊啊啊啊啊"}
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
             * user_name : 杨
             * avatar : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489725190004&di=5f2b261d178057ab58dc192058dd9c0f&imgtype=0&src=http%3A%2F%2Fpic35.nipic.com%2F20131121%2F2531170_145358633000_2.jpg
             * sign : 啊啊啊啊啊啊啊
             */

            private String user_name;
            private String avatar;
            private String sign;

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
