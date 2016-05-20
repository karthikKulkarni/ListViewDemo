package com.android.samplelistview.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by karthik_kulkarni01 on 5/20/2016.
 * This is base Application class of this application
 */
public class SampleListViewApplication extends Application {
    private static SampleListViewApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        //Initialize Image loader for the 1st time
        initImageLoader(getApplicationContext());

    }

    /**
     * Get application context for further use through out the application
     */
    private static void setContext(SampleListViewApplication mContext) {
        SampleListViewApplication.mContext = mContext;
    }

    public static SampleListViewApplication getContext() {
        return mContext;
    }

    /**
     * Universal Image Loader
     * Initialize ImageLoader from universalImageLoader lib with initial configuration.
     * This will be used to load images in the specified ImageView lazily.
     * More details : https://github.com/nostra13/Android-Universal-Image-Loader
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(10 * 1024 * 1024); // 10 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

}
