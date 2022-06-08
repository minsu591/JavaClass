package co.mia.study.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.mia.study.notice.vo.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList();
	List<NoticeVO> noticeSearchList(@Param("key") String key, @Param("val") String val);
	
	NoticeVO noticeSelectOne(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	int noticeHitUpdate(int id); //조회수 증가 메소드
}

