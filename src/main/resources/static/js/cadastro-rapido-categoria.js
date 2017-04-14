var Easy = Easy || {};

Easy.CadastroRapidoCategoria = (function() {
	
	function CadastroRapidoCategoria() {
		this.modal = $('#modalCadastroRapidoCategoria');
		this.botaoSalvar = this.modal.find('.js-modal-salvar-categoria-botao');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeCategoria = $('#nomeCategoria');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-categoria');
	}
	
	CadastroRapidoCategoria.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeCategoria.focus();
	}
	
	function onModalClose() {
		this.inputNomeCategoria.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeCategoria = this.inputNomeCategoria.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeCategoria }),
			error: onErroSalvandoCategoria.bind(this),
			success: onCategoriaSalva.bind(this)
		});
	}
	
	function onErroSalvandoCategoria(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onCategoriaSalva(categoria) {
		var comboEstilo = $('#categoria');
		comboEstilo.append('<option value=' + categoria.codigo + '>' + categoria.nome + '</option>');
		comboEstilo.val(categoria.codigo);
		this.modal.modal('hide');
	}
	
	return CadastroRapidoCategoria;
	
})();


$(function(){
	var cadastroRapidoCategoria = new Easy.CadastroRapidoCategoria();
	cadastroRapidoCategoria.iniciar();
});