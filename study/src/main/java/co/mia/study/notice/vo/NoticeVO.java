package co.mia.study.notice.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	private int id;
	private String writer;
	private String title;
	private String subject;
	private Date wdate;
	private int hit;
}
