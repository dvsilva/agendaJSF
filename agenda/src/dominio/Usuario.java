package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario implements EntityIdSequencial, Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "USUARIO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "USUARIO_ID", sequenceName = "SEQ_USUARIO", allocationSize = 1)
	private Long id;

	@Column(unique = true)
	private String login;

	private String nome;
	private String senha;

	/**
	 * 
	 */
	public Usuario(String login, String nome, String senha)
	{
		super();
		this.login = login;
		this.nome = nome;
		this.senha = senha;
	}

	/**
	 * 
	 */
	public Usuario()
	{
		super();
	}

	public boolean senhaCorreta(String senhaDigitada)
	{
		if (this.senha.equals(senhaDigitada))
			return true;
		else
			return false;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	@Override
	public int hashCode()
	{
		if (this.id == null)
			return 0;

		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return this.login;
	}

}
