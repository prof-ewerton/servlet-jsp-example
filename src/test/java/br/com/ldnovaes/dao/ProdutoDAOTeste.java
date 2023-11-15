package br.com.ldnovaes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.ldnovaes.model.Produto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoDAOTeste {

	IProdutoDAO produtoDAO = new ProdutoDAO();
	
	Integer statusQuery;
	Long ID_PRODUTO = 1L;

	@Test
	@Order(value = 2)
	public void buscarProduto() throws ServletException {
		ResultSet resultSet = this.produtoDAO.buscarPorId(ID_PRODUTO);
		Produto produto = this.converterResultSetParaProduto(resultSet);
		
		Assertions.assertNotNull(produto);
	}

	@Test
	@Order(value = 1)
	public void salvarVenda() throws ServletException {
		Produto produto = this.criarProduto();
		this.statusQuery = this.produtoDAO.persistir(produto);
		Assertions.assertEquals(1, statusQuery);
	}

	@Test
	@Order(value = 4)
	public void deletarProduto() throws ServletException {
		ResultSet resultSet = this.produtoDAO.buscarPorId(ID_PRODUTO);
		Produto produto = this.converterResultSetParaProduto(resultSet);
		this.statusQuery = this.produtoDAO.deletar(produto);
		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 3)
	public void editarProduto() throws ServletException {
		ResultSet resultSet = this.produtoDAO.buscarPorId(ID_PRODUTO);
		Produto produto = this.converterResultSetParaProduto(resultSet);

		produto.setNome("teste feito");

		this.statusQuery = this.produtoDAO.editar(produto);

		Assertions.assertEquals(1, this.statusQuery);
	}

	@Test
	@Order(value = 5)
	public void listarTodos() {
		ResultSet listaTodosProdutos = null;
			
		try {
			listaTodosProdutos = this.produtoDAO.listarTodos();
		} catch (ServletException e) {
			
		} finally {
			Assertions.assertNotNull(listaTodosProdutos);
		}
		
	}

	public Produto criarProduto() {
		
		Produto produto = new Produto();
		produto.setNome("teste nao realizado");
		return produto;
		
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
