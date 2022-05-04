package co.micol.notice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.notice.service.NoticeService;
import co.micol.notice.service.NoticeVO;
import co.micol.notice.serviceImpl.NoticeServiceImpl;
import co.micol.student.dto.StudentVO;
import co.micol.student.service.StudentService;
import co.micol.student.serviceImpl.StudentServiceImpl;

//1.학생 관리, 2.공지사항, 종료
//1 => 등록 수정 삭제 조회 종료
//2 => 등록 수정 삭제 조회 종료
public class Menu {
	private Scanner scn = new Scanner(System.in);
	private NoticeService ns = new NoticeServiceImpl(); // 게시판 관리

	private StudentService ss = new StudentServiceImpl(); // 학생 관리
	String studentId;

	public void run() {
		int menu = -1;
		while (true) {
			System.out.println("1. 학생관리 | 2. 공지사항 관리 | 3. 종료");
			menu = scn.nextInt();
			if (menu == 1) {
				studentManager();
			} else if (menu == 2) {
				noticeManager();
			} else if (menu == 3) {
				System.out.println("전체 시스템 종료합니다.");
				break;
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	private void noticeManager() {
		List<NoticeVO> noList = new ArrayList<NoticeVO>();//글 번호, 제목, 내용, 작성자, 작성시간, 조회수
		NoticeVO notice;
		int no;
		while (true) {
			System.out.println("1. 등록 | 2. 수정 | 3. 삭제 | 4. 한 글 조회 | 5. 전체 조회 | 6. 종료");
			int menu = -1;

			try {
				menu = scn.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력 바랍니다.");
			}

			if (menu == 1) {
				notice = new NoticeVO();
				System.out.print("작성자 이름을 입력해주세요 >>> "); // writer
				notice.setWriter(scn.nextLine());
				System.out.print("제목을 입력해주세요 >>> "); // title
				notice.setTitle(scn.nextLine());
				System.out.print("내용을 입력해주세요 >>> "); // subject
				notice.setSubject(scn.nextLine());

				ns.noticeInsert(notice);
			} else if (menu == 2) {
				System.out.print("수정할 게시글의 번호를 입력해주세요 >>> ");
				no = scn.nextInt();
				notice = new NoticeVO();
				notice.setId(no);
				System.out.print("수정할 게시글의 제목을 입력해주세요 >>> ");
				notice.setTitle(scn.next());
				System.out.print("수정할 게시글의 작성자를 입력해주세요 >>> ");
				notice.setWriter(scn.next());
				LocalDate now = LocalDate.now(); //현재 시간을 작성시간으로 넣음
				notice.setWdate(java.sql.Date.valueOf(now));
				System.out.print("수정할 게시글의 내용을 입력해주세요 >>> ");
				notice.setSubject(scn.next());

				int n = ns.noticeUpdate(notice); // 수정 완료하면 1로 출력
				if (n == 1) {
					System.out.println("수정 완료되었습니다.");
				}
			} else if (menu == 3) {
				System.out.print("삭제할 게시글의 번호를 입력해주세요 >>> ");
				no = scn.nextInt();
				notice = new NoticeVO();
				notice.setId(no);

				int n = ns.noticeDelete(notice);
				if (n == 1) {
					System.out.println("삭제 완료되었습니다.");
				}
			} else if (menu == 4) {
				System.out.print("조회할 글의 번호를 입력해주세요 >>> ");
				no = scn.nextInt();
				notice = new NoticeVO();
				notice.setId(no);
				notice = ns.noticeSelect(notice);
				if (notice == null) {
					System.out.println("조회할 글이 없습니다.");
				} else {
					notice.toString();
					System.out.println("============ 상세 보기 ===============");
					System.out.println(notice.getSubject());
				}
			} else if (menu == 5) {
				noList = ns.noticeSelectList();
				noList.toString();
			} else if (menu == 6) {
				System.out.println("공지사항 시스템을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}

		}
	}

	private void studentManager() {
		List<StudentVO> stList = new ArrayList<StudentVO>();
		StudentVO student;
		while (true) {
			System.out.println("1. 등록 | 2. 수정 | 3. 삭제 | 4. 한명 조회 | 5. 전체 조회 | 6. 종료");
			int menu = -1;
			try {
				menu = scn.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력 바랍니다.");
			}

			if (menu == 1) {
				student = new StudentVO();
				System.out.print("등록할 학생의 이메일을 입력해주세요 >>> ");
				student.setStudentId(scn.next());
				System.out.print("등록할 학생의 이름을 입력해주세요 >>> ");
				student.setName(scn.next());
				System.out.print("등록할 학생의 생년월일을 입력해주세요 >>> ");
				String birth = scn.next();
				student.setBirthday(java.sql.Date.valueOf(birth));
				System.out.print("등록할 학생의 학과를 입력해주세요 >>> ");
				student.setMajor(scn.next());
				System.out.print("등록할 학생의 주소를 입력해주세요 >>> ");
				student.setAddress(scn.next());
				System.out.print("등록할 학생의 전화번호를 입력해주세요 >>> ");
				student.setTel(scn.next());

				ss.insertStudent(student);
			} else if (menu == 2) {
				System.out.print("수정할 학생의 이메일을 입력해주세요 >>> ");
				studentId = scn.next();
				student = new StudentVO();
				student.setStudentId(studentId);
				System.out.println("수정할 학생의 이름을 입력해주세요 >>> ");
				student.setName(scn.next());
				System.out.print("등록할 학생의 생년월일을 입력해주세요 >>> ");
				String birth = scn.next();
				student.setBirthday(java.sql.Date.valueOf(birth));
				System.out.println("수정할 학생의 전공을 입력해주세요 >>> ");
				student.setMajor(scn.next());
				ss.updateStudent(student);
			} else if (menu == 3) {
				System.out.print("삭제할 학생의 이메일을 입력해주세요 >>> ");
				studentId = scn.next();
				student = new StudentVO();
				student.setStudentId(studentId);
				ss.deleteStudent(student);
			} else if (menu == 4) {
				System.out.print("조회할 학생의 이메일을 입력해주세요 >>> ");
				studentId = scn.next();
				student = new StudentVO();
				student.setStudentId(studentId);
				student = ss.selectStudent(student);
				if (student == null) {
					System.out.println("조회할 학생이 없습니다.");
				} else {
					student.toString();
				}
			} else if (menu == 5) {
				stList = ss.selectListStudent();
				stList.toString();
			} else if (menu == 6) {
				System.out.println("학생관리 시스템을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

}
