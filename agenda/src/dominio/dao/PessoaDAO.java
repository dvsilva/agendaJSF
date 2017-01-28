package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Grupo;
import dominio.Pessoa;

public class PessoaDAO extends JpaDAO<Pessoa>
{

	public PessoaDAO()
	{
		super();
	}

	public PessoaDAO(EntityManager manager)
	{
		super(manager);
	}

	public Pessoa lerPorLogin(String nome)
	{
		Pessoa resultado;

		Query consulta = this.getEntityManager().createQuery("from Pessoa p where p.nome = :nome");
		consulta.setParameter("nome", nome);

		try
		{
			resultado = (Pessoa) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}

	@Override
	public List<Pessoa> lerTodos() {
		List<Pessoa> resultado;

		Query consulta = this.getEntityManager().createQuery("from Pessoa p order by p.nome");

		try
		{
			resultado = (List<Pessoa>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}
	
	public List<Pessoa> filtrarPorGrupo(Grupo grupo) {
		List<Pessoa> resultado;
		
		StringBuilder comando = new StringBuilder("from Pessoa p ");
		
		if (grupo != null)
			comando.append(" where p.grupo.id = :pGrupo");

		comando.append(" order by p.nome");

		Query consulta = this.getEntityManager().createQuery(comando.toString());
		if (grupo != null)
			consulta.setParameter("pGrupo", grupo.getId());

		try
		{
			resultado = (List<Pessoa>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;

	}
	
	

}
