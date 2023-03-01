import java.util.List;

public class Venda {
	private Integer codigo;
	private List<Produto> itens;
	private Double valor;
	private Double comissaoSistema;
	private Empresa empresa;
	private Cliente cliente;

	public Venda(Integer código, List<Produto> itens, Double valor, Double comissaoSistema, Empresa empresa, Cliente cliente) {
		super();
		this.codigo = código;
		this.itens = itens;
		this.valor = valor;
		this.comissaoSistema = comissaoSistema;
		this.empresa = empresa;
		this.cliente = cliente;
	}

	public Venda() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getComissaoSistema() {
		return comissaoSistema;
	}

	public void setComissaoSistema(Double comissaoSistema) {
		this.comissaoSistema = comissaoSistema;
	}

	public void mostrarVenda() {
		System.out.println("************************************************************");
		System.out.printf("Venda de código - %d - CPF %s :\n", this.codigo, this.cliente.getCpf());
		this.itens.forEach(item -> {
			System.out.printf("%d - %s - R$ %.2f\n", item.getId(), item.getNome(), item.getPreco());
		});
		System.out.printf("Total Venda: R$ %.2f\n", this.valor);
		System.out.printf("Total Taxa a ser paga: R$ %.2f\n", this.comissaoSistema);
		System.out.printf("Total Líquido  para empresa: R$ %.2f\n",(this.valor - this.comissaoSistema));
		System.out.println("************************************************************");
	}

	public void mostrarCompra() {
		System.out.println("************************************************************");
		System.out.printf("\nCompra de código - %d - na empresa %s:\n", this.codigo, this.empresa.getNome());
		this.itens.forEach(item -> {
			System.out.printf("%d - %s - R$ %.2f\n", item.getId(), item.getNome(), item.getPreco());
		});
		System.out.printf("Total: R$\n" + this.valor);
		System.out.println("************************************************************");
	}
}
