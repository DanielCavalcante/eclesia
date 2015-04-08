package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import models.Member;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import dao.MemberDao;

public class Members extends Controller {
	
	private static Form<Member> memberForm = Form.form(Member.class);
	
	public static Result create() {
		return ok(views.html.member.create.render());
	}
	
	public static Result list() {
		List<Member> members = new ArrayList<Member>();
		MemberDao dao = new MemberDao();
		members = dao.list();
		return ok(views.html.member.list.render(members));
	}
	
	public static Result save() {
		MemberDao dao = new MemberDao();
		Form<Member> formFromRequest = memberForm.bindFromRequest();
		
//		if (formFromRequest.hasErrors()) {
//			return badRequest(views.html.member.create.render());
//		}
		File path = storePicture();
		Member member = formFromRequest.get();
		member.setImage(path.getName());
		
		dao.save(member);
		
		return redirect(routes.Members.list());
	}
	
	private static File storePicture() {
		try {
			RequestBody requestBody = request().body();
			MultipartFormData body = requestBody.asMultipartFormData();
			FilePart filePart = body.getFile("picture");
			File picture = filePart.getFile();
			File path = destinationFile(filePart);
			FileUtils.moveFile(picture, path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static File destinationFile(FilePart picture) {
		return new File("/public/images/members/", System.currentTimeMillis() + "_" + picture.getFilename());
	}
	
}