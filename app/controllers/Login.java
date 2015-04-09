package controllers;

import models.User;
import dao.UserDao;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Login extends Controller {
	
	private static Form<models.Login> loginForm = Form.form(models.Login.class);
	private static UserDao dao = new UserDao();
	
	public static Result login() {
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		} else {
			models.Login login = loginForm.get();
			User user = dao.authenticate(login);
			
			if (user != null) {
				return redirect(routes.Application.index());
			} else {
				flash("DeuAguia");
				return redirect(views.html.login.render(loginForm));
			}
		}
	}
	
	public static Result logout() {
		session().clear();
		return redirect(views.html.login.render(loginForm));
	}

}
