package cn.fengrong.lib_activityresult.listener;

import android.content.Intent;

/**
 * Created by jiangyongxing on 2019/4/30.
 * 描述：
 */
public interface InnerActivityResultListener {

    /**
     * 返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
