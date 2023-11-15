package br.com.ldnovaes.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.VendaDAO;
import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.model.Venda;

public class VendaService extends GenericService<Venda> implements IVendaService{
	
	ClienteService clienteService;
	ProdutoService produtoService;
	
	public VendaService() {
		super(new VendaDAO());
		this.clienteService = new ClienteService();
		this.produtoService = new ProdutoService();
	}

	@Override
	protected Venda converterResultSetParaModel(ResultSet resultSet) throws ServletException {
		try {

			if (resultSet.next()) {

				Long id = resultSet.getLong("id");
				Long cliente_id = resultSet.getLong("cliente_id");
				Long produto_id = resultSet.getLong("produto_id");
				Long valorTotal = resultSet.getLong("total");

				Cliente cliente = this.clienteService.buscarPorId(cliente_id);
				Produto produto = this.produtoService.buscarPorId(produto_id);
				Venda venda = new Venda();
				
				venda.setId(id);
				venda.setCliente(cliente);
				venda.setProduto(produto);
				venda.setValorTotal(valorTotal);

				return venda;
			} else {
				throw new ServletException("Não encontrado nenhuma venda");
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	
	}

	@Override
	protected List<Venda> converterResultSetParaLista(ResultSet resultSet) throws ServletException {
		List<Venda> vendas = new ArrayList<>();

		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				Long cliente_id = resultSet.getLong("cliente_id");
				Long produto_id = resultSet.getLong("produto_id");
				Long valorTotal = resultSet.getLong("total");

				Cliente cliente = this.clienteService.buscarPorId(cliente_id);
				Produto produto = this.produtoService.buscarPorId(produto_id);
				Venda venda = new Venda();
				
				venda.setId(id);
				venda.setCliente(cliente);
				venda.setProduto(produto);
				venda.setValorTotal(valorTotal);

				vendas.add(venda);
			}
			return vendas;
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	public Cliente buscarClientePorId(Long id) throws ServletException {
		return this.clienteService.buscarPorId(id);
	}
	
	public Produto buscarProdutoPorId(Long id) throws ServletException {
		return this.produtoService.buscarPorId(id);
	}

}
