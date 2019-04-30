package cn.fengrong.lib_activityresult.listener;

import cn.fengrong.lib_activityresult.bean.Result;

/**
 * Created by jiangyongxing on 2019/4/30.
 * 描述：
 */
public interface ActivityResultListener {

    /**
     * 成功的回调
     *
     * @param result
     */
    void onSuccess(Result result);

    /**
     * 失败的回调
     *
     * @param result
     */
    void onFailed(Result result);
}
