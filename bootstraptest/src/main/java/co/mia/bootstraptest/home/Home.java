package co.mia.bootstraptest.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bootstraptest.comm.Command;

public class Home implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "home/home";
	}

}
