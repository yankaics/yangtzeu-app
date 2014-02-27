package com.rex.yuol.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class JwcRegex {
	/**
	 * 获取登录页上的VIEWSTATE吗，供登录使用
	 * 
	 * @param content
	 *            原网页
	 * @return map<s,s>结构的参数表
	 * @throws Exception 正则匹配失败
	 */
	public static Map<String, String> get_keys(String content) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String key1 = "", key2 = "";
		String regEx1 = "__VIEWSTATE\" value=\"([/d/D][^\"]+)\"";
		String regEx2 = "__EVENTVALIDATION\" value=\"([/d/D][^\"]+)\"";

		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(content);
		boolean rs1 = mat1.find();
		// 如果没有匹配到
		if (!rs1) {
			throw new Exception("正则匹配失败!没找到__VIEWSTATE,__EVENTVALIDATION...");
		}
		key1 = mat1.group(1);
		map.put("viewstate", key1);

		Pattern pat2 = Pattern.compile(regEx2);
		Matcher mat2 = pat2.matcher(content);
		boolean rs2 = mat2.find();
		key2 = mat2.group(1);
		map.put("eventvalidation", key2);

		Log.i("regex", key1 + "==>" + key2);
		return map;
	}
	
	/**
	 * 判断未登录
	 * @param content 内容
	 * @return true未登录
	 * @throws Exception 空内容
	 */
	public static Boolean is_not_login(String content) throws Exception{
		if(content.equals("")){
			throw new Exception("内容为空！");
		}
		String regEx1 = "^<script>alert";
		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(content);
		Boolean result = mat1.find();
		return result;
	}
}
