package com.easyrestaurant.venda;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.easyrestaurant.buider.ProdutoBuilder;
import com.easyrestaurant.model.Produto;

public class TabelaItensPedidoTest {

	private TabelaItensPedido tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensPedido();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() {
		Produto produto = new Produto();
		BigDecimal valor = new BigDecimal("8.90");
		produto.setValorUnitario(valor);
		
		tabelaItensVenda.adicionarItem(produto, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() {
		Produto p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("8.90").build();
		
		Produto p2 = new ProdutoBuilder()
				.comCodigo(2L).comValorUnitarioDe("4.50").build();
		
		tabelaItensVenda.adicionarItem(p1, 1);
		tabelaItensVenda.adicionarItem(p2, 2);
		
		assertEquals(new BigDecimal("17.90"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveManterQuantidadeDeItensAoAdicionarUmProdutoExistente() {
		Produto p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("4.50").build();
		
		Produto p2 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("4.50").build();
		
		tabelaItensVenda.adicionarItem(p1, 1);
		tabelaItensVenda.adicionarItem(p2, 1);
		
		assertEquals(1, tabelaItensVenda.quantidadeDeItens());
		assertEquals(new BigDecimal("9.00"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuatidadeItens() {
		Produto p1 = new ProdutoBuilder()
				.comCodigo(1L)
				.comValorUnitarioDe("4.50").build();
		
		tabelaItensVenda.adicionarItem(p1, 1);
		tabelaItensVenda.alterarQuantidade(p1, 4);
		
		assertEquals(1, tabelaItensVenda.quantidadeDeItens());
		assertEquals(new BigDecimal("18.00"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveRemoverItemDoPedido() {
		Produto p1 = new ProdutoBuilder()
				.comCodigo(1L).comValorUnitarioDe("5.00").build();
		
		Produto p2 = new ProdutoBuilder()
				.comCodigo(2L).comValorUnitarioDe("4.50").build();
		
		Produto p3 = new ProdutoBuilder()
				.comCodigo(3L).comValorUnitarioDe("3.90").build();
		
		tabelaItensVenda.adicionarItem(p1, 1);
		tabelaItensVenda.adicionarItem(p2, 2);
		tabelaItensVenda.adicionarItem(p3, 1);
		
		tabelaItensVenda.removerItem(p2);
		
		assertEquals(2, tabelaItensVenda.quantidadeDeItens());
		assertEquals(new BigDecimal("8.90"), tabelaItensVenda.getValorTotal());
	}
	
}
