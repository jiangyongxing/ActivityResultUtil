package cn.fengrong.lib_activityresult.manager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import cn.fengrong.lib_activityresult.bean.Result;
import cn.fengrong.lib_activityresult.listener.ActivityResultListener;
import cn.fengrong.lib_activityresult.listener.InnerActivityResultListener;
import cn.fengrong.lib_activityresult.ui.ActivityResultFragment;
import cn.fengrong.lib_activityresult.ui.SupportActivityResultFragment;

/**
 * Created by jiangyongxing on 2019/4/30.
 * 描述：
 */
public class ActivityResultFragmentManager {

    /**
     * 默认的RequestCode
     */
    public static final int DEFAULT_REQUEST_CODE = 9999;
    private SupportActivityResultFragment mSupportActivityResultFragment;
    private ActivityResultFragment mActivityResultFragment;

    private Intent mIntent;
    private int mRequestCode = DEFAULT_REQUEST_CODE;
    private ActivityResultListener mActivityResultListener;
    private FragmentManager mSupportFragmentManager;
    private android.app.FragmentManager mFragmentManager;

    public ActivityResultFragmentManager() {
    }

    public ActivityResultFragmentManager(FragmentManager fragmentManager, SupportActivityResultFragment supportActivityResultFragment) {
        this.mSupportFragmentManager = fragmentManager;
        this.mSupportActivityResultFragment = supportActivityResultFragment;
    }

    public ActivityResultFragmentManager(android.app.FragmentManager fragmentManager, ActivityResultFragment activityResultFragment) {
        this.mFragmentManager = fragmentManager;
        this.mActivityResultFragment = activityResultFragment;
    }

    public ActivityResultFragmentManager setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public ActivityResultFragmentManager setRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    public ActivityResultFragmentManager setListener(ActivityResultListener listener) {
        this.mActivityResultListener = listener;
        return this;
    }

    public void startIntent() {
        if (mSupportFragmentManager != null) {
            SupportActivityResultFragment resultFragment = mSupportActivityResultFragment;
            if (resultFragment != null) {
                resultFragment.setStartIntent(mIntent);
                resultFragment.setRequestCode(mRequestCode);
                resultFragment.setInnerActivityResultListener(listener);
                mSupportFragmentManager.beginTransaction().add(resultFragment, ActivityStartRequest.TAG).commitAllowingStateLoss();
            }
        }

        if (mFragmentManager != null) {
            ActivityResultFragment resultFragment = mActivityResultFragment;
            if (resultFragment != null) {
                resultFragment.setStartIntent(mIntent);
                resultFragment.setRequestCode(mRequestCode);
                resultFragment.setInnerActivityResultListener(listener);
                mFragmentManager.beginTransaction().add(resultFragment, ActivityStartRequest.TAG).commitAllowingStateLoss();
            }
        }
    }

    /**
     * the listener we will give to the fragment
     */
    private final InnerActivityResultListener listener = new InnerActivityResultListener() {
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            onReceivedActivityResult(data, requestCode, resultCode);
        }
    };

    private void onReceivedActivityResult(Intent data, int requestCode, int resultCode) {
        Result result = new Result(data, requestCode, resultCode);
        if (mActivityResultListener == null) {
            return;
        }
        if (resultCode == Activity.RESULT_OK && requestCode == mRequestCode) {
            mActivityResultListener.onSuccess(result);
        }
        if (resultCode == Activity.RESULT_CANCELED && requestCode == mRequestCode) {
            mActivityResultListener.onFailed(result);
        }
    }
}
