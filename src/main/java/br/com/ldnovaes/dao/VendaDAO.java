package br.com.ldnovaes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ldnovaes.dao.util.SQLVenda;
import br.com.ldnovaes.model.Venda;

public class VendaDAO extends GenericDAO<Venda> implements IVendaDAO{

	public VendaDAO() {
		super(new SQLVenda());
	}

	@Override
	protected void adicionarParametrosInsert(PreparedStatement stm, Venda venda) throws SQLException {
		stm.setLong(1, venda.getCliente().getId());
		stm.setLong(2, venda.getProduto().getId());
		stm.setLong(3, venda.getValorTotal());
	}

	@Override
	protected void adicionarParametroDelete(PreparedStatement stm, Venda venda) throws SQLException {
		stm.setLong(1, venda.getId());
		
	}

	@Override
	protected void adicionarParametroBuscaPorId(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
	}

	@Override
	protected void adicionarParametrosUpdate(PreparedStatement stm, Venda venda) throws SQLException {
		stm.setLong(1, venda.getCliente().getId());
		stm.setLong(2, venda.getProduto().getId());
		stm.setLong(3, venda.getValorTotal());
		stm.setLong(4, venda.getId());
	}

}
