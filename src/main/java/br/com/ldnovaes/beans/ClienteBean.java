package br.com.ldnovaes.beans;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.service.ClienteService;

@WebServlet(name = "cadastroCliente", urlPatterns = { "/cliente" })
public class ClienteBean extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteService service = new ClienteService();
	private Integer statusQuery;
	List<Cliente> clientes;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		clientes = this.service.listarTodos();
		req.setAttribute("listaTodos", clientes);
		req.setAttribute("conteudo", "cliente/listar-clientes.jsp");
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
			
			this.cliente = this.criarOuAtualizaCliente(req, resp);
			this.preencherLista(req, resp);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.cliente = this.service.buscarPorId(id);
		this.statusQuery = this.service.deletar(cliente);
		this.preencherLista(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		this.cliente = this.service.buscarPorId(id);
		req.setAttribute("cliente", cliente);
		req.setAttribute("conteudo", "cliente/cadastro-cliente.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	private void preencherLista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.clientes = this.service.listarTodos();
		req.setAttribute("listaTodos", clientes);
		req.setAttribute("conteudo", "cliente/listar-clientes.jsp");

		if (this.statusQuery == 1) {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // sucesso
		} else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp); // falha
		}
	}
	
	private Cliente criarOuAtualizaCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String cpf = req.getParameter("cpf");
		String telefone = req.getParameter("telefone");

		
		if (id == null || id.equals("")) {
			Cliente cliente = new Cliente();
			
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setCpf(cpf);
			cliente.setTelefone(telefone);
			
			this.statusQuery = this.service.persistir(cliente);
			
			return cliente;	
		} else {
			Long idLong = Long.valueOf(id);
			Cliente cliente = this.service.buscarPorId(idLong);
			
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setCpf(cpf);
			cliente.setTelefone(telefone);
			
			this.statusQuery = this.service.editar(cliente);
			return cliente;
		}

	}
}
