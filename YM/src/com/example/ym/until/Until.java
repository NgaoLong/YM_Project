package com.example.ym.until;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Until {
	//loai bo dau tieng viet
	public static String stripDiacriticas(String s) {
	    return Normalizer.normalize(s, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public static String stripDiacriticas2(String input) {
	    return Normalizer
	            .normalize(input, Normalizer.Form.NFD)
	            .replaceAll("[^\\p{ASCII}]", "");
	}
}
