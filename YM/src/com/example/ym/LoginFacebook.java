package com.example.ym;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ym.until.Until;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Util;
import com.facebook.widget.LoginButton;

public class LoginFacebook extends YMBaseActivity {

	protected final String TAG = LoginFacebook.class.getSimpleName();

	private LoginButton loginFacebookButton;

	/******************************************************************
	 * Method override
	 */
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		findViews();
		setReadPermissionsForLoginFacebookButton();	
	
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

	/****
	 * End Method override
	 **********************************************************************/
	
	/************************************************************
	 * User Control - UI
	 */
	private void setReadPermissionsForLoginFacebookButton() {
		loginFacebookButton.setReadPermissions(FACEBOOK_PERMISSIONS);
	}
	
	private void findViews() {
		loginFacebookButton = (LoginButton) findViewById(R.id.btn_splash_fb);

	}
	
	@Override
	public void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		super.onSessionStateChange(session, state, exception);
		if (session.isOpened())
		{
			LoginSuccess();
		}
	}
	
	
	private void LoginSuccess()
	{
		Intent i = new Intent(this,MainHome.class);
		startActivity(i);
		finish();
	}
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
