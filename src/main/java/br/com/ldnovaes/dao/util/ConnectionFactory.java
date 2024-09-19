package br.com.ldnovaes.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionFactory {
	
	private static Connection conexao;
	
	public static Connection getConexao() throws SQLException {
		conexao = iniciarConexao();
		return conexao;
	}
	
	private static Connection iniciarConexao() {
		Map<String, String> env = System.getenv();
		
		for (Map.Entry<String,String> pair : env.entrySet()) {
		    System.out.println(pair.getKey());
		    System.out.println(pair.getValue());
		}
		
		String DB_USER = env.get("DB_USER");
		String DB_PASS = env.get("DB_PASS");
		String DB_HOST = env.get("DB_HOST");
		String DB_NAME = env.get("DB_NAME");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException c) {
			System.out.println("Erro");
		}
		
		try {
			
			return DriverManager.getConnection("jdbc:postgresql://" + DB_HOST+ ":5432/" + DB_NAME, DB_USER, DB_PASS);
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}
	}
}
