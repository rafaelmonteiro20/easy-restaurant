package com.easyrestaurant.session;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.easyrestaurant.buider.ProdutoBuilder;
import com.easyrestaurant.model.Product;

public class TabelaItensPedidoTest {

	private TabelaItensPedido tabelaItensPedido;
	
	@Before
	public void setUp() {
		this.tabelaItensPedido = new TabelaItensPedido("1");
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() {
		assertEquals(BigDecimal.ZERO, tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() {
		Product produto = new Product();
		BigDecimal valor = new BigDecimal("8.90");
		produto.setValorUnitario(valor);
		
		tabelaItensPedido.adicionarItem(produto, 1);
		
		assertEquals(valor, tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() {
		Product p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("8.90").build();
		
		Product p2 = new ProdutoBuilder()
				.comCodigo(2L).comValorUnitarioDe("4.50").build();
		
		tabelaItensPedido.adicionarItem(p1, 1);
		tabelaItensPedido.adicionarItem(p2, 2);
		
		assertEquals(new BigDecimal("17.90"), tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveManterQuantidadeDeItensAoAdicionarUmProdutoExistente() {
		Product p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("4.50").build();
		
		Product p2 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("4.50").build();
		
		tabelaItensPedido.adicionarItem(p1, 1);
		tabelaItensPedido.adicionarItem(p2, 1);
		
		assertEquals(1, tabelaItensPedido.quantidadeDeItens());
		assertEquals(new BigDecimal("9.00"), tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuatidadeItens() {
		Product p1 = new ProdutoBuilder()
				.comCodigo(1L)
				.comValorUnitarioDe("4.50").build();
		
		tabelaItensPedido.adicionarItem(p1, 1);
		tabelaItensPedido.alterarQuantidade(p1, 4);
		
		assertEquals(1, tabelaItensPedido.quantidadeDeItens());
		assertEquals(new BigDecimal("18.00"), tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveRemoverItemDoPedido() {
		Product p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("5.00").build();
		
		Product p2 = new ProdutoBuilder()
				.comCodigo(2L).comValorUnitarioDe("4.50").build();
		
		Product p3 = new ProdutoBuilder()
				.comCodigo(3L).comValorUnitarioDe("3.90").build();
		
		tabelaItensPedido.adicionarItem(p1, 1);
		tabelaItensPedido.adicionarItem(p2, 2);
		tabelaItensPedido.adicionarItem(p3, 1);
		
		tabelaItensPedido.removerItem(p2);
		
		assertEquals(2, tabelaItensPedido.quantidadeDeItens());
		assertEquals(new BigDecimal("8.90"), tabelaItensPedido.getValorTotal());
	}
	
	@Test
	public void deveMudarQuantidadeDoItemParaUmQuandoForNegativo() {
		Product p1 = new ProdutoBuilder()
				.comCodigo(-5L).comValorUnitarioDe("8.90").build();
		
		tabelaItensPedido.adicionarItem(p1, 1);
		
		assertEquals(1, tabelaItensPedido.quantidadeDeItens());
		assertEquals(new BigDecimal("8.90"), tabelaItensPedido.getValorTotal());
	}
	
}
