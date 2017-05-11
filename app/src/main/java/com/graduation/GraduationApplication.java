package com.graduation;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

//import com.wtf.fragment.WifiFragment;


/**
 * Created by liyan on 2016/10/10.
 */
public class GraduationApplication extends Application{
    private static GraduationApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
