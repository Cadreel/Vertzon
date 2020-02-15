package br.com.vertzon.loja.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vertzon.loja.dao.FabricanteDAO;
import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Fabricante.class)
public class FabricanteConverter implements Converter {

	private FabricanteDAO fabricanteDAO;
	
	public FabricanteConverter() {
		this.fabricanteDAO = CDIServiceLocator.getBean(FabricanteDAO.class);
	}
	
	@SuppressWarnings("deprecation")
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fabricante retorno = null;
		
		if(value != null) {
			retorno = this.fabricanteDAO.buscarPeloCodigo(new Long(value));
		}
		
		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Long codigo = ((Fabricante) value).getCodigo();
			String retorno = (codigo == null ? null :  codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
