package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import models.Member;
import play.mvc.Controller;
import play.mvc.Result;

public class Members extends Controller {
	
	public static Result create() {
		return ok(views.html.member.create.render());
	}
	
	public static Result list() throws SQLException {
		List<Member> members = new ArrayList<Member>();
		MemberDao dao = new MemberDao();
		members = dao.list();
		return ok(views.html.member.list.render(members));
	}

}