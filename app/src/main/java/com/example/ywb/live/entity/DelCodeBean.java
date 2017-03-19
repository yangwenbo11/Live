package com.example.ywb.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */

public class DelCodeBean {

    /**
     * result : data 1167925189869689 deleted
     * removed : [1167925189869689]
     * not_removed : []
     * error_code : 0
     */

    private String result;
    private int error_code;
    private List<Long> removed;
    private List<?> not_removed;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<Long> getRemoved() {
        return removed;
    }

    public void setRemoved(List<Long> removed) {
        this.removed = removed;
    }

    public List<?> getNot_removed() {
        return not_removed;
    }

    public void setNot_removed(List<?> not_removed) {
        this.not_removed = not_removed;
    }
}
