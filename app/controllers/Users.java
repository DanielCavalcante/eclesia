package controllers;

import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Users extends Controller {
	
	private static Form<User> userForm = Form.form(User.class);
	
	public static Result create() {
		return ok(views.html.user.create.render());
	}
	
	public static Result list() {
		List<User> users = new ArrayList<User>();
		UserDao dao = new UserDao();
		users = dao.list();
		return ok(views.html.user.list.render(users));
	}
	
	public static Result save() {
		UserDao dao = new UserDao();
		Form<User> formFromRequest = userForm.bindFromRequest();
		
		if (formFromRequest.hasErrors())
			return badRequest(views.html.member.create.render());
		
		User user = formFromRequest.get();
		
		dao.save(user);
		
		return redirect(routes.Users.list());
	}
	
	public static Result edit(Long id) {
//		UserDao dao = new UserDao();
//		User user = dao.find(id);
		
		return ok(views.html.user.create.render());
	}

}
