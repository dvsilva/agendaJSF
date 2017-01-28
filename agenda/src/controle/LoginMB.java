package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.util.JSFUtil;
import dominio.Usuario;
import dominio.dao.UsuarioDAO;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean autenticado = false;
	private Usuario usuario = new Usuario(null, "(não autenticado)", null);

	private String login;
	private String senha;

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public boolean isAutenticado()
	{
		return autenticado;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	/**
	 * 
	 */
	public String acaoAbrirLogin()
	{
		return "login";
	}

	/**
	 * 
	 */
	public String acaoAutenticar()
	{
		UsuarioDAO dao = new UsuarioDAO();

		Usuario usuarioDoBanco = dao.lerPorLogin(this.getLogin());

		if (usuarioDoBanco == null)
		{
			JSFUtil.retornarMensagemErro("Usuário não existe.", null, null);
			return "login";
		}
		else if (usuarioDoBanco.senhaCorreta(this.getSenha()))
		{
			// guardar o obteto usuário
			this.setUsuario(usuarioDoBanco);
			this.autenticado = true;

			return "home";
		}
		else
		{
			JSFUtil.retornarMensagemErro("Senha inválida.", null, null);
			return "login";
		}
	}

	/**
	 * 
	 */
	public String acaoLogout()
	{
		this.usuario = new Usuario(null, "(não autenticado)", null);
		this.autenticado = false;
		this.login = null;
		this.senha = null;

		// encerrar a sessão atual
		JSFUtil.getHttpSession().invalidate();

		return "login";
	}

}
