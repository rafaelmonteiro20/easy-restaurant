Easy.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
		this.uuid = $('#uuid').val();
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoProduto: item.codigo,
				uuid : this.uuid
			}
		});
		
		resposta.done(onItemAtualizado.bind(this));
	}

	function onItemAtualizado(html) {
		this.tabelaProdutosContainer.html(html);
		var quantidadeItemInput = $('.js-tabela-produto-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskMoney({ precision: 0, thousands: ''});
		
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if(quantidade <= 0) {
			input.val(1);
			quantidade = 1;
		}
		
		var codigoProduto = input.data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/' + codigoProduto,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid : this.uuid
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
			url: 'item/' + this.uuid + '/' + codigoProduto,
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