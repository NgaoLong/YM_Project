package com.example.ym.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ym.R;

public class SearchFragment extends YMBaseFragment{
	private final long userSearchDefaultID = 0;
	
	/******************************************************************
	 * Method override
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = (View) inflater.inflate(R.layout.search_fragment, container,
				false);
		return view;
	}
	
	/****
	 * End Method override
	 **********************************************************************/
	
	/************************************************************
	 * User Control - UI
	 */
//	private void search 
	
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
