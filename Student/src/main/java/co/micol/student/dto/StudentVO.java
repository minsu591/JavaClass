package co.micol.student.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentVO { //Dto
	// 컬럼 명과 똑같이 맞추기 java의 이름 구조 지키기
	private String studentId;
	private String name;
	private Date birthday;
	private String major;
	private String address;
	private String tel;

	
	@Override
	public String toString() {
		System.out.print(studentId + " : ");
		System.out.print(name + " : ");
		System.out.print(birthday + " : ");
		System.out.print(major + " : ");
		System.out.print(address + " : ");
		System.out.println(tel + " : ");
		return null;
	}

}
