package br.com.vertzon.loja.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vertzon.loja.dao.ProdutoDAO;
import br.com.vertzon.loja.modelo.Produto;
import br.com.vertzon.loja.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	private ProdutoDAO produtoDAO;
	
	public ProdutoConverter() {
		this.produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}
	
	@SuppressWarnings("deprecation")
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if(value != null) {
			retorno = this.produtoDAO.buscarPeloCodigo(new Long (value));
		}
		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Produto) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		return "";
	}

}
