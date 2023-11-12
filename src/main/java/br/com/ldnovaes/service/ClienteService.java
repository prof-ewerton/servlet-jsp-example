package br.com.ldnovaes.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.ClienteDAO;
import br.com.ldnovaes.model.Cliente;

public class ClienteService extends GenericService<Cliente> implements IClienteService {

	public ClienteService() {
		super(new ClienteDAO());
	}

	@Override
	public List<Cliente> converterResultSetParaLista(ResultSet resultSet) throws ServletException {
		List<Cliente> clientes = new ArrayList<>();

		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String cpf = resultSet.getString("cpf");
				String email = resultSet.getString("email");
				String telefone = resultSet.getString("telefone");

				Cliente cliente = new Cliente();
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setCpf(cpf);
				cliente.setEmail(email);
				cliente.setTelefone(telefone);

				clientes.add(cliente);
			}

			return clientes;
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected Cliente converterResultSetParaModel(ResultSet resultSet) throws ServletException {

		try {

			if (resultSet.next()) {

				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String cpf = resultSet.getString("cpf");
				String email = resultSet.getString("email");
				String telefone = resultSet.getString("telefone");

				Cliente cliente = new Cliente();
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setCpf(cpf);
				cliente.setEmail(email);
				cliente.setTelefone(telefone);

				return cliente;
			} else {
				throw new ServletException("Não encontrado nenhum cliente");
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
