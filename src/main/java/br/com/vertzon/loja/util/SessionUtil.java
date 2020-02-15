package br.com.vertzon.loja.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.vertzon.loja.modelo.Usuario;

public class SessionUtil {

	public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static String getUserName(){
        return ((Usuario)getSession().getAttribute("user")).getLogin();
    }
    
    public static Usuario getUser(){
        return ((Usuario)getSession().getAttribute("user"));
    }
}
