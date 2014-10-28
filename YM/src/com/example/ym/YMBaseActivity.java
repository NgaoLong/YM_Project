package com.example.ym;

import java.util.Arrays;
import java.util.List;

import com.example.ym.systemconfigure.FacebookPermissions;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class YMBaseActivity extends FragmentActivity {
	
	public List<String> FACEBOOK_PERMISSIONS = Arrays.asList(
			FacebookPermissions.USER_ABOUT_ME,
			FacebookPermissions.USER_BIRTHDAY,
			FacebookPermissions.USER_EDUCATION_HISTORY,
			FacebookPermissions.EMAIL, FacebookPermissions.USER_HOMETOWN,
			FacebookPermissions.USER_RELATIONSHIP_DETAILS,
			FacebookPermissions.USER_LOCATION,
			FacebookPermissions.USER_RELIGION_POLITICS,
			FacebookPermissions.USER_RELATIONSHIPS,
			FacebookPermissions.USER_WEBSITE,
			FacebookPermissions.USER_ACTIVITIES,
			FacebookPermissions.USER_LIKES, FacebookPermissions.USER_EVENTS,
			FacebookPermissions.USER_INTERESTS,
			FacebookPermissions.READ_STREAM, FacebookPermissions.USER_PHOTOS,
			"user_friends");
	public UiLifecycleHelper uiHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (uiHelper != null) {
			uiHelper.onSaveInstanceState(outState);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (uiHelper != null) {
			uiHelper.onResume();
		}
	}

	@Override
	protected void onPause() {
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
//		if (Session.getActiveSession() != null) {
//			Session.getActiveSession().closeAndClearTokenInformation();
//		}

	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);

		}
	};

	public void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (session.isOpened()) {

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (uiHelper != null) {
			uiHelper.onActivityResult(requestCode, resultCode, data);
		}
	}
}
