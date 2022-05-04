package com.yedam.movie.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yedam.app.common.PropertiesPair;
import com.yedam.movie.vo.BoxOfficeResult;
import com.yedam.movie.vo.MovieInfo;

public class MovieService {
	private static final String key = ""; //키 값 삽입
	// 일별 박스 오피스를 반환받고 싶기 때문에
	// GET 방식 => url 뒤에 정보가 붙어서 감 => 정보와 URL을 구분하는 방법 : '?'가 붙어있음
	public static List<MovieInfo> getDailyBoxOfficeResult() {
		//계속 사용하는 값이기 때문에 필드 값으로 지정하기
		
		String serviceURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?";
		
		List<PropertiesPair> params = new ArrayList<PropertiesPair>();
		//필수로 채워야하는 인터페이스
		params.add(new PropertiesPair("key",key));
		params.add(new PropertiesPair("targetDt", "20220101"));
		StringBuilder sb = null;
		
		try { //외부와 통신하는거라 예외처리 필요
			String paramURL = PropertiesPair.getQuery(params);
			String requestURL = serviceURL + paramURL;
			
			sb = new StringBuilder();
			
			URL url = new URL(requestURL); //String인 주소를 URL로 변환
			//연결을 관리하는 클래스 필요, 강제 형변환 필요
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//여기서부터 GET, POST 방법이 달라짐
			//이걸 실행하는 순간 서버에 직접 요청 보냄
			//서버가 반응했을 때 상태에 대해서 코드로 받음
			//정상 응답 : HttpURLConnection.HTTP_OK
			if(con.getResponseCode()== HttpURLConnection.HTTP_OK) {//이 경우에만 데이터를 가공해야됨
				//인풋스트림을 열어서 문자로 받을거니까 인풋스트림 리더를 붙이고 속도 향상을 위해서 bufferedReader를 붙여서 데이터를 받을거임
				//문자형을 바꿀 때 기준을 알려줄 수 있음
				//데이터 오는 과정
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
				//BufferedReader가 지원하는 readline이용 => 한 줄 한 줄 읽는데 그게 필요한게 아니라
				String line;
				while((line = br.readLine())!=null) { //line = br.readLine()이게 null값이면 읽어올 데이터가 더 없다는 것
					//br.readLine()이 내부 내용 한줄만 읽어오는거
					//스트링빌더 선언
					sb.append(line);
				} //통신을 다 썼으면 종료할 것
				br.close();
			}else {
				System.out.println(con.getResponseMessage()); //오류 메세지 출력
			}
			con.disconnect();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String jsonResult = sb.toString();
		BoxOfficeResult result = new Gson().fromJson(jsonResult, BoxOfficeResult.class); //1번째 변수인 json을 넘기고 BoxOfficeResult 클래스로 변환하겠다
		
		return result.getBoxOfficeResult().getDailyBoxOfficeList();
		
		
		

	}
}
