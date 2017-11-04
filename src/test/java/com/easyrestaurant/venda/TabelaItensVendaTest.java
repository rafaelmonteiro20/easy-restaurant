package com.easyrestaurant.venda;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.easyrestaurant.model.Produto;

public class TabelaItensVendaTest {

	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda();
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
		Produto p1 = new Produto();
		BigDecimal v1 = new BigDecimal("8.90");
		p1.setValorUnitario(v1);
		
		Produto p2 = new Produto();
		BigDecimal v2 = new BigDecimal("4.50");
		p2.setValorUnitario(v2);
		
		tabelaItensVenda.adicionarItem(p1, 1);
		tabelaItensVenda.adicionarItem(p2, 2);
		
		assertEquals(new BigDecimal("17.90"), tabelaItensVenda.getValorTotal());
	}
	
}
