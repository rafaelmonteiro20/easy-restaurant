var Easy = Easy || {};

Easy.ModalFormCategory = (function() {
	
	function ModalFormCategory() {
		this.modal = $('#modalFormCategory');
		this.btnSave = this.modal.find('.js-modal-save-category-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNameCategory = $('#nameCategory');
		this.containerMessageErro = $('.js-message-modal-form-category');
	}
	
	ModalFormCategory.prototype.init = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.btnSave.on('click', onClickBtnSave.bind(this));
	}
	
	function onModalShow() {
		this.inputNameCategory.focus();
	}
	
	function onModalClose() {
		this.inputNameCategory.val('');
		this.containerMessageErro.addClass('hidden');
		this.inputNameCategory.removeClass('is-invalid');
	}
	
	function onClickBtnSave() {
		var nameCategory = this.inputNameCategory.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ name: nameCategory }),
			error: onErrorSaveCategory.bind(this),
			success: onSaveCategory.bind(this)
		});
	}
	
	function onErrorSaveCategory(obj) {
		var message = obj.responseText;
		this.containerMessageErro.removeClass('hidden');
		this.containerMessageErro.html('<span>' + message + '</span>');
		this.inputNameCategory.addClass('is-invalid');
	}
	
	function onSaveCategory(category) {
		var comboCategory = $('#category');
		comboCategory.append('<option value=' + category.id + '>' + category.name + '</option>');
		comboCategory.val(category.id);
		this.modal.modal('hide');
	}
	
	return ModalFormCategory;
	
})();


$(function(){
	var modalFormCategory = new Easy.ModalFormCategory();
	modalFormCategory.init();
});
