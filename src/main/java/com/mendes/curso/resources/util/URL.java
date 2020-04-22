package com.mendes.curso.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String arg) {
		try {
			return URLDecoder.decode(arg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Date convertDate(String arg, Date valorPadrao) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(arg);
		} catch (ParseException e) {
			return valorPadrao;
		}
	}
}
