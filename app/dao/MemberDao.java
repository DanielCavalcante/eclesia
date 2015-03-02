package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Member;
import util.ConnectionJDBC;


public class MemberDao {

	private Connection con;
	
	public MemberDao() throws SQLException {
		this.con = ConnectionJDBC.getInstance().getConnection();
	}
	
	public List<Member> list() {
		List<Member> members = new ArrayList<Member>();
		try {
			PreparedStatement stm = this.con.prepareStatement("SELECT * FROM members");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getLong("id"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				/* TODO
				 * Falta pegar os outros campos
				 */
				members.add(m);
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return members;
	}
	
	public void save(Member member) {
		String sql = "INSERT INTO members VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stm = this.con.prepareStatement(sql);
			stm.setString(2, member.getName());
			stm.setString(3, member.getPhone());
			stm.setString(4, member.getEmail());
			/* TODO
			 * Falta os outros campos
			 */
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Long id) {
		String sql = "delete from members where id=" + id + ";";
		try {
			PreparedStatement stm = this.con.prepareStatement(sql);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
