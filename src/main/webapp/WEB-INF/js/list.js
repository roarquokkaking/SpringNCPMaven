$(function(){

	$.ajax({
		type:'post',
		url:'/mini/user/getUserList',
		data:{'pg':$('#pg').val()},
		dataType: 'json',
		success:function(data){
			console.log(JSON.stringify(data));
			
			$.each(data.list,function(index,items){
				/*$('<tr/>').append($('<td/>',{
				
				align: 'center',
				text:items.name
				 
				})).append($('<td/>',{
				
				align: 'center',
				text:items.id
				
				})).append($('<td/>',{
				
				align: 'center',
				text:items.pwd
				
				})).appendTo($('#userListTable'));*/
				
				var result = `<tr>`
							+`<td align="center">`+items.name+`</td>`
							+`<td align="center"><a href="#" class="idA">`+items.id+`</a></td>`
							+`<td align="center">`+items.pwd+`</td>`
							+`</tr>`;
							
				$('#userListTable').append(result);
				
				
				
			});//each
			
			$('#userPagingDiv').html(data.userPaging);
			
			$('.idA').click(function(){
				//alert('id='+$(this).text());
				//alert('name='+$(this).parent().prev().text());
				location.href= '/mini/user/updateForm?id='+$(this).text()+'&pg='+$('#pg').val();
				
			});
		},
		error:function(e){
			console.log(e);
		}
	
	});//ajax

});