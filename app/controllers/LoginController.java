package controllers;

import models.Login;
import models.Member;
import models.User;
import dao.UserDao;
import patch.PatchedForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {
	
//	private static Form<Login> form = new PatchedForm<Login>(Login.class);
	private static Form<Login> form = Form.form(Login.class);
	private static UserDao dao = new UserDao();
	
	public static Result login() {
		if (form.hasErrors()) {
			return badRequest(views.html.login.render(form));
		} else {
			Login login = form.get();
			User user = dao.authenticate(login);
			
			if (user != null) {
				return redirect(routes.Application.index());
			} else {
				flash("DeuAguia");
				return redirect(views.html.login.render(form));
			}
		}
	}
	
	public static Result logout() {
		session().clear();
		return redirect(views.html.login.render(form));
	}

}
