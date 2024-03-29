package com.allan.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allan.cursomc.domain.Categoria;
import com.allan.cursomc.domain.Cidade;
import com.allan.cursomc.domain.Cliente;
import com.allan.cursomc.domain.Endereco;
import com.allan.cursomc.domain.Estado;
import com.allan.cursomc.domain.Pagamento;
import com.allan.cursomc.domain.PagamentoComBoleto;
import com.allan.cursomc.domain.PagamentoComCartao;
import com.allan.cursomc.domain.Pedido;
import com.allan.cursomc.domain.Produto;
import com.allan.cursomc.domain.enums.EstadoPagamento;
import com.allan.cursomc.domain.enums.TipoCliente;
import com.allan.cursomc.repositeories.CategoriaRepository;
import com.allan.cursomc.repositeories.CidadeRepository;
import com.allan.cursomc.repositeories.ClienteRepository;
import com.allan.cursomc.repositeories.EnderecoRepository;
import com.allan.cursomc.repositeories.EstadoRepository;
import com.allan.cursomc.repositeories.PagamentoRepository;
import com.allan.cursomc.repositeories.PedidoRepository;
import com.allan.cursomc.repositeories.ProdutoRepository;

@SpringBootApplication
public class Cursomc1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public Cursomc1Application() {
		// TODO Auto-generated constructor stub
	}

	Cursomc1Application(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Cursomc1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Definindo formato de horas
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// Instanciando categoria
		Categoria cat1 = new Categoria(null, "Informatica");

		Categoria cat2 = new Categoria(null, "Escritorio");

		// Instanciando produto

		Produto p1 = new Produto(null, "Computador", 2000.00);

		Produto p2 = new Produto(null, "Impressora", 800.00);

		Produto p3 = new Produto(null, "Mouse", 80.00);

		// Instanciando Cidades e estados

		Estado est1 = new Estado(null, "Minas Gerais");

		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);

		Cidade c2 = new Cidade(null, "São Paulo", est2);

		Cidade c3 = new Cidade(null, "Campinas", est2);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "26855921155", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("882643825", "40028922"));

		Cliente cli2 = new Cliente(null, "Joao Jose", "joao@gmail.com", "7799654235", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("0110202908", "402949239"));

		Cliente cli3 = new Cliente(null, "Empreendimentos Company", "company@gmail.com", "55997634128",
				TipoCliente.PESSOAJURIDICA);
		cli3.getTelefones().addAll(Arrays.asList("98762906", "202899309"));

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "casa", "Jardim", "69502900", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua das Neves", "71", "apto", "Rosas", "24048944", cli2, c2);
		Endereco e3 = new Endereco(null, "Rua Cardoso", "21", "Loja", "Centro", "29048944", cli3, c3);
		Endereco e4 = new Endereco(null, "Rua Potigua", "77", "casa", "Amparo", "22578928", cli3, c3);

		// Instanciando Pedidos

		Pedido ped1 = new Pedido(null, sdf.parse("30/03/2024 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("12/04/2024 12:00"), cli2, e2);
		Pedido ped3 = new Pedido(null, sdf.parse("29/03/2024 20:30"), cli3, e4);
		Pedido ped4 = new Pedido(null, sdf.parse("29/03/2024 21:30"), cli3, e3);

		cli1.getEnderecos().addAll(Arrays.asList(e1));

		cli2.getEnderecos().addAll(Arrays.asList(e2));

		cli3.getEnderecos().addAll(Arrays.asList(e3, e4));

		// Instanciando pagamento

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, null);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/05/2024 14:40"), null);
		ped2.setPagamento(pag2);

		Pagamento pag3 = new PagamentoComCartao(null, EstadoPagamento.CANCELADO, ped3, 2);
		ped3.setPagamento(pag3);

		Pagamento pag4 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped4, sdf.parse("30/03/2024 00:00"),
				sdf.parse("29/03/2024 23:25"));
		ped4.setPagamento(pag4);

		// Adicionando pedidos aos clientes

		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		cli3.getPedidos().addAll(Arrays.asList(ped3, ped4));

		// Salvando os dados

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2, pag3, pag4));
	}

}
