package com.yedam.movie.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MovieInfo { //클래스 이름은 상관없지만 구성 필드는 JSON을 통해 반환받는 키 값을 사용해야함
	private int rnum;
	private int rank;
	private int rankInten;
	private String rankOldAndNew;
	private String movieCd;
	private String movieNm;
	private Date openDt;
	private long salesAmt;
	private double salesShare;
	private long salesInten;
	private double salesChange;
	private long salesAcc;
	private long audiCnt;
	private long audiInten;
	private double audiChange;
	private long audiAcc;
	private long scrnCnt;
	private long showCnt;

}
