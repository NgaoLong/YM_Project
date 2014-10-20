package com.example.ym.until;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayHelper {

	/**
	 * This method converts dp unit to equivalent pixels, depending on device density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need to convert into
	 *            pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on device density
	 */
	public static int convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int px = (int) (dp * (metrics.densityDpi / 160f));
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static int getScreenHeight(Activity context) {

		DisplayMetrics displaymetrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		return height - getStatusBarHeight(context);
	}

	public static int getScreenWidth(Activity context) {

		DisplayMetrics displaymetrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;

		return width;
	}

	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
