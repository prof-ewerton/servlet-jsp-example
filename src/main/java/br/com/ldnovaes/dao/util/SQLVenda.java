package br.com.ldnovaes.dao.util;

public class SQLVenda implements SQLUtil{

	@Override
	public String getInsertSql() {
		return "INSERT INTO venda (cliente_id, produto_id, total) VALUES (?, ?, ?)"; 
	}

	@Override
	public String getSelectAllSql() {
		return "SELECT \n" +
				"    v.id as id,\n" +
			    "    c.id as cliente_id,\n" +
			    "    p.id as produto_id,\n" +
			    "    v.total as total\n" +
			    "FROM cliente as c \n" +
			    "    JOIN venda as v ON v.cliente_id = c.id\n" +
			    "    JOIN produto as p ON v.produto_id = p.id\n" +
			    "ORDER BY id, cliente_id, produto_id, total\n";
	}

	@Override
	public String getDeleteSQL() {
		return "DELETE FROM venda WHERE id = ?";
	}

	@Override
	public String getSelectByIdSQL() {
		return "SELECT \n" +
				"    v.id as id,\n" +
			    "    c.id as cliente_id,\n" +
			    "    p.id as produto_id,\n" +
			    "    v.total as total\n" +
			    "FROM cliente as c \n" +
			    "    JOIN venda as v ON v.cliente_id = c.id\n" +
			    "    JOIN produto as p ON v.produto_id = p.id\n" +
			    "WHERE v.id = ? \n" +
			    "ORDER BY id, cliente_id, produto_id, total\n";
	}

	@Override
	public String getUpdateSQL() {
		return "UPDATE venda " +
				"SET cliente_id = ?, produto_id = ?, total = ? " +
				"WHERE id = ?";
	}

}
