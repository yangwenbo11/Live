package com.example.ywb.live.entity;

/**
 * Created by Administrator on 2017/3/16.
 */

public class CreateLiveBean {

    /**
     * result : {"created_at":1489654472871,"updated_at":1489654472871,"id":1167910191038576,"data":{"status":1,"live_type":0,"pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489662270808&di=82c4667163a86b31e63a943292a67777&imgtype=0&src=http%3A%2F%2Fpic3.zhongsou.com%2Fimage%2F38063b6d7defc892894.jpg","live_name":"00000"},"uid":1165009125179394}
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
         * created_at : 1489654472871
         * updated_at : 1489654472871
         * id : 1167910191038576
         * data : {"status":1,"live_type":0,"pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489662270808&di=82c4667163a86b31e63a943292a67777&imgtype=0&src=http%3A%2F%2Fpic3.zhongsou.com%2Fimage%2F38063b6d7defc892894.jpg","live_name":"00000"}
         * uid : 1165009125179394
         */

        private long created_at;
        private long updated_at;
        private long id;
        private DataBean data;
        private long uid;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public static class DataBean {
            /**
             * status : 1
             * live_type : 0
             * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489662270808&di=82c4667163a86b31e63a943292a67777&imgtype=0&src=http%3A%2F%2Fpic3.zhongsou.com%2Fimage%2F38063b6d7defc892894.jpg
             * live_name : 00000
             */

            private int status;
            private int live_type;
            private String pic;
            private String live_name;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getLive_type() {
                return live_type;
            }

            public void setLive_type(int live_type) {
                this.live_type = live_type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLive_name() {
                return live_name;
            }

            public void setLive_name(String live_name) {
                this.live_name = live_name;
            }
        }
    }
}
