$(function(){
	$.ajax({
		type: 'post',
		url: '/mini/user/getUploadList',
		dataType:'json',
		success:function(data){
			console.log(JSON.stringify(data));
			
			$.each(data,function(index,items){
				/*$('<tr/>').append($('<td/>',{
					align:'center',
					text:items.seq
				})).append($('<td/>',{
					align:'center'
					}).append($('<img/>',{
					src:'../storage/'+items.imageOriginalName,
					style:'width:50px;height:50px',
					alt:'gg'
				}))).append($('<td/>',{
					align:'center',
					text:items.imageName
				})).appendTo($('#uploadListTable'));*/
			
				//이미지는 NCP 에서 가져온다 . 
				var result = `<tr>`
							+`<td align="center"><input type="checkbox" name="check" id="check" value="`+items.seq+`">`+items.seq+`</td>`
							+`<td align="center"><a href="/mini/user/uploadView?seq=`+items.seq+`"><img id="image" src="https://kr.object.ncloudstorage.com/bitcamp-6th-bucket-102/storage/`+items.imageFileName+`"style="width:50px;height:50px"/></a></td>`
							+`<td align="center">`+items.imageName+`</td>`
							+`</tr>`;
							
				$('#uploadListTable').append(result);
			});//each
			
			// 전체 선택 / 전체 해제
			$('#all').click(function(){
				if($(this).prop('checked')){
					$('input[name="check"]').prop('checked',true);
				}else{
					$('input[name="check"]').prop('checked',false);
				}
			});
			
			//전체선택한 후 체크 1개라도 해제가 되면 all은 체크해제
			$('input[name="check"]').click(function(){
				$('#all').prop('checked',$('input[name="check"]').length==$('input[name="check"]:checked').length);
			});
			
			},
		error:function(e){
			console.log(e);
		}
	});//ajax

});


