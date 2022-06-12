package co.mia.bs0611.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.mia.bs0611.notice.vo.NoticeVO;

public interface NoticeMapper {
	public List<NoticeVO> noticeSelectAll(); //전체 출력
	public List<NoticeVO> noticeSearch(@Param("key") String key, @Param("val") String val); //작성자, 제목, 전체 검색 등
	
	public NoticeVO noticeSelectOne(NoticeVO vo);
	public int noticeInsert(NoticeVO vo);
	public int noticeUpdate(NoticeVO vo);
	public int noticeDelete(int id);
}
