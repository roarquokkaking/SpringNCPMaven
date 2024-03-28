$(function(){
	$.ajax({
		type: 'post',
		url: '/mini/user/getUploadImage',
		data:{'seq':$('#seq').val()},
		dataType:'json',
		success:function(data){
			console.log(JSON.stringify(data));
			
			var result=`<img src="https://kr.object.ncloudstorage.com/bitcamp-6th-bucket-102/storage/`+data.imageFileName+`"width="150" height="150"/>`;
			
			$('#imageName').val(data.imageName);
			$('#imageContent').html(data.imageContent);
			$('#showImgList').html(result);
		},
		error:function(e){
			console.log(e);
		}
	});
	
	
	$('#uploadUpdateBtn').click(function(){
		var formData = new FormData($('#uploadUpdateFormAJax')[0]);
	
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			url: '/mini/user/uploadUpdate',
			data:formData,
			success:function(data){
				location.href='/mini/user/uploadList';
			},
			error:function(e){
				console.log(e);
			}
		});
	
	});
});