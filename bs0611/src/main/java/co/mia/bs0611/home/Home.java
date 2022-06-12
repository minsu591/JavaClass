package co.mia.bs0611.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bs0611.comm.Command;

public class Home implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "home/home";
	}

}
