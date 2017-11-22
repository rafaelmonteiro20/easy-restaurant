Easy.Pedido = (function() {
	
	function Pedido(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.valorTotalBox = $('.js-valor-total-box');
	}
	
	Pedido.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		var valorTotal = valorTotalItens == null ? 0 : valorTotalItens;
		this.valorTotalBox.html(Easy.formatarMoeda(valorTotal));
	}
	
	return Pedido;
	
}());

$(function() {
	
	var autocomplete = new Easy.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Easy.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var pedido = new Easy.Pedido(tabelaItens);
	pedido.iniciar();
	
});