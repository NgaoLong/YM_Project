package com.example.ym.until;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageInfoHelper {

	public static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get app version: " + e);
		}
	}

	public static String getAppVersionName(Context context) {
		String appVersionName = "";
		try {
			appVersionName = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA).versionName;
		} catch (NameNotFoundException e) {
			throw new RuntimeException("Could not get app name: " + e);
		}
		return appVersionName;
	}
}
