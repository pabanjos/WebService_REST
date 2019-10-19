package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/compra?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "1234";

	protected void abrirConexao() throws Exception {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASS);
	}

	protected void fecharConexao() throws Exception {
		con.close();
	}

}
