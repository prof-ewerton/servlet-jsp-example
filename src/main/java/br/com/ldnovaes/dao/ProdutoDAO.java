package br.com.ldnovaes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ldnovaes.dao.util.SQLProduto;
import br.com.ldnovaes.model.Produto;

public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO{

	public ProdutoDAO() {
		super(new SQLProduto());
	}

	@Override
	protected void adicionarParametrosInsert(PreparedStatement stm, Produto model) throws SQLException {
		stm.setString(1, model.getNome());
		
	}

	@Override
	protected void adicionarParametroDelete(PreparedStatement stm, Produto model) throws SQLException {
		stm.setLong(1, model.getId());
		
	}

	@Override
	protected void adicionarParametroBuscaPorId(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
		
	}

	@Override
	protected void adicionarParametrosUpdate(PreparedStatement stm, Produto model) throws SQLException {
		stm.setString(1, model.getNome());
		stm.setLong(2, model.getId());
	}

}
