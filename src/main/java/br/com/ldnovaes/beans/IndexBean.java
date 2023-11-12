package br.com.ldnovaes.beans;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home", "" })
public class IndexBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IndexBean() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("conteudo", "index-conteudo.jsp");

        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
