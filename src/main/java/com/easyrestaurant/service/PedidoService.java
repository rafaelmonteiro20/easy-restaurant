package com.easyrestaurant.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.ItemPedido;
import com.easyrestaurant.model.Pedido;
import com.easyrestaurant.repository.Pedidos;

//@Service
public class PedidoService {
	
	@Autowired
	private Pedidos pedidos;

	@Transactional
	public void salvar(Pedido pedido) {
		if(pedido.isNovo())
			pedido.setDataCriacao(LocalDateTime.now());
	
		pedido.setValorTotal(calcularTotal(pedido));
		pedidos.save(pedido);
	}

	private BigDecimal calcularTotal(Pedido pedido) {
		return pedido.getItens().stream()
				.map(ItemPedido::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
