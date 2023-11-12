package br.com.ldnovaes.dao;
	

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.util.SQLCliente;
import br.com.ldnovaes.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO {
	
	public ClienteDAO() {
		super(new SQLCliente());
	}
	
	@Override
	public ResultSet listarTodos() throws ServletException {
		return super.listarTodos();
	}

	@Override
	protected void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setString(1, cliente.getNome());
		stm.setString(2, cliente.getEmail());
		stm.setString(3, cliente.getCpf());
		stm.setString(4, cliente.getTelefone());
	}		
	
	@Override
	protected void adicionarParametroDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setLong(1, cliente.getId());
		
	}
	
	@Override
	protected void adicionarParametroBuscaPorId(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
	}
	
	@Override
	protected void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setString(1, cliente.getNome());
		stm.setString(2, cliente.getEmail());
		stm.setString(3, cliente.getCpf());
		stm.setString(4, cliente.getTelefone());
		stm.setLong(5, cliente.getId());
		
	}
}
