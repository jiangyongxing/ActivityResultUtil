package cn.fengrong.activityresultutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.fengrong.lib_activityresult.ActivityResult;
import cn.fengrong.lib_activityresult.bean.Result;
import cn.fengrong.lib_activityresult.listener.ActivityResultListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResult.with(this)
                .setIntent(new Intent(this, Main2Activity.class))
                .setRequestCode(10)
                .setListener(new ActivityResultListener() {
                    @Override
                    public void onSuccess(Result result) {
                    }

                    @Override
                    public void onFailed(Result result) {
                    }
                }).startIntent();
    }
}
