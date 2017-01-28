package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dominio.Cidade;
import dominio.dao.CidadeDAO;

@FacesConverter(value="cidade-converter", forClass=Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente,
			String valor) {
	
		if (valor == null || valor.length() == 0)
			return null;
		
		Long id = null;
		try
		{
			id = new Long(valor);
		}
		catch (Exception e)
		{
			return null;
		}

		CidadeDAO dao = new CidadeDAO();
		Cidade cidade = dao.lerPorId(id);

		System.out.println("+++ executando CidadeConverter - " + valor + " - " + cidade);
		return cidade;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente,
			Object objeto) {

		if (objeto instanceof Cidade)
			return ((Cidade) objeto).getId().toString();

		return null;
	}

}
