package co.micol.prj;

import co.micol.prj.listTest.ListTest;
import co.micol.prj.listTest.MapTest;
import co.micol.prj.listTest.SetTest;

public class MainApp {

	public static void main(String[] args) {
		//java project 시작 및 종료
		//List
//		ListTest list = new ListTest();
//		list.listTest();
//		System.out.println("===============================");
//		list.studentList();
		
		//HashSet
//		SetTest st = new SetTest();
//		st.setTest();
		
		//Map
		MapTest mt = new MapTest();
		mt.mapTest();
		System.out.println("============================");
		mt.studentMap();
	}
}
