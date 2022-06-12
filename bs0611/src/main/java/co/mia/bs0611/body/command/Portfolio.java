package co.mia.bs0611.body.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bs0611.comm.Command;

public class Portfolio implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "body/portfolio";
	}

}
