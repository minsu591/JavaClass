package com.yedam.app;

import java.util.List;

import com.yedam.forecast.service.ForecastService;
import com.yedam.forecast.service.vo.WeatherInfo;

public class App {
	public static void main(String[] args) {
//		List<MovieInfo> result = MovieService.getDailyBoxOfficeResult(); //String으로 출력
//		for(MovieInfo info : result) {
//			System.out.println(info);
//			System.out.println(info.getMovieCd());
//		}
		
		List<WeatherInfo> list = ForecastService.getWeather();
		for(WeatherInfo info : list) {
			System.out.println(info);
		}
	}
}
