import java.time.Duration;
import java.time.LocalDateTime;

public class Usuario {
	private String username;
	private String senha;
	private Cliente cliente;
	private Empresa empresa;
	private final int tempoExpiracao = 10;
	private LocalDateTime inicioSecao;

	public Usuario() {
		super();
	}

	public Usuario(String username, String senha, Cliente cliente, Empresa empresa) {
		super();
		this.username = username;
		this.senha = senha;
		this.cliente = cliente;
		this.empresa = empresa;
	}

	public boolean isAdmin() {
		return this.empresa == null && this.cliente == null;
	}

	public boolean isEmpresa() {
		return this.empresa != null;
	}

	public void login(String usuario, String senha) {
		if (this.username.equals(usuario) && this.senha.equals(senha)) {
			this.inicioSecao = LocalDateTime.now();
		}
	}

	public boolean estaLogado() {
		if (this.inicioSecao != null) {
			Duration duracoSecao = Duration.between(this.inicioSecao, LocalDateTime.now());
			if (duracoSecao.toMinutes() < this.tempoExpiracao) {
				this.inicioSecao = LocalDateTime.now();
				return true;
			} else {
				deslogar();
			}
		}
		return false;
	}

	public void deslogar() {
		this.inicioSecao = null;
	}

	public boolean IsCliente() {
		return this.cliente != null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
