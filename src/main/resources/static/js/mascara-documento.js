var Easy = Easy || {};

Easy.MascaraDocumento = (function() {
	
	function MascaraDocumento() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelDocumento = $('[for=documento]');
		this.inputDocumento = $('#documento');
	}
	
	MascaraDocumento.prototype.iniciar = function() {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento) {
		var tipoPessoaSelecionada = $(evento.currentTarget);
		
		this.labelDocumento.text(tipoPessoaSelecionada.data('documento'));
		this.inputDocumento.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputDocumento.val('');
		this.inputDocumento.removeAttr('disabled');
	}
	
	return MascaraDocumento;
	
}());

$(function() {
	var mascaraDocumento = new Easy.MascaraDocumento();
	mascaraDocumento.iniciar();
});