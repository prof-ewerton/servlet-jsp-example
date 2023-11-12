package br.com.ldnovaes.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException c) {
			System.out.println("Erro");
		}
		
		try {
			
			return DriverManager.getConnection("jdbc:postgresql://postgres-compose:5432/servlet_example", "postgres", "postgres");
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}
	}
}
