package co.micol.prj.listTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
	public void setTest() {
		//집합 생성
		Set<String> name = new HashSet<String>();
		name.add("홍길동");
		name.add("박승리");
		name.add("이기자");
		name.remove("이기자"); //삭제하는거
		
		//집합을 줄세움 : 넣은 순서대로 나오지 않음
		//인덱스를 관리하지 않는다
		Iterator<String> iterator = name.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next()); //집합 안에 있는 걸 출력
		}
	}
	

}
