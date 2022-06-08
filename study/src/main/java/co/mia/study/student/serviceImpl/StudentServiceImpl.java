package co.mia.study.student.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.mia.study.comm.DataSource;
import co.mia.study.student.service.StudentMapper;
import co.mia.study.student.service.StudentService;
import co.mia.study.student.vo.StudentVO;

public class StudentServiceImpl implements StudentService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true); //괄호 안이 비어있으면 auto commit안됨.
	private StudentMapper map = sqlSession.getMapper(StudentMapper.class);
	//Mapper interface를 안 만들고도 쓸 수 있지만 sqlSession.selectList(select * from student)처럼 써야됨
	
	
	@Override
	public List<StudentVO> studentSelectList() {
		return map.studentSelectList();
	}

	@Override
	public StudentVO studentSelect(StudentVO vo) {
		return map.studentSelect(vo);
	}

	@Override
	public int studentInsert(StudentVO vo) {
		return map.studentInsert(vo);
	}

	@Override
	public int studentUpdate(StudentVO vo) {
		return map.studentUpdate(vo);
	}

	@Override
	public int studentDelete(StudentVO vo) {
		return map.studentDelete(vo);
	}

}
