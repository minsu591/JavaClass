package co.mia.bs0611.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bs0611.comm.Command;
import co.mia.bs0611.notice.service.NoticeService;

public class NoticeForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//들어온 데이터 처리
		String key = (String) request.getAttribute("key");
		String val = (String) request.getAttribute("val");
		NoticeService dao = new NoticeServiceImpl();
		dao.
		return null;
	}

}
