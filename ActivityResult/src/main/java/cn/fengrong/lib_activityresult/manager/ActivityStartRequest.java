package cn.fengrong.lib_activityresult.manager;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import cn.fengrong.lib_activityresult.ui.ActivityResultFragment;
import cn.fengrong.lib_activityresult.ui.SupportActivityResultFragment;

/**
 * @author jiangyongxing
 * @date 2019/4/12
 * 描述：
 */
public class ActivityStartRequest {

    protected static final String TAG = "ACTIVITY_RESULT_FRAGMENT";


    private ActivityStartRequest() {

    }

    private static class Singleton {
        static ActivityStartRequest activityStartRequest = new ActivityStartRequest();
    }

    public static ActivityStartRequest getInstance() {
        return Singleton.activityStartRequest;
    }


    public ActivityResultFragmentManager get(Fragment fragment) {
        checkoutEmpty(fragment);
        Activity activity = fragment.getActivity();
        if (activity instanceof AppCompatActivity) {
            return get((AppCompatActivity) activity);
        }
        return get(activity);
    }

    public ActivityResultFragmentManager get(android.support.v4.app.Fragment fragment) {
        checkoutEmpty(fragment);
        FragmentActivity activity = fragment.getActivity();
        return get(activity);
    }

    public ActivityResultFragmentManager get(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return new ActivityResultFragmentManager();
        }
        return fragmentGet(activity.getFragmentManager());
    }

    private ActivityResultFragmentManager fragmentGet(android.app.FragmentManager fragmentManager) {
        ActivityResultFragment activityResultFragment = getActivityResultFragment(fragmentManager);
        ActivityResultFragmentManager resultFragmentManager = activityResultFragment.getActivityResultFragmentManager();
        if (resultFragmentManager == null) {
            resultFragmentManager = new ActivityResultFragmentManager(fragmentManager,activityResultFragment);
            activityResultFragment.setActivityResultFragmentManager(resultFragmentManager);
        }
        return resultFragmentManager;
    }

    private ActivityResultFragment getActivityResultFragment(android.app.FragmentManager fragmentManager) {
        ActivityResultFragment current = (ActivityResultFragment) fragmentManager.findFragmentByTag(TAG);
        if (current == null) {
            current = new ActivityResultFragment();
        }
        return current;
    }


    public ActivityResultFragmentManager get(AppCompatActivity activity) {
        if (activity == null || activity.isFinishing()) {
            return new ActivityResultFragmentManager();
        }
        return supportFragmentGet(activity.getSupportFragmentManager());
    }

    private ActivityResultFragmentManager supportFragmentGet(FragmentManager fragmentManager) {
        SupportActivityResultFragment activityResultFragment = getSupportActivityResultFragment(fragmentManager);
        ActivityResultFragmentManager resultFragmentManager = activityResultFragment.getActivityResultFragmentManager();
        if (resultFragmentManager == null) {
            resultFragmentManager = new ActivityResultFragmentManager(fragmentManager,activityResultFragment);
            activityResultFragment.setActivityResultFragmentManager(resultFragmentManager);
        }
        return resultFragmentManager;
    }

    private SupportActivityResultFragment getSupportActivityResultFragment(FragmentManager fragmentManager) {
        SupportActivityResultFragment current = (SupportActivityResultFragment) fragmentManager.findFragmentByTag(TAG);
        if (current == null) {
            current = new SupportActivityResultFragment();
        }
        return current;
    }


    private static void checkoutEmpty(Object fragment) {
        if (fragment == null) {
            throw new RuntimeException("Can not be empty");
        }
    }
}
