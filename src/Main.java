import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		// SIMULANDO BANCO DE DADOS

		BancoDados bancoDados = new BancoDados();
		bancoDados.iniciar();

		executar(bancoDados.getUsuarios(), bancoDados.getClientes(), bancoDados.getEmpresas(), bancoDados.getProdutos(),
				bancoDados.getCarrinho(), bancoDados.getVendas());
	}

	public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre com seu usuário e senha:");
		System.out.print("Usuário: ");
		String username = sc.next();
		System.out.print("Senha: ");
		String senha = sc.next();
		

		Optional<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username)).findFirst();
		if (usuariosSearch.isPresent()) {
			Usuario usuarioLogado = usuariosSearch.get();
			usuarioLogado.login(username, senha);
			if (usuarioLogado.estaLogado()) {
				while (usuarioLogado.estaLogado()) {
					System.out.println("Escolha uma opção para iniciar");

					if (usuarioLogado.isAdmin()) {

						System.out.println("1 - Listar todas as vendas");
						System.out.println("2 - Ver todos os produtos");
						System.out.println("3 - Listar todas as Empresas");
						System.out.println("0 - Deslogar");

						Integer escolha = sc.nextInt();

						switch (escolha) {
							case 1 -> {

								if (vendas.isEmpty()) {
									System.out.println("Nenhuma venda ou compra realizada.\n");
								}

								vendas.forEach(Venda::mostrarVenda);
							}
							case 2 -> {
								for (Empresa empresa : empresas) {
									System.out.printf("\nProduto da empresa %s - código %d\n", empresa.getNome(), empresa.getId());
									produtos.stream().filter(produto -> produto.getEmpresa().getId().equals(empresa.getId())).toList().forEach(prod -> {
										System.out.printf("%d - %s - R$ %.2f - %d unidades\n", prod.getId(), prod.getNome(), prod.getPreco(), prod.getQuantidade());
									});
								}
								System.out.println();
							}
							case 3 -> {
								empresas.forEach(empresa -> {
									System.out.printf("%d - %s - %s - Taxa: %.2f, Saldo: R$ %.2f\n", empresa.getId(),
											empresa.getNome(), empresa.getCnpj(), empresa.getTaxa(), empresa.getSaldo());
								});
								System.out.println();
							}
							case 0 -> {
								usuarioLogado.deslogar();
								executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
							}
						}
					}

					if (usuarioLogado.isEmpresa()) {
						System.out.println("1 - Listar vendas");
						System.out.println("2 - Ver produtos");
						System.out.println("0 - Deslogar");
						Integer escolha = sc.nextInt();

						switch (escolha) {
							case 1 -> {
								System.out.println();
								System.out.println("************************************************************");
								System.out.println("VENDAS EFETUADAS");

								vendas.forEach(venda -> {
									if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
										venda.mostrarVenda();
									}

								});
								System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
								System.out.println("************************************************************");
							}
							case 2 -> {
								System.out.println();
								System.out.println("************************************************************");
								System.out.println("PRODUTOS");

								produtos.forEach(produto -> {
									if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
										produto.mostrarProduto();
									}

								});
								System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
								System.out.println("************************************************************");
							}
							case 0 -> {
								usuarioLogado.deslogar();
								executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
							}
						}

					} else if(!usuarioLogado.isAdmin()) {
						System.out.println("1 - Relizar Compras");
						System.out.println("2 - Ver Compras");
						System.out.println("0 - Deslogar");
						Integer escolha = sc.nextInt();
						switch (escolha) {
							case 1 -> {
								System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
								empresas.forEach(x -> {
									System.out.println(x.getId() + " - " + x.getNome());
								});
								Integer escolhaEmpresa = sc.nextInt();
								Integer escolhaProduto = -1;
								do {
									System.out.println("Escolha os seus produtos: ");
									produtos.forEach(x -> {
										if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
											System.out.println(x.getId() + " - " + x.getNome());
										}
									});
									System.out.println("0 - Finalizar compra");
									escolhaProduto = sc.nextInt();
									for (Produto produtoSearch : produtos) {
										if (produtoSearch.getId().equals(escolhaProduto))
											carrinho.add(produtoSearch);
									}
								} while (escolhaProduto != 0);
								System.out.println("************************************************************");
								System.out.println("Resumo da compra: ");
								carrinho.forEach(item -> {
									if (item.getEmpresa().getId().equals(escolhaEmpresa)) {
										System.out.printf("%d - %s - R$ %.2f\n", item.getId(), item.getNome(), item.getPreco());
									}
								});
								Empresa empresaEscolhida = empresas.stream()
										.filter(x -> x.getId().equals(escolhaEmpresa)).findFirst().get();
								Cliente clienteLogado = clientes.stream()
										.filter(x -> x.getUsername().equals(usuarioLogado.getUsername())).findFirst().get();
								Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
								System.out.printf("Total: R$ %.2f\n", venda.getValor());
								System.out.println("************************************************************");
								carrinho.clear();
							}
							case 2 -> {
								System.out.println();
								System.out.println("************************************************************");
								System.out.println("COMPRAS EFETUADAS");
								vendas.forEach(venda -> {
									if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
										venda.mostrarCompra();
									}
								});
							}
							case 0 -> {
								usuarioLogado.deslogar();
								executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
							}
						}
					}
				}
			}
			else
				System.out.println("Senha incorreta");
		} else {
			System.out.println("Usuário não encontrado");
		}
	}

	public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
		Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
		Double comissaoSistema = total * empresa.getTaxa();
		int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCodigo() + 1;
		Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
		empresa.setSaldo(empresa.getSaldo() + total);
		vendas.add(venda);
		return venda;
	}
}
