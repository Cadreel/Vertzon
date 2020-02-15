package br.com.vertzon.loja.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.vertzon.loja.filter.AbstractFilter;
import br.com.vertzon.loja.modelo.Usuario;

@WebFilter(urlPatterns = "/pages/protected/*", servletNames = "{Faces Servlet}")
public class LoginFilter extends AbstractFilter implements Filter{

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response =  (HttpServletResponse) sr1;
        
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");
        if (session.isNew() || user == null) {
            doLogin(sr, sr1, request);
            request.getSession(false);
        }else{
             fc.doFilter(sr, sr1); 
        }
	}

	@Override
	public void destroy() {
		
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
