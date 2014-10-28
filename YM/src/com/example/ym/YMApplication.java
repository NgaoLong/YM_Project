package com.example.ym;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class YMApplication extends Application {
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	
	public void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.rounded_image_bg)
//				.showImageOnFail(R.drawable.noimg).bitmapConfig(Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(200)).cacheInMemory(true)
				.cacheOnDisk(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(options)
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize((int) (2f * 1024 * 1024)).threadPoolSize(3)
				.threadPriority(Thread.MIN_PRIORITY + 2)
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(500)
				.build();
		ImageLoader.getInstance().init(config);
	}
}
