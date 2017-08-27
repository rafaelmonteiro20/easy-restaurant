Easy = Easy || {};

Easy.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-nome-cerveja-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuOuNome) {
				return '/easy-restaurant/produtos?skuOuNome=' + skuOuNome;
			},
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: function(nome, produto) {
					produto.valorFormatado = Easy.formatarMoeda(produto.valorUnitario);
					return this.template(produto);
				}.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());

$(function() {
	var autocomplete = new Easy.Autocomplete();
	autocomplete.iniciar();
})