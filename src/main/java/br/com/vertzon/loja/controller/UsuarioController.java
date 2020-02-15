package br.com.vertzon.loja.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.vertzon.loja.modelo.Role;
import br.com.vertzon.loja.modelo.Usuario;

@SessionScoped
@ManagedBean(name="usuarioController")
public class UsuarioController extends AbstractController {

	public static final String INJECTION_NAME = "#{usuarioController}";
	
	private Usuario user;
	private boolean isDisable = false;
	
	public boolean isAdmin() {
		return Role.ADMIN.equals(user.getRole());
	}
	
	public boolean isDefaultUser() {
		return Role.USER.equals(user.getRole());
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}	
	
	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "login.xhtml";
	}
	
	public boolean acessoPermitido() {
		if(isAdmin()) {
			isDisable = false;
		}else {
			isDisable = true;
		}
		return isDisable;
	}
}
