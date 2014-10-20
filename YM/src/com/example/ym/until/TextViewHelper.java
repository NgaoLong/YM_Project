package com.example.ym.until;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class TextViewHelper {
	public static Spannable setSpanableTextView(String text, int from, int to, int color) {
		Spannable wordtoSpan = new SpannableString(text);
		wordtoSpan.setSpan(new ForegroundColorSpan(color), from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return wordtoSpan;
	}
}
