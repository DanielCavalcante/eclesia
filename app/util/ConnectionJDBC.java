package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {

	private static ConnectionJDBC instance;
	private Connection con;

	private ConnectionJDBC() {

	}

	public Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eclesia",
						"postgres", "root");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static ConnectionJDBC getInstance() {
		if (instance == null) {
			instance = new ConnectionJDBC();
		}
		return instance;
	}

}
