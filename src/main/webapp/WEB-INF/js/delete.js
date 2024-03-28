$(function(){
	//삭제 버튼
	$('#deleteBtn').click(function(){
		if(confirm('삭제 하실?')){
			$.ajax({
				type:'post',
				url:'/mini/user/delete',
				data:'id='+$('#id').val(),
				success:function(data){
					alert('회원정보 삭제완료');
					location.href='/mini/user/list'; 
					
				},
				error:function(e){
					console.log(e);
				}
			});
		}
	});
	
});