package com.example.ym;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ym.systemconfigure.FacebookPermissions;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginFacebook extends YMBaseActivity {

	protected static final String TAG = LoginFacebook.class.getSimpleName();
	private List<String> FACEBOOK_PERMISSIONS = Arrays.asList(FacebookPermissions.USER_ABOUT_ME,
			FacebookPermissions.USER_BIRTHDAY, FacebookPermissions.USER_EDUCATION_HISTORY, FacebookPermissions.EMAIL,
			FacebookPermissions.USER_HOMETOWN, FacebookPermissions.USER_RELATIONSHIP_DETAILS, FacebookPermissions.USER_LOCATION,
			FacebookPermissions.USER_RELIGION_POLITICS, FacebookPermissions.USER_RELATIONSHIPS, FacebookPermissions.USER_WEBSITE,
			FacebookPermissions.USER_ACTIVITIES, FacebookPermissions.USER_LIKES, FacebookPermissions.USER_EVENTS,
			FacebookPermissions.USER_INTERESTS, FacebookPermissions.READ_STREAM, FacebookPermissions.USER_PHOTOS);
	private UiLifecycleHelper uiHelper;
	private LoginButton loginFacebookButton;
	private Session session;
	/******************************************************************
	 * Method override
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		findViews();
		setReadPermissionsForLoginFacebookButton();
		
		uiHelper = new UiLifecycleHelper(LoginFacebook.this, callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (uiHelper != null) {
			uiHelper.onResume();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		if (uiHelper != null) {
			uiHelper.onSaveInstanceState(bundle);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (uiHelper != null) {
			uiHelper.onPause();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (uiHelper != null) {
			uiHelper.onDestroy();
		}
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().closeAndClearTokenInformation();
		}

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (uiHelper != null) {
			uiHelper.onActivityResult(requestCode, resultCode, data);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);

		}
	};

	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (session.isOpened()) {
			this.session = session;
			makeMeRequest(session);
		}
	}
	
	private void makeMeRequest(final Session session) {
		// Make an API call to get user data and define a
		// new callback to handle the response.
//		showLoadingImage();
		Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				// TODO Auto-generated method stub
				if (session == Session.getActiveSession()) {
					//login thanh cong
					Log.d(TAG, "Login success");
				}
			}
		});
		request.executeAsync();
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
