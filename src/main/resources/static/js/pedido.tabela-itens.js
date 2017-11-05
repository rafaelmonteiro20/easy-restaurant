Easy.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoProduto: item.codigo
			}
		});
		
		resposta.done(onItemAdicionado.bind(this));
		
		function onItemAdicionado(html) {
			this.tabelaProdutosContainer.html(html);
		}
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Easy.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Easy.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});