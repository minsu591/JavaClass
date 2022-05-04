package co.micol.notice.service;

import java.util.List;

public interface NoticeService {
	List<NoticeVO> noticeSelectList(); //전체 게시글 조회
	NoticeVO noticeSelect(NoticeVO vo); //글 하나 조회
	int noticeInsert(NoticeVO vo); //글 삽입
	int noticeUpdate(NoticeVO vo); //글 수정
	int noticeDelete(NoticeVO vo); //글 삭제
}
