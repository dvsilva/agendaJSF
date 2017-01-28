package dominio.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controle.util.JpaDAO;
import dominio.Tarefa;

public class TarefaDAO extends JpaDAO<Tarefa> implements Serializable {

	public TarefaDAO() {
		super();
	}

	public TarefaDAO(EntityManager manager) {
		super(manager);
	}

	public List<Tarefa> filtrar(String descricao, Date data) {
		
		List<Tarefa> retorno = null;
		StringBuilder jpql = new StringBuilder();
		String conector = " where ";
		jpql.append("from Tarefa t ");

		if (descricao != null)
		{
			jpql.append(" where t.descricao like :pDesc");
			conector = " and ";
		}

		if (data != null)
			jpql.append(conector).append(" t.data = :pData");

		jpql.append(" order by t.data, t.descricao");
		String comando = jpql.toString();
		
		TypedQuery<Tarefa> query = this.getEntityManager().createQuery(comando, Tarefa.class);

		if (descricao != null)
			query.setParameter("pDesc", "%" + descricao + "%");
		
		if (data != null)
			query.setParameter("pData", data);
		
		retorno = query.getResultList();
		
		return retorno;
	}

}












































/*


	public List<Tarefa> filtrarTarefas(String descricao, Date data)
	{
		List<Tarefa> retorno = null;
		String conector = " where ";
		StringBuilder jpql = new StringBuilder();
		jpql.append("from Tarefa t");
		
		if (descricao != null)
		{
			jpql.append(conector).append(" t.descricao like :pDesc");
			conector = " and ";
		}
		
		if (data != null)
			jpql.append(conector).append(" t.data = :pData");
		
		jpql.append(" order by t.descricao");
		String comando = jpql.toString();
		
		TypedQuery<Tarefa> query =  this.getEntityManager().createQuery(comando, Tarefa.class);
		if (descricao != null)
			query.setParameter("pDesc", "%" +descricao + "%");
		
		if (data != null)
			query.setParameter("pData", data);
		
		retorno = query.getResultList();
		
		return retorno;
	}







*/