package co.mia.study.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.study.comm.Command;
import co.mia.study.notice.service.NoticeService;
import co.mia.study.notice.serviceImpl.NoticeServiceImpl;
import co.mia.study.notice.vo.NoticeVO;

public class NoticeSelectOne implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setId(Integer.valueOf(request.getParameter("id")));
		vo = dao.noticeSelectOne(vo);
		request.setAttribute("notice", vo);
		return "notice/noticeSelectOne";
	}
}
