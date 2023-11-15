package br.com.ldnovaes.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.ProdutoDAO;
import br.com.ldnovaes.model.Produto;

public class ProdutoService extends GenericService<Produto> implements IProdutoService {

	public ProdutoService() {
		super(new ProdutoDAO());
	}

	@Override
	protected Produto converterResultSetParaModel(ResultSet resultSet) throws ServletException {
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

	@Override
	protected List<Produto> converterResultSetParaLista(ResultSet resultSet) throws ServletException {
		List<Produto> produtos = new ArrayList<>();

		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");

				Produto produto = new Produto();
				produto.setId(id);
				produto.setNome(nome);
				
				produtos.add(produto);
			}
			return produtos;
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
