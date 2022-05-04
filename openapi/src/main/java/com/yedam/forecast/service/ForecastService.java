package com.yedam.forecast.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.yedam.app.common.PropertiesPair;
import com.yedam.forecast.service.vo.WeatherInfo;

public class ForecastService {
	// 동네예보 - 육상예보조회
	public static List<WeatherInfo> getWeather() {
		String key = "";
		String serviceURL = "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?";
		String line;
		// GET방식이기 때문에 Query를 짜는 것
		List<PropertiesPair> params = new ArrayList<PropertiesPair>();
		params.add(new PropertiesPair("ServiceKey", key));
		params.add(new PropertiesPair("pageNo", "1"));
		params.add(new PropertiesPair("numOfRows", "10"));
		params.add(new PropertiesPair("dataType", "JSON"));
		params.add(new PropertiesPair("regId", "11A00101"));

		// StringBuilder 내부에 메모리가 존재 => replace를 열 번 실천해도 메모리는 하나만 존재, 성능상
		// StringBuilder가 좋음
		// Stream을 통해 데이터를 가져올 때는 StringBuilder가 좋음
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			// 이걸 통해서 getQuery
			String paramURL = PropertiesPair.getQuery(params);
			String requestURL = serviceURL + paramURL;

			URL url = new URL(requestURL); // 양식을 통해서 어디까지가 url이고 정보인지 확인함

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET"); // 메소드 정의
			// 파일 이용할 때 사용하길 추천
			con.setRequestProperty("Content-type", "application/json"); // Content-type : 갖는 쪽이 이 텍스트가 무슨 종류인지 정보를 줌

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// inputStream을 통해 데이터가 흘러 들어옴 => 이 데이터가 문자여야하므로 보조스트림으로 InputStreamReader을 붙인다
				// 성능의 문제 때문에 BufferedReader을 붙인다.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} else {
				System.out.println(con.getResponseCode());
			}
			con.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String jsonResult = sb.toString();
		return getWeatherData(jsonResult);
	
	}
	
	private static List<WeatherInfo> getWeatherData(String jsonData){ //정적 메소드로 사용하기 때문에 static으로 선언
		List<WeatherInfo> list = new ArrayList<WeatherInfo>();
		try {
			JSONParser parser = new JSONParser();
			//parser가 반환하는거 => Object
			//하나씩 꺼낸다고 생각하면됨
			JSONObject forecastData = (JSONObject) parser.parse(jsonData);
			JSONObject response = (JSONObject) forecastData.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray)items.get("item");
			
			//하나씩 꺼내서 리스트가 나온 경우에
			for(int i = 0; i<item.size();i++) {
				JSONObject data = (JSONObject)item.get(i);
				WeatherInfo info = new WeatherInfo();
				info.setAnnounceTime((long) data.get("announceTime")); //필수
				//캐스팅 오류가 있을 경우에
//				info.setAnnounceTime(Long.parseLong(data.get("announceTime").toString()));
				info.setNumEf((data.get("numEf")==null)? 0 : (long)data.get("numEf")); //옵션
				info.setRegId((String) data.get("regId")); //필수
				info.setRnSt((data.get("rnSt")==null)? 0 : (long) data.get("rnSt")); //옵션
				info.setRnYn((long)data.get("rnYn")); //필수
				info.setTa((data.get("ta")==null) ? null : (String)data.get("ta"));
				//옵션
				info.setWf((String)data.get("wf")); //필수
				info.setWfCd((String)data.get("wfCd")); //필수
				info.setWsIt((String)data.get("wsIt")); //필수
				
				list.add(info);
			}
			
			
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

}
