package br.com.ldnovaes.dao.util;

public class SQLCliente implements SQLUtil{

	@Override
	public String getInsertSql() {
		return "INSERT INTO cliente (nome, email, cpf, telefone) "+
				"VALUES (?, ?, ?, ?)";
	}

	@Override
	public String getSelectAllSql() {
		return "SELECT * FROM cliente";
	}
	
	@Override
	public String getDeleteSQL() {
		return "DELETE FROM cliente WHERE id = ?";
	}
	
	@Override 
	public String getSelectByIdSQL() {
		return "SELECT * FROM cliente WHERE id = ?";
	}
	
	@Override 
	public String getUpdateSQL() {
		return "UPDATE cliente " +
				"SET nome = ?, email = ?, cpf = ?, telefone = ? " +
				"WHERE id = ?";
	}

}
