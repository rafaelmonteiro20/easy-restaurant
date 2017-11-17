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
		
		resposta.done(onItemAtualizado.bind(this));
	}

	function onItemAtualizado(html) {
		this.tabelaProdutosContainer.html(html);
		$('.js-tabela-produto-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		var codigoProduto = input.data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/' + codigoProduto,
			method: 'PUT',
			data: {
				quantidade: quantidade
			}
		});
		
		resposta.done(onItemAtualizado.bind(this));
	}

	function onDoubleClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}

	function onExclusaoItemClick(evento) {
		var input = $(evento.target);
		var codigoProduto = input.data('codigo-produto');
		var resposta = $.ajax({
			url: 'item/' + codigoProduto,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizado.bind(this));
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Easy.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Easy.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});