package br.com.vertzon.loja.filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {

	public AbstractFilter() {
		super();
	}
	
	protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/login.xhtml");
		dispatcher.forward(request, response);
	}
	
	protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/acessoNegado.xhtml");
		dispatcher.forward(request, response);
	}
}
