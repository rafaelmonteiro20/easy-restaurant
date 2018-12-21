var Easy = Easy || {};

Easy.UploadImage = (function() {
	
	function UploadImage() {
		this.inputNameImage = $('input[name=image]');
		this.htmlTemplate = $('#image-product').html();
		this.template = Handlebars.compile(this.htmlTemplate);
		this.containerImageProduct = $('.js-container-image-product');
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadImage.prototype.init = function () {
		var settings = {
			multiple : false,
   			allow: '*.(jpg|jpeg|png)',
   			url: this.containerImageProduct.data('url-images'),
   			complete: onUploadComplete.bind(this)
		};

   		UIkit.upload('.js-upload', settings);
   		
   		if(this.inputNameImage.val()) {
   			onUploadComplete.call(this, '');
   		}
	}
	
	function onUploadComplete(response) {
		console.log(response);
		
		var imageDTO = JSON.parse(response.response);
		this.inputNameImage.val(imageDTO.name);
		this.uploadDrop.addClass('hidden');
		
		var htmlImageProduct = this.template({nameImage: imageDTO.name});
		this.containerImageProduct.append(htmlImageProduct);
		
		$('.js-remove-image').on('click', onRemoveImage.bind(this));
	}
	
	function onRemoveImage() {
		$('.js-image-product').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNameImage.val('');
	}
	
	return UploadImage;
	
})();

$(function() {
	var uploadImage = new Easy.UploadImage();
	uploadImage.init();
});
