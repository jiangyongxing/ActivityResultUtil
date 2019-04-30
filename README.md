本 `module` 的宗旨：

> 帮助Android开发者解决需要重写Activity中的onActivityResult回调方法的痛点

使用方式：

```java
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
```

