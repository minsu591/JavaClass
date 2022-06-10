package co.mia.bootstraptest.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bootstraptest.comm.Command;

public class noticeInputForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 입력 폼 호출
		return "notice/noticeInputForm";
	}

}
