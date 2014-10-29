package com.example.ym;

import com.example.ym.fragment.SearchFragment;

import android.os.Bundle;

public class MainHome extends YMBaseActivity {
	protected final String TAG = MainHome.class.getSimpleName();

	/******************************************************************
	 * Method override
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		Log.e("AAA",Session.getActiveSession().getAccessToken());
	  SearchFragment fragment = new SearchFragment();
	  fragment.setFragment(this, fragment,R.id.contentFragment);
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
