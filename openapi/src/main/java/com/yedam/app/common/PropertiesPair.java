package com.yedam.app.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor //모든 필드를 이용해서 생성자를 추가하는 어노테이션
@Data
public class PropertiesPair {//PropertiesPair : key와 value값 가진 객체, getQuery : PropertiesPair를 리스트로 받아서 
	private String key;
	private String value;
	
	public static String getQuery(List<PropertiesPair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true; //첫 쌍인지?
		
		for(PropertiesPair param : params) {
			if(isFirst) { //첫 번째 쌍이면 앞에 &를 붙이지 않고
				isFirst = false;
			}else {
				sb.append("&");
			}
			//문자 Set을 자바가 가지고 있지 않을 경우 => throws UnsupportedEncodingException
			sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			sb.append("=");
			sb.append(URLEncoder.encode(param.getValue(),"UTF-8"));
		}
		//"key" = key & "key2" = key2 & ...
		return sb.toString();
		
	}

}
