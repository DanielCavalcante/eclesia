package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import util.ConnectionJDBC;

public class UserDao {
	
private Connection con;
	
	public UserDao() {
		this.con = ConnectionJDBC.getInstance().getConnection();
	}
	
	public List<User> list() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement stm = this.con.prepareStatement("SELECT * FROM users");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setRoot(rs.getBoolean("root"));
				users.add(u);
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void save(User user) {
		String sql = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
		try {
			PreparedStatement stm = this.con.prepareStatement(sql);
			stm.setString(1, user.getName());
			stm.setString(2, user.getLogin());
			stm.setString(3, user.getPassword());
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User find(Long id) {
		String sql = "SELECT * FROM users WHERE id = " + id;
		User user = new User();
		try {
			PreparedStatement stm = this.con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setRoot(rs.getBoolean("root"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
