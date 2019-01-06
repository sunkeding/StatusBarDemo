package com.keding.statusbardemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //会让应用的主体内容占用系统状态栏的空间
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //将状态栏设置成透明色
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        Log.d("MainActivity", "statusBarHeight:" + statusBarHeight);
        View view_statusbar_space=findViewById(R.id.view_statusbar_space);
        ViewGroup.LayoutParams layoutParams = view_statusbar_space.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
            layoutParams.height=statusBarHeight;
            view_statusbar_space.setLayoutParams(layoutParams);
        }else {
            layoutParams.width=0;
            layoutParams.height=0;
            view_statusbar_space.setLayoutParams(layoutParams);
        }

    }
}
