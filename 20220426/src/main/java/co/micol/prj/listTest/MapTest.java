package co.micol.prj.listTest;

import java.util.HashMap;
import java.util.Map;

import co.micol.prj.dto.StudentVO;

public class MapTest {
	//key value 구조로
	//단위 하나 하나를 쓸 때 주로 사용
	public void mapTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","홍길동");
		map.put("address","대구 광역시");
		System.out.println(map.get("name")); 
		System.out.println(map.get("address"));
	}
	
	public void studentMap() {
		Map<Integer,StudentVO> map = new HashMap<Integer,StudentVO>();

		map.put(1, new StudentVO("홍길동"));
		map.put(2, new StudentVO("홍길동"));
		System.out.println(map.get(1));
		
		
	}
}
