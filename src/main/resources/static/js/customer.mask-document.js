var Easy = Easy || {};

Easy.MaskDocument = (function() {
	
	function MaskDocument() {
		this.radioTypePerson = $('.js-radio-type-person');
		this.labelDocument = $('[for=document]');
		this.inputDocument = $('#document');
	}
	
	MaskDocument.prototype.init = function() {
		this.radioTypePerson.on('change', onChangeTypePerson.bind(this));
		var selectedTypePerson = this.radioTypePerson.filter(':checked')[0];
		if(selectedTypePerson) {
			applyMask.call(this, $(selectedTypePerson));
		}
	}
	
	function onChangeTypePerson(event) {
		var selectedTypePerson = $(event.currentTarget);
		applyMask.call(this, selectedTypePerson);
		this.inputDocument.val('');
	}
	
	function applyMask(selectedTypePerson) {
		this.labelDocument.text(selectedTypePerson.data('document'));
		this.inputDocument.mask(selectedTypePerson.data('document-mask'));
		this.inputDocument.removeAttr('disabled');
	}
	
	return MaskDocument;
	
}());

$(function() {
	var MaskDocument = new Easy.MaskDocument();
	MaskDocument.init();
});
