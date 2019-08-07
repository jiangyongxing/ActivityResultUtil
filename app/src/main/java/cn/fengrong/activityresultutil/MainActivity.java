package cn.fengrong.activityresultutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.fengrong.lib_activityresult.ActivityResult;
import cn.fengrong.lib_activityresult.bean.Result;
import cn.fengrong.lib_activityresult.listener.ActivityResultListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startNewActivity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);

        startActivityFor(intent);
        startActivityFor(intent);
        startActivityFor(intent);
        startActivityFor(intent);
    }

    private void startActivityFor(Intent intent) {
        ActivityResult.with(this)
                .setIntent(intent)
                .setRequestCode(10)
                .setListener(new ActivityResultListener() {
                    @Override
                    public void onSuccess(Result result) {
                        Log.e("jiang","返回成功");
                    }

                    @Override
                    public void onFailed(Result result) {
                        Log.e("jiang","返回失败");

                    }
                }).startIntent();
    }
}
