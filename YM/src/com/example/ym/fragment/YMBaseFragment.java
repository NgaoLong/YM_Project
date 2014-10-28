package com.example.ym.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ym.R;

public class YMBaseFragment extends Fragment {

	protected int fragmentLevel = 1;
	private static int res;
	
	public String getTransactionName() {
		return "Level" + fragmentLevel;
	}

	public void setFragmentLevel(int fragmentLevel) {
		this.fragmentLevel = fragmentLevel;
	}

	public int getFragmentLevel() {
		return fragmentLevel;
	}
	
	public static void setFragment(FragmentActivity context, YMBaseFragment fragment) {
		if (context == null) {
			return;
		}

		FragmentManager fragmentManager = context.getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() != 0) {
			try {
				fragmentManager.popBackStackImmediate(fragment.getTransactionName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} catch (Exception e) {

			}
		}
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if (!"Level1".equals(fragment.getTransactionName())) {
			fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left,
					R.anim.exit_to_right);
		}
		fragmentTransaction.replace(res, fragment);

		fragmentTransaction.addToBackStack(fragment.getTransactionName()).commit();
	}

	public static void setFragment(FragmentActivity context, YMBaseFragment fragment, int view) {
		if (context == null) {
			return;
		}

		FragmentManager fragmentManager = context.getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() != 0) {
			try {
				fragmentManager.popBackStackImmediate(fragment.getTransactionName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} catch (Exception e) {

			}
		}
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if (!"Level1".equals(fragment.getTransactionName())) {
			fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left,
					R.anim.exit_to_right);
		}
		fragmentTransaction.replace(view, fragment);

		fragmentTransaction.addToBackStack(fragment.getTransactionName()).commit();
	}

	public static void setFragmentNoClear(FragmentActivity context, YMBaseFragment fragment) {
		if (context == null) {
			return;
		}
		FragmentManager fragmentManager = context.getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() != 0) {
			try {
				fragmentManager.popBackStackImmediate(fragment.getTransactionName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} catch (Exception e) {

			}
		}
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if (!"Level1".equals(fragment.getTransactionName())) {
			fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left,
					R.anim.exit_to_right);
		}
		fragmentTransaction.add(res, fragment);
		fragmentTransaction.addToBackStack(fragment.getTransactionName()).commit();
	}

	/**
	 * Pop back stack
	 */
	protected void back() {
		if (getActivity() == null)
			return;

		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() != 0) {
			try {
				fragmentManager.popBackStackImmediate(this.getTransactionName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} catch (Exception e) {
			}
		}
	}

}
