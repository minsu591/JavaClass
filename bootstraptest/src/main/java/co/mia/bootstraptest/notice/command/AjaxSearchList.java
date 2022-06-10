package co.mia.bootstraptest.notice.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.mia.bootstraptest.comm.Command;
import co.mia.bootstraptest.notice.service.NoticeService;
import co.mia.bootstraptest.notice.serviceImpl.NoticeServiceImpl;
import co.mia.bootstraptest.notice.vo.NoticeVO;

public class AjaxSearchList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 검색
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		ObjectMapper mapper = new ObjectMapper(); //json string으로 만들기 위해
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		list = dao.noticeSearchList(key, val);
		String jsonData="";
		try {
			jsonData=mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "ajax:"+jsonData;
	}

}
