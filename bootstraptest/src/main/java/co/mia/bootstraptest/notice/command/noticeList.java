package co.mia.bootstraptest.notice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bootstraptest.comm.Command;
import co.mia.bootstraptest.notice.service.NoticeService;
import co.mia.bootstraptest.notice.serviceImpl.NoticeServiceImpl;
import co.mia.bootstraptest.notice.vo.NoticeVO;

public class noticeList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 목록 가져오기
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> list = dao.noticeSelectList();
		request.setAttribute("list", list);
		return "notice/noticeList";
	}
}
