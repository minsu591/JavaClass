package co.mia.bootstraptest.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.mia.bootstraptest.notice.vo.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList();
	List<NoticeVO> noticeSearchList(@Param("key") String key, @Param("val") String val);
	
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	
	int noticeHitUpdate(int id);

}
