package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Cidade;

public class CidadeDAO extends JpaDAO<Cidade> {

	public CidadeDAO() {
		super();
	}

	public CidadeDAO(EntityManager manager) {
		super(manager);
	}

	public List<Cidade> filtrarPorUf(String uf) {
		System.out.println("... executando CidadeDAO.filtrarPorUf()");
		List<Cidade> resultado;

		StringBuilder comando = new StringBuilder("from Cidade c ");

		if (uf != null)
			comando.append(" where c.uf = :pUf");

		comando.append(" order by c.nome");

		Query consulta = this.getEntityManager()
				.createQuery(comando.toString());
		if (uf != null)
			consulta.setParameter("pUf", uf);

		try {
			resultado = (List<Cidade>) consulta.getResultList();
		} catch (NoResultException e) {
			resultado = null;
		}

		return resultado;

	}

}
