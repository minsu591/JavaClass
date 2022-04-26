package co.micol.prj.dto;

import java.sql.Date;

public class StudentVO { //학생 하나를 저장할 수 있는 클래스
	private String studentId; //학번
	private String name;
	private int age;
	private String gender;
	private Date birthDay; //생년월일
	
	public StudentVO() {
		
	}
	
	public StudentVO(String name) { //생성자 오버로딩
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	

	public String toString() { //toString() 메소드를 오버라이드함
		System.out.print(this.studentId+" : ");
		System.out.print(this.name+" : ");
		System.out.print(this.age+" : ");
		System.out.print(this.birthDay+" : ");
		System.out.println(this.gender);
		return null;
	}
	
	
}
