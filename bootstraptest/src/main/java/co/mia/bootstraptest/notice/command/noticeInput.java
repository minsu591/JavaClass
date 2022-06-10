package co.mia.bootstraptest.notice.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.mia.bootstraptest.comm.Command;
import co.mia.bootstraptest.notice.service.NoticeService;
import co.mia.bootstraptest.notice.serviceImpl.NoticeServiceImpl;
import co.mia.bootstraptest.notice.vo.NoticeVO;

public class noticeInput implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 폼의 정보가 넘어오는 곳
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();

		// 제출된 사진 불러오기
		// separate는 2개씩
		// 실제로 저장하는 공간
		String saveDir = "c:\\Temp\\";
		int size = 1024 * 1024 * 1024; // 파일 최대 사이즈
		String dirFile = null; //물리적인 파일
		String originalFile = null;
		try {
			MultipartRequest multi = new MultipartRequest(request, saveDir, size, "utf-8", new DefaultFileRenamePolicy());
			dirFile = multi.getFilesystemName("file"); //물리공간에 저장될 파일명 (동일 이름이면 구분할 수 있게)
			originalFile = multi.getOriginalFileName("file"); //실제 파일명
			System.out.println("orgFile : "+originalFile);
			System.out.println("dirFile : "+ dirFile);
			
			//파일을 저장한 이후에는 multi파트로 읽어줘야함.
			vo.setWriter(multi.getParameter("writer"));
			vo.setWdate(Date.valueOf(multi.getParameter("wdate")));
			vo.setSubject(multi.getParameter("subject"));
			vo.setTitle(multi.getParameter("title"));
			vo.setHit(0);
			
			//파일이 없으면 안담기게
			vo.setFileName(originalFile);
			vo.setDirFileName(dirFile);
		} catch (IOException e) { //파일이 존재하지 않을 수도 있음, file not found exception도 생길 수 있음
			e.printStackTrace();
		}

		int n = dao.noticeInsert(vo);
		if(n != 0) {
			request.setAttribute("message","정상 입력되었습니다!");
		}else {
			request.setAttribute("message", "입력에 실패하였습니다...");
		}

		return "notice/noticeMessage";
	}

}
