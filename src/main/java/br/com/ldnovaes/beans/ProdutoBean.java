package br.com.ldnovaes.beans;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.service.ProdutoService;

@WebServlet(name = "cadastroProduto", urlPatterns = { "/produto" })
public class ProdutoBean extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Produto produto;
	private ProdutoService service = new ProdutoService();
	private Integer statusQuery;
	List<Produto> produtos;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		produtos = this.service.listarTodos();
		req.setAttribute("listaTodos", produtos);
		req.setAttribute("conteudo", "produto/listar-produtos.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("acao") != null) { // post para deletar ou editar
			
			if (req.getParameter("acao").equals("deletar")) {
				this.doDelete(req, resp);
			} else {
				this.doPut(req, resp); // carrega o formulário
			}
			
		} else { // post para criação ou edição de cliente
			
			this.produto = this.criarOuAtualizaProduto(req, resp);
			this.preencherLista(req, resp);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.produto = this.service.buscarPorId(id);
		this.statusQuery = this.service.deletar(produto);
		this.preencherLista(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.produto = this.service.buscarPorId(id);
		req.setAttribute("produto", produto);
		req.setAttribute("conteudo", "produto/cadastro-produto.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	private void preencherLista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.produtos = this.service.listarTodos();
		req.setAttribute("listaTodos", produtos);
		req.setAttribute("conteudo", "produto/listar-produtos.jsp");

		if (this.statusQuery == 1) {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // sucesso
		} else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // falha
		}
	}
	
	private Produto criarOuAtualizaProduto(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		
		if (id == null || id.equals("")) {
			Produto produto = new Produto();
			
			produto.setNome(nome);
			
			this.statusQuery = this.service.persistir(produto);
			
			return produto;	
		} else {
			Long idLong = Long.valueOf(id);
			Produto produto = this.service.buscarPorId(idLong);
			
			produto.setNome(nome);
			
			this.statusQuery = this.service.editar(produto);
			return produto;
		}

	}

}
