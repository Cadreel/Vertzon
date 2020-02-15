package br.com.vertzon.loja.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.vertzon.loja.dao.UsuarioDAO;
import br.com.vertzon.loja.modelo.Usuario;

@RequestScoped
@ManagedBean
public class LoginController extends AbstractController {

	@ManagedProperty(value = UsuarioController.INJECTION_NAME)
    private UsuarioController usuarioController;

    private String loginOrEmail;
    private String senha;

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public String getLogin() {
        return loginOrEmail;
    }

    public void setLogin(String login) {
        this.loginOrEmail = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private Usuario isValidLogin(String loginOrEmail, String senha) {
    	Usuario user;
    	
    	if(isEmail(loginOrEmail)) {
    		user = new UsuarioDAO().findByEmail(loginOrEmail);
    	}else {
    		user = new UsuarioDAO().findByLogin(loginOrEmail);
    	}
    	
    	if(user == null || !senha.equals(user.getSenha())) {
    		return null;
    	}
    	return user;
    }
    
    private boolean isEmail(String value) {
    	return value.contains("@");
    }

    public String entrar() {
        Usuario user = isValidLogin(loginOrEmail, senha);

        if (user != null) {
		    usuarioController.setUser(user);
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		    request.getSession().setAttribute("user", user);
		    return "/pages/Home.xhtml";
		}
        displayInfoMessage("Senha ou Usuário inválido");
        return "/pages/login.xhtml";
    }
}
