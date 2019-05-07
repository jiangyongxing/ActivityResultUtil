package cn.fengrong.lib_activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import cn.fengrong.lib_activityresult.manager.ActivityResultFragmentManager;
import cn.fengrong.lib_activityresult.manager.ActivityStartRequest;

/**
 *
 * @author jiangyongxing
 * @date 2019/4/12
 * 描述：
 */
public final class ActivityResult {

    private ActivityResult(){
    }

    public static ActivityResultFragmentManager with(Fragment fragment){
        return ActivityStartRequest.getInstance().get(fragment);
    }

    public static ActivityResultFragmentManager with(android.support.v4.app.Fragment fragment){
        return ActivityStartRequest.getInstance().get(fragment);
    }


    public static ActivityResultFragmentManager with(Activity activity){
        return ActivityStartRequest.getInstance().get(activity);
    }

    public static ActivityResultFragmentManager with(AppCompatActivity activity){
        return ActivityStartRequest.getInstance().get(activity);
    }


}

