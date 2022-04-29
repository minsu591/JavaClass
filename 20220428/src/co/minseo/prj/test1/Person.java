package co.minseo.prj.test1;

public class Person {
	//필드
	private String ssn;
	private String name;
	private String address;
	
	//기본 생성자
	public Person() {
		
	}
	//주민등록번호, 이름, 주소를 매개변수로 받는 생성자
	public Person(String ssn, String name, String address) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.address = address;
	}
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
