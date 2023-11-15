package br.com.ldnovaes.beans;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ldnovaes.model.Venda;
import br.com.ldnovaes.service.VendaService;

@WebServlet(name = "cadastroVenda", urlPatterns = { "/venda" })
public class VendaBean extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Venda venda;
	private VendaService service = new VendaService();
	private Integer statusQuery;
	List<Venda> vendas;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.vendas = this.service.listarTodos();
		req.setAttribute("listaTodos", vendas);
		req.setAttribute("conteudo", "venda/listar-vendas.jsp");
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
			
			this.venda = this.criarOutAtualizarVenda(req, resp);
			this.preencherLista(req, resp);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.venda = this.service.buscarPorId(id);
		this.statusQuery = this.service.deletar(venda);
		this.preencherLista(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.venda = this.service.buscarPorId(id);
		req.setAttribute("venda", venda);
		req.setAttribute("conteudo", "venda/cadastro-venda.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	private void preencherLista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.vendas = this.service.listarTodos();
		req.setAttribute("listaTodos", this.vendas);
		req.setAttribute("conteudo", "venda/listar-vendas.jsp");

		if (this.statusQuery == 1) {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // sucesso
		} else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // falha
		}
	}
	
	private Venda criarOutAtualizarVenda(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		String id = req.getParameter("id");
		String cliente_id = req.getParameter("cliente-id");
		String produto_id = req.getParameter("produto-id");
		Long valorTotal = Long.valueOf(req.getParameter("valor-total"));

		
		if (id == null || id.equals("")) {
			Venda venda = new Venda();
			
			venda.setCliente(this.service.buscarClientePorId(Long.valueOf(cliente_id)));
			venda.setProduto(this.service.buscarProdutoPorId(Long.valueOf(produto_id)));
			venda.setValorTotal(valorTotal);
			
			this.statusQuery = this.service.persistir(venda);
			return venda;	
			
		} else {
			Long idLong = Long.valueOf(id);
			Venda venda = this.service.buscarPorId(idLong);
			
			venda.setCliente(this.service.buscarClientePorId(Long.valueOf(cliente_id)));
			venda.setProduto(this.service.buscarProdutoPorId(Long.valueOf(produto_id)));
			venda.setValorTotal(valorTotal);
			
			this.statusQuery = this.service.editar(venda);
			return venda;
		}

	}
}
