package br.com.ldnovaes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.model.Venda;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendaDAOTeste {
	
	IVendaDAO vendaDAO = new VendaDAO();
	IClienteDAO clienteDAO = new ClienteDAO();
	IProdutoDAO produtoDAO = new ProdutoDAO();
	
	Integer statusQuery;
	Long ID_VENDA = 1L;

	@Test
	@Order(value = 2)
	public void buscarVenda() throws ServletException {
		ResultSet resultSet = this.vendaDAO.buscarPorId(ID_VENDA);
		Venda venda = this.criarVendaResulSet(resultSet);
		
		Assertions.assertNotNull(venda);
	}

	@Test
	@Order(value = 1)
	public void salvarVenda() throws ServletException {
		Venda venda = this.criarVenda();
		this.statusQuery = this.vendaDAO.persistir(venda);
		Assertions.assertEquals(1, statusQuery);
	}

	@Test
	@Order(value = 4)
	public void deletarVenda() throws ServletException {
		ResultSet resultSet = this.vendaDAO.buscarPorId(ID_VENDA);
		Venda venda = this.criarVendaResulSet(resultSet);
		this.statusQuery = this.vendaDAO.deletar(venda);
		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 3)
	public void editarVenda() throws ServletException {
		ResultSet resultSet = this.vendaDAO.buscarPorId(ID_VENDA);
		Venda venda = this.criarVendaResulSet(resultSet);

		venda.setValorTotal(8000l);

		this.statusQuery = this.vendaDAO.editar(venda);

		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 5)
	public void listarTodos() {
		ResultSet listaTodasVendas = null;
			
		try {
			listaTodasVendas = this.vendaDAO.listarTodos();
		} catch (ServletException e) {
			
		} finally {
			Assertions.assertNotNull(listaTodasVendas);
		}
		
	}

	public Venda criarVenda() {
		
		Venda venda = new Venda();
		try {
			Cliente cliente = this.converterResultSetParaClientel(this.clienteDAO.buscarPorId(1L));
			Produto produto = this.converterResultSetParaProduto(this.produtoDAO.buscarPorId(1L));
			
			venda.setCliente(cliente);
			venda.setProduto(produto);
			venda.setValorTotal(5000l);

			return venda;
		} catch (Exception e) {
			return null;
		}
		
	}

	public Venda criarVendaResulSet(ResultSet resultSet) {
		try {
			if (resultSet.next()) {

				Long id = resultSet.getLong("id");
				Long cliente_id = resultSet.getLong("cliente_id");
				Long produto_id = resultSet.getLong("produto_id");
				Long valorTotal = resultSet.getLong("total");

				Cliente cliente = this.converterResultSetParaClientel(this.clienteDAO.buscarPorId(cliente_id));
				Produto produto = this.converterResultSetParaProduto(this.produtoDAO.buscarPorId(produto_id));
				Venda venda = new Venda();
				
				venda.setId(id);
				venda.setCliente(cliente);
				venda.setProduto(produto);
				venda.setValorTotal(valorTotal);

				return venda;
			} else {
				throw new ServletException("Não encontrado nenhuma venda");
			}
		} catch (Exception e) {
			return null;
		}

	}
	
	protected Cliente converterResultSetParaClientel(ResultSet resultSet) throws ServletException {

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
	
	protected Produto converterResultSetParaProduto(ResultSet resultSet) throws ServletException {
		try {

			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");

				Produto produto = new Produto();
				produto.setId(id);
				produto.setNome(nome);

				return produto;
				
			} else {
				throw new ServletException("Não encontrado nenhum produto");
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
