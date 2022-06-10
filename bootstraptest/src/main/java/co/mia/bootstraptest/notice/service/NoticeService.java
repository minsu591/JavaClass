package co.mia.bootstraptest.notice.service;

import java.util.List;

import co.mia.bootstraptest.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> noticeSelectList();
	List<NoticeVO> noticeSearchList(String key, String val);
	
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	
	int noticeHitUpdate(int id);
}
