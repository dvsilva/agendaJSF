package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Pessoa implements EntityIdSequencial, Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "PESSOA_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "PESSOA_ID", sequenceName = "SEQ_PESSOA", allocationSize = 1)
	private Long id;

	@Column(unique=true, nullable=false)
	private String nome;
	
	private String celular;
	private String telefoneFixo;
	private String email;
	private Endereco endereco;
	
	@ManyToOne
	private Cidade cidade;

	@ManyToOne
	private Grupo grupo;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCelular()
	{
		return celular;
	}

	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	public String getTelefoneFixo()
	{
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefone)
	{
		this.telefoneFixo = telefone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Endereco getEndereco()
	{
		if (endereco == null)
			endereco = new Endereco();

		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public Grupo getGrupo()
	{
		return grupo;
	}

	public void setGrupo(Grupo grupo)
	{
		this.grupo = grupo;
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
		Pessoa other = (Pessoa) obj;
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
		return this.nome;
	}

}
