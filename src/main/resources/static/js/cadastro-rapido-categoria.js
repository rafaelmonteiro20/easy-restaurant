$(function(){
	
	var modal = $('#modalCadastroRapidoCategoria');
	var botaoSalvar = modal.find('.js-modal-salvar-categoria-botao');
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() });
	
	var url = form.attr('action');
	var inputNomeCategoria = $('#nomeCategoria');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-categoria');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose);
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeCategoria.focus();
	}
	
	function onModalClose() {
		inputNomeCategoria.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeCategoria = inputNomeCategoria.val().trim();
		
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeCategoria }),
			error: onErroSalvandoCategoria,
			success: onCategoriaSalva
		});
	}
	
	function onErroSalvandoCategoria(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onCategoriaSalva(categoria) {
		var comboEstilo = $('#categoria');
		comboEstilo.append('<option value=' + categoria.codigo + '>' + categoria.nome + '</option>');
		comboEstilo.val(categoria.codigo);
		modal.modal('hide');
	}
	
});