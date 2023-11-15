package br.com.ldnovaes.dao;

import java.sql.ResultSet;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.ldnovaes.model.Cliente;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOTeste {
	IClienteDAO clienteDAO = new ClienteDAO();
	Integer statusQuery;
	Long ID_CLIENTE = 1L;

	@Test
	@Order(value = 2)
	public void buscarCliente() throws ServletException {
		ResultSet resultSet = this.clienteDAO.buscarPorId(ID_CLIENTE);
		Cliente cliente = this.criarClienteResulSet(resultSet);
		Assertions.assertNotNull(cliente);
	}

	@Test
	@Order(value = 1)
	public void salvarCiente() throws ServletException {
		Cliente cliente = this.criarCliente();
		this.statusQuery = this.clienteDAO.persistir(cliente);
		Assertions.assertEquals(1, statusQuery);
	}

	@Test
	@Order(value = 4)
	public void deletarCliente() throws ServletException {
		ResultSet resultSet = this.clienteDAO.buscarPorId(ID_CLIENTE);
		Cliente cliente = this.criarClienteResulSet(resultSet);
		this.statusQuery = this.clienteDAO.deletar(cliente);
		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 3)
	public void editarCliente() throws ServletException {
		ResultSet resultSet = this.clienteDAO.buscarPorId(ID_CLIENTE);
		Cliente cliente = this.criarClienteResulSet(resultSet);

		cliente.setCpf("00000000000");

		this.statusQuery = this.clienteDAO.editar(cliente);

		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 5)
	public void listarTodos() {
		ResultSet listaTodosClientes = null;
			
		try {
			listaTodosClientes = this.clienteDAO.listarTodos();
		} catch (ServletException e) {
			
		} finally {
			Assertions.assertNotNull(listaTodosClientes);
		}
		
	}

	public Cliente criarCliente() {

		Cliente cliente = new Cliente();

		cliente.setCpf("70646217893");
		cliente.setEmail("teste@gmail.com");
		cliente.setNome("leandro");
		cliente.setTelefone("61 9 96349099");

		return cliente;
	}

	public Cliente criarClienteResulSet(ResultSet resultSet) {
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
		} catch (Exception e) {
			return null;
		}

	}
}
