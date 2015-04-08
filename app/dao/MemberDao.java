package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Member;
import util.ConnectionJDBC;


public class MemberDao {

	private Connection con;
	
	public MemberDao() {
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
		String sqlMember = "INSERT INTO members (name, phone, email, ministry, conversion_date, birth_date, address_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String sqlAddress = "INSERT INTO addresses (street, cep, city, state) VALUES (?, ?, ?, ?)"; 
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			PreparedStatement stm = this.con.prepareStatement(sqlMember);
			stm.setString(1, member.getName());
			stm.setString(2, member.getPhone());
			stm.setString(3, member.getEmail());
			stm.setString(4, "Louvor");
			
			Date dataConversion = sdf.parse(member.getConversionDate());
			Date dataBirthDate = sdf.parse(member.getBirthDate());
			
			stm.setDate(5, new java.sql.Date(dataConversion.getTime()));
			stm.setDate(6, new java.sql.Date(dataBirthDate.getTime()));
			
			PreparedStatement stmAddress = this.con.prepareStatement(sqlAddress, Statement.RETURN_GENERATED_KEYS);
			stmAddress.setString(1, member.getAddress().getStreet());
			stmAddress.setString(2, member.getAddress().getCep());
			stmAddress.setString(3, member.getAddress().getCity());
			stmAddress.setString(4, member.getAddress().getState());
			
			System.out.println(member);
			
			stmAddress.execute();
			
			ResultSet rs = stmAddress.getGeneratedKeys();
			rs.next();
			Long addressId = rs.getLong(1);
			
			stm.setLong(7, addressId);
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
