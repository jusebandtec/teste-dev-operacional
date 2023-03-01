
public class Produto {
	private Integer id;
	private String nome;
	private Integer quantidade;
	private Double preco;
	private Empresa empresa;

	public Produto(Integer id,String nome, Integer quantidade, Double preco, Empresa empresa) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.empresa = empresa;
	}

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void mostrarProduto() {
		System.out.println("************************************************************");
		System.out.println("Código: " + this.id);
		System.out.println("Produto: " + this.nome);
		System.out.println("Quantidade em estoque: " + this.quantidade);
		System.out.println("Valor: R$" + this.preco);
		System.out.println("************************************************************");
	}

}
