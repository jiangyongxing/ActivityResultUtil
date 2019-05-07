package cn.fengrong.lib_activityresult.bean;

import android.content.Intent;

/**
 * Created by jiangyongxing on 2019/4/30.
 * 描述：
 */
public class Result {

    private Intent data;
    private int requestCode;
    private int responseCode;

    public Result(Intent data, int requestCode, int responseCode) {
        this.data = data;
        this.requestCode = requestCode;
        this.responseCode = responseCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
