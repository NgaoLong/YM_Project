package com.example.ym;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainHome extends YMBaseActivity {
	protected final String TAG = MainHome.class.getSimpleName();

	/******************************************************************
	 * Method override
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e("AAA",Session.getActiveSession().getAccessToken());
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	
	}
	
	
	
	/****
	 * End Method override
	 **********************************************************************/

	/************************************************************
	 * User Control - UI
	 */

	/**
	 * End User Control - UI
	 *****************************************************************************/

	/************************************************************
	 * Implement
	 */

	/**
	 * End Implement
	 *****************************************************************************/

}
