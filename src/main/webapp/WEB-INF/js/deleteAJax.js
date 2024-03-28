$(function(){
	$('#deleteAJaxBtn').click(function(){
		var formData = new FormData($('#uploadFormAJax')[0]);
		
		$.ajax({
			type:'post',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			url:'/mini/user/deleteImage',
			data: formData,
			success:function(data){
				location.href='/mini/user/uploadList';
			},
			error:function(e){
				console.log(e);
			}
			
		});
	});
});