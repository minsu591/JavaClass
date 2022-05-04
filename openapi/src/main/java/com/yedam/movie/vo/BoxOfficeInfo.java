package com.yedam.movie.vo;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeInfo {
	private String boxofficeType;
	private String showRange;
	private List<MovieInfo> dailyBoxOfficeList;

}
