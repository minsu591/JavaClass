package co.mia.study.student.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentVO {
	private String studentId;
	private String name;
	private Date birthday;
	private String major;
	private String address;
	private String tel;
	private String author;
	private String password;

}
