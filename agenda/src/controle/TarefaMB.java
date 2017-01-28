package controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Tarefa;
import dominio.Usuario;
import dominio.dao.TarefaDAO;

@ManagedBean(name = "tarefaMB")
@RequestScoped
public class TarefaMB implements Serializable {

	private Tarefa tarefa = new Tarefa();
	private TarefaDAO dao = new TarefaDAO();
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginMB;

	private FormularioFiltro filtro = new FormularioFiltro();
	
	private List<Tarefa> tarefas = null;

	public List<Tarefa> getTarefas() {
		if (this.tarefas == null)
			this.tarefas = this.dao.lerTodos();

		return this.tarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}


	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public FormularioFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(FormularioFiltro filtro) {
		this.filtro = filtro;
	}

	/**
	 * 
	 */
	public String acaoListar() {
		return "tarefaListar";
	}

	/**
	 * 
	 */
	public String acaoFiltrar() {
		
		this.tarefas = this.dao.filtrar(this.filtro.getDescricao(), this.filtro.getData());

		return "tarefaListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao() {
		// limpar o objeto da página
		this.setTarefa(new Tarefa());
		this.getTarefa().setData(new Date());

		return "tarefaEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao() {
		Long id = JSFUtil.getParametroLong("itemId");
		Tarefa objetoDoBanco = this.dao.lerPorId(id);
		this.setTarefa(objetoDoBanco);

		return "tarefaEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar() {
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getTarefa().getId() != null)
				&& (this.getTarefa().getId().longValue() == 0))
			this.getTarefa().setId(null);
		
		Usuario usuario = this.loginMB.getUsuario();

		this.dao.salvar(this.getTarefa());
		// limpa a lista
		this.tarefas = null;

		// limpar o objeto da página
		this.setTarefa(new Tarefa());

		return "tarefaListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar() {
		// limpar o objeto da página
		this.setTarefa(new Tarefa());

		return "tarefaListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir() {
		Long id = JSFUtil.getParametroLong("itemId");
		Tarefa objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setTarefa(new Tarefa());
		// limpa a lista
		this.tarefas = null;

		return "tarefaListar";
	}

	/**
	 * CLASSE INTERNA PARA EDIÇÃO DOS CAMPOS DE FILTRO
	 */
	public class FormularioFiltro {
		private Date data;
		private String descricao;

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

	}

}
