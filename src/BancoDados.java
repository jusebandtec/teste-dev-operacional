import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class BancoDados {
    private List<Usuario> usuarios;
    private List<Cliente> clientes;
    private List<Empresa> empresas;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private List<Produto> carrinho;


    public void iniciar() {
        this.carrinho = new ArrayList<>();
        this.vendas = new ArrayList<>();

        Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
        Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
        Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

        Produto produto = new Produto(1, "Pão Frances", 5, 3.50, empresa);
        Produto produto2 = new Produto(2, "Coturno", 10, 400.0, empresa2);
        Produto produto3 = new Produto(3, "Jaqueta Jeans", 15, 150.0, empresa2);
        Produto produto4 = new Produto(4, "Calça Sarja", 15, 150.0, empresa2);
        Produto produto5 = new Produto(5, "Prato feito - Frango", 10, 25.0, empresa3);
        Produto produto6 = new Produto(6, "Prato feito - Carne", 10, 25.0, empresa3);
        Produto produto7 = new Produto(7, "Suco Natural", 30, 10.0, empresa3);
        Produto produto8 = new Produto(8, "Sonho", 5, 8.50, empresa);
        Produto produto9 = new Produto(9, "Croissant", 7, 6.50, empresa);
        Produto produto10 = new Produto(10, "Chá Gelado", 4, 5.50, empresa);

        Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
        Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

        Usuario usuario1 = new Usuario("admin", "1234", null, null);
        Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
        Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
        Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
        Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
        Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);


        this.usuarios = cadastrar(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6);
        this.clientes = cadastrar(cliente, cliente2);
        this.empresas = cadastrar(empresa, empresa2, empresa3);
        this.produtos = Arrays.asList(produto, produto2, produto3, produto4, produto5, produto6, produto7,
                produto8, produto9, produto10);
    }

    public static <T> void verificarExiste(T objeto, Predicate predicate, List<T> list) {
        if (list.stream().anyMatch(predicate))
            return;

        list.add(objeto);
    }

    @SafeVarargs
    public static <T> List<T> cadastrar(T... objetos) {
        List<T> base = new ArrayList<>();
        for (T objeto : objetos) {

            if (objeto instanceof Empresa) {
                Predicate<Empresa> predicate = empresa -> empresa.getCnpj().equals(((Empresa) objeto).getCnpj());
                verificarExiste(objeto, predicate, base);
            }

            if (objeto instanceof Usuario) {
                Predicate<Usuario> predicate = usuario -> usuario.getUsername().equals(((Usuario) objeto).getUsername());
                verificarExiste(objeto, predicate, base);
            }

            if (objeto instanceof Cliente) {
                Predicate<Cliente> predicate = cliente -> cliente.getCpf().equals(((Cliente) objeto).getCpf());
                verificarExiste(objeto, predicate, base);
            }
        }
        return base;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }
}


