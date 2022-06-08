package co.mia.study.notice.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.study.comm.Command;
import co.mia.study.notice.service.NoticeService;
import co.mia.study.notice.serviceImpl.NoticeServiceImpl;
import co.mia.study.notice.vo.NoticeVO;

public class NoticeSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//공지사항 목록
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> notices = new ArrayList<NoticeVO>();
		notices = dao.noticeSelectList();
		request.setAttribute("notices", notices); //변수로 위의 값을 담음
		return "notice/noticeSelectList";
	}

}
