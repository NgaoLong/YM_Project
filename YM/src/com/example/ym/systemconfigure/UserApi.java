package com.example.ym.systemconfigure;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.de.cocomero.common.SNAPServerConfig;
import com.de.cocomero.entities.InitializedUserResponse;
import com.de.cocomero.entities.SimpleResponse;
import com.de.cocomero.entities.UserAppeal;
import com.de.cocomero.entities.UserResponse;
import com.de.cocomero.entities.UserWishes;

/**
 * Class that handles requests. Listener and error listener interface should be passed to the
 * request method.
 *
 */
public class UserApi {

	/** Volley Request queue **/
	private static UserApi snapApiRequestInstance;

	public static UserApi getInstance() {
		if (snapApiRequestInstance == null) {
			snapApiRequestInstance = new UserApi();
		}
		return snapApiRequestInstance;
	}

	/*
	 * Get User API
	 */
	public void getUser(Listener<UserResponse> mListener, ErrorListener mErrorListener) {
		GsonRequest<UserResponse> getUser = new GsonRequest<UserResponse>(Method.GET, SNAPServerConfig.SNAP_USER,
				UserResponse.class, mListener, mErrorListener);
		getUser.performRequest();
	}

	public void getInitializedUser(Listener<InitializedUserResponse> listener, ErrorListener errorListener) {
		GsonRequest<InitializedUserResponse> getInitializedUser = new GsonRequest<InitializedUserResponse>(Method.GET,
				SNAPServerConfig.SNAP_INITIALIZED_USER, InitializedUserResponse.class, listener, errorListener);
		getInitializedUser.performRequest();
	}

	/*
	 * Get User Wishes API
	 */
	public void getUserWishes(String userId, Listener<UserWishes> mListener, ErrorListener mErrorListener) {
		String url = String.format(SNAPServerConfig.SNAP_WISHES_USER, userId);
		GsonRequest<UserWishes> userWishesRequest = new GsonRequest<UserWishes>(Method.GET, url, UserWishes.class, mListener,
				mErrorListener);
		userWishesRequest.performRequest();
	}

	/*
	 * Get User appeal API
	 */
	public void getUserAppeal(int userId, Listener<UserAppeal> mListener, ErrorListener mErrorListener) {
		String url = String.format(SNAPServerConfig.SNAP_APPEAL_USER, userId);
		GsonRequest<UserAppeal> userAppealRequest = new GsonRequest<UserAppeal>(Method.GET, url, UserAppeal.class, mListener,
				mErrorListener);
		userAppealRequest.performRequest();
	}

	/*
	 * Post Report User
	 */
	public void postReportUser(int userId, String sComment, Listener<SimpleResponse> mListener, ErrorListener mErrorListener) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("user_id", userId);
			jsonObject.put("comment", sComment);
		} catch (JSONException e) {
		}
		GsonRequest<SimpleResponse> reportUserRequest = new GsonRequest<SimpleResponse>(Method.POST,
				SNAPServerConfig.SNAP_REPORT_USER, SimpleResponse.class, mListener, mErrorListener);
		reportUserRequest.setJsonBody(jsonObject);

		reportUserRequest.performRequest();
	}

	/************************************************************************************
	 * POST /api/user/
	 */

	public void putUser(Listener<SimpleResponse> mListener, ErrorListener mErrorListener, final JSONObject body) {

		GsonRequest<SimpleResponse> simpleResponse = new GsonRequest<SimpleResponse>(Method.PUT, SNAPServerConfig.SNAP_USER,
				SimpleResponse.class, mListener, mErrorListener);
		simpleResponse.setJsonBody(body);

		simpleResponse.performRequest();
	}

	public static JSONObject getUserJsonBody(String name, String location, Boolean is_push_matching, Boolean is_push_approach,
			Boolean is_push_from_admin, Boolean is_push_info, Boolean is_initialized) {
		JSONObject body = new JSONObject();
		try {
			if (name != null) {
				body.put("name", name);
			}
			if (location != null) {
				body.put("location", location);
			}
			if (is_push_matching != null) {
				body.put("is_push_matching", is_push_matching);
			}
			if (is_push_approach != null) {
				body.put("is_push_approach", is_push_approach);
			}
			if (is_push_from_admin != null) {
				body.put("is_push_from_admin", is_push_from_admin);
			}
			if (is_push_info != null) {
				body.put("is_push_info", is_push_info);
			}
			if (is_initialized != null) {
				body.put("is_initialized", is_initialized);
			}
		} catch (JSONException e) {

		}
		return body;
	}

	public void postUser(Listener<SimpleResponse> mListener, ErrorListener mErrorListener, final String body) {
		GsonRequest<SimpleResponse> simpleResponse = new GsonRequest<SimpleResponse>(Method.PUT, SNAPServerConfig.SNAP_USER,
				SimpleResponse.class, mListener, mErrorListener, body);

		simpleResponse.performRequest();
	}
	/**
	 * End POST /api/user/
	 ***************************************************************************************/
}