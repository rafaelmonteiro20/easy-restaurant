var Easy = Easy || {};

Easy.UploadFoto = (function() {
	
	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlFotoProdutoTemplate = $('#foto-produto').html();
		this.template = Handlebars.compile(this.htmlFotoProdutoTemplate);
		
		this.containerFotoProduto = $('.js-container-foto-produto');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
			multiple : false,
			allow: '*.(jpg|jpeg|png)',
			url: this.containerFotoProduto.data('url-fotos'),
			complete: onUploadComplete.bind(this)
		}
		
		UIkit.upload(this.uploadDrop, settings);
	}
	
	function onUploadComplete(resposta) {
		var fotoDTO = resposta.responseJSON;
		
		this.inputNomeFoto.val(fotoDTO.nome);
		this.inputContentType.val(fotoDTO.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlFotoProduto = this.template({nomeFoto: fotoDTO.nome});
		this.containerFotoProduto.append(htmlFotoProduto);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-produto').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto;
	
})();

$(function() {
	var uploadFoto = new Easy.UploadFoto();
	uploadFoto.iniciar();
});