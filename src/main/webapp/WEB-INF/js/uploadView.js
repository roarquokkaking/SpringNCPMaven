$(function(){
	$.ajax({
		type: 'post',
		url: '/mini/user/getUploadImage',
		data:{'seq':$('#seq').val()},
		dataType:'json',
		success:function(data){
			console.log(JSON.stringify(data));
			
			var result=`<img src="https://kr.object.ncloudstorage.com/bitcamp-6th-bucket-102/storage/`+data.imageFileName+`"width="200" height="200"/>`;
			
			$('#imageSpan').html(result);
			$('#imageOriginalNameSpan').html(data.imageOriginalName);
			$('#imageNameSpan').html(data.imageName);
			$('#imageContentSpan').html(data.imageContent);
		},
		error:function(e){
			console.log(e);
		}
	});
});