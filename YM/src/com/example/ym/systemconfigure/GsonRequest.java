/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ym.systemconfigure;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.de.cocomero.activities.SNAPBaseActivity;
import com.de.cocomero.common.SNAPApplication;
import com.de.cocomero.entities.leafs.AResponse;
import com.de.cocomero.utils.NetworkUtil;
import com.de.cocomero.utils.SharedPreferencesHelper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {

	public static final String STATUS_CODE_TAG = "StatusCode";

	private final Gson mGson;
	private final Class<T> mClazz;
	private final Listener<T> mListener;
	private final ErrorListener mErrorListener;
	private Map<String, String> params;
	private String mBody;

	public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mClazz = clazz;
		this.mListener = listener;
		this.mErrorListener = errorListener;
		mGson = new Gson();
		mBody = new JSONObject().toString();
		params = new HashMap<String, String>();
		this.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

	public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> params, Listener<T> listener,
			ErrorListener errorListener, String body) {
		this(method, url, clazz, listener, errorListener);
		this.params = params;
		this.mBody = body;
	}

	public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener, String body) {
		this(method, url, clazz, listener, errorListener);
		this.mBody = body;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params;
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		return mBody.getBytes();
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<String, String>();
		// headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Accept", "application/vnd.defide.snap.v1 + json");

		SharedPreferences sharedPreferences = SNAPApplication.getInstance().getSharedPreferences(
				SharedPreferencesHelper.SNAP_PREF_FILE, Context.MODE_PRIVATE);
		String accessToken = sharedPreferences.getString(SharedPreferencesHelper.ACCESS_TOKEN, null);
		if (accessToken != null) {
			headers.put("Authorization", "Bearer " + accessToken);
		}
		return headers;
	}

	@Override
	protected void deliverResponse(T response) {
		if (mListener != null) {
			mListener.onResponse(response);
		}
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		Log.e("", "");
		try {
			String json = new String(response.data, "UTF-8");
			if (json.contains("errorCode")) {
				return Response.error(new VolleyError(response));
			}
			
			T t = mGson.fromJson(json, mClazz);
			String sNext = response.headers.get("Next");
			if(sNext != null && sNext.trim().length() > 0){
				int next = Integer.parseInt(sNext);
				if(next > 0){
					if(t instanceof AResponse){
						((AResponse)t).setNext(next);
					}
				}
			}
			return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	public static class GsonVolleyError extends VolleyError {
		private static final long serialVersionUID = -2796744972636583137L;
		public String data;
		public int statusCode;

		public GsonVolleyError(String data, int statusCode) {
			this.data = data;
			this.statusCode = statusCode;
		}
	}

	@Override
	protected VolleyError parseNetworkError(VolleyError volleyError) {
		GsonVolleyError error = new GsonVolleyError("", 500);
		if (volleyError != null && volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
			error = new GsonVolleyError(new String(volleyError.networkResponse.data), volleyError.networkResponse.statusCode);
		}
		return error;
	}

	/**
	 * set params for request
	 * 
	 * @param params
	 */
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	/**
	 * set params for request
	 * 
	 * @param params
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	/**
	 * Set JSONObject for body
	 * 
	 * @param body
	 */
	public void setJsonBody(JSONObject body) {
		this.mBody = body.toString();
	}

	public void setBody(String mBody) {
		this.mBody = mBody;
	}

	public void performRequest() {
		final SNAPApplication context = SNAPApplication.getInstance();
		final RequestQueue queue = context.getRequestQueue();
		if (NetworkUtil.isConnected(context)) {
			queue.add(this);
		} else {
			handleError(new GsonVolleyError("", 9999));
			if (SNAPBaseActivity.sInstance != null) {
				SNAPBaseActivity.sInstance.showNonNetwordErrorDialog(new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SNAPBaseActivity.isShowing = false;
					}
				}, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SNAPBaseActivity.isShowing = false;
						performRequest();
					}
				});
			}
		}
	}

	private void handleError(VolleyError error) {
		if (mErrorListener != null) {
			this.mErrorListener.onErrorResponse(error);
		}
	}
}