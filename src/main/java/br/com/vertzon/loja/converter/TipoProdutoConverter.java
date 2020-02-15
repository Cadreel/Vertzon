package br.com.vertzon.loja.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vertzon.loja.dao.TipoProdutoDAO;
import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TipoProduto.class)
public class TipoProdutoConverter implements Converter {

	private TipoProdutoDAO tipoProdutoDAO;
	
	public TipoProdutoConverter() {
		this.tipoProdutoDAO = CDIServiceLocator.getBean(TipoProdutoDAO.class);
	}
	@SuppressWarnings("deprecation")
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoProduto retorno = null;
		
		if(value != null) {
			retorno = this.tipoProdutoDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Long codigo = ((TipoProduto) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return "";
	}

}
