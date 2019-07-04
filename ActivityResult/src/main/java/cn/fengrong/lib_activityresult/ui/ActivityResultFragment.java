package cn.fengrong.lib_activityresult.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.fengrong.lib_activityresult.listener.InnerActivityResultListener;
import cn.fengrong.lib_activityresult.manager.ActivityResultFragmentManager;

/**
 * Created by jiangyongxing on 2019/4/12.
 * 描述：
 */
public class ActivityResultFragment extends Fragment {

    public static final String INTENT_TO_START = "INTENT_TO_START";
    public static final String INTENT_TO_REQUEST_CODE = "INTENT_TO_REQUEST_CODE";

    public static final int REQUEST_CODE_DEFAULT = 24;

    private ActivityResultFragmentManager mActivityResultFragmentManager;


    @Nullable
    private Intent intentToStart;
    private int mRequestCode = REQUEST_CODE_DEFAULT;
    private InnerActivityResultListener mInnerActivityResultListener;

    public static ActivityResultFragment newInstance(@NonNull final Intent intent, int requestCode) {
        final Bundle args = new Bundle();
        args.putParcelable(INTENT_TO_START, intent);
        args.putInt(INTENT_TO_REQUEST_CODE, requestCode);
        final ActivityResultFragment fragment = new ActivityResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        if (arguments != null) {
            this.intentToStart = arguments.getParcelable(INTENT_TO_START);
            this.mRequestCode = arguments.getInt(INTENT_TO_REQUEST_CODE, REQUEST_CODE_DEFAULT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (intentToStart != null) {
            startActivityForResult(intentToStart, mRequestCode);
        } else {
            // this shouldn't happen, but just to be sure
            getFragmentManager().beginTransaction()
                    .remove(this)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode) {
            if (mInnerActivityResultListener != null) {
                mInnerActivityResultListener.onActivityResult(requestCode, resultCode, data);
                getFragmentManager().beginTransaction()
                        .remove(this)
                        .commitAllowingStateLoss();
            }
        }

    }

    public ActivityResultFragmentManager getActivityResultFragmentManager() {
        return mActivityResultFragmentManager;
    }

    public void setActivityResultFragmentManager(ActivityResultFragmentManager activityResultFragmentManager) {
        mActivityResultFragmentManager = activityResultFragmentManager;
    }

    public void setStartIntent(@Nullable Intent intentToStart) {
        if (intentToStart == null){
            throw new IllegalArgumentException("Intent cannot be empty");
        }
        this.intentToStart = intentToStart;
    }

    public void setRequestCode(int requestCode) {
        mRequestCode = requestCode;
    }

    public void setInnerActivityResultListener(InnerActivityResultListener listener) {
        this.mInnerActivityResultListener = listener;
    }

    @Override
    public void onDestroy() {
        Log.e("jiang", "onDestroy:" + "fragment 销毁了");
        super.onDestroy();
    }
}
