package co.mia.study.student.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.study.comm.Command;

public class LoginForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "student/loginForm";
	}

}
