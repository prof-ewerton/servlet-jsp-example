package br.com.ldnovaes.dao.util;

public class SQLProduto implements SQLUtil {

	@Override
	public String getInsertSql() {
		return "INSERT INTO produto (nome) VALUES (?)";
	}

	@Override
	public String getSelectAllSql() {
		return "SELECT * FROM produto";
	}

	@Override
	public String getDeleteSQL() {
		return "DELETE FROM produto WHERE id = ?";
	}

	@Override
	public String getSelectByIdSQL() {
		return "SELECT * FROM produto WHERE id = ?";
	}

	@Override
	public String getUpdateSQL() {
		return "UPDATE produto " +
				"SET nome = ? " +
				"WHERE id = ?";
	}

}
