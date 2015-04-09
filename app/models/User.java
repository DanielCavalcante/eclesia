package models;

import play.data.validation.Constraints.Required;

public class User {
	
	private Long id;
	@Required
	private String name;
	@Required
	private String login;
	@Required
	private String password;
	private boolean root;
	@Required
	private String confirmPassword;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRoot() {
		return root;
	}
	public void setRoot(boolean root) {
		this.root = root;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + 
				"/n" + "Name: " + getName() + 
				"/n Login : " + getLogin() + 
				"/n Password: " + getPassword() + 
				"/n Root: " + isRoot();
	}

}
