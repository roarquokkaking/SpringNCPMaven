<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse;
		width: 600px;
	}
	th,td{
		padding: 5px;
		width: 200px;
	}
	#currentPaging{
		border:1px solid black;
		color:red;
		cursor:pointer;
		font-size:15pt;
		margin:4px;
		padding:1px 8px; 
	}
	#currentPaging:hover{
		
		text-decoration: underline;
	}
	#paging{
		font-size:15pt;
		color: black;	
		cursor:pointer;
		margin:5px;
		padding:5px;
	}
	#paging:hover{
		text-decoration: underline;
	}
</style>
</head>
<body>
	<input type="text" id="pg" value="${pg }"/><br>
	
	<table border="1" frame="hsides" role="rows" id="userListTable">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
		</tr>
		<a href="/mini/"><img src="../image/스크린샷 2024-03-20 160335.png"/></a>
		<!-- 동적처리 -->
	</table>
	<div id="userPagingDiv" style="width: 600px; text-align: center; margin-top: 10px" ></div>
<script src="http://code.jQuery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/list.js"></script>
<script type="text/javascript">
function userPaging(pg){
	location.href="/mini/user/list?pg="+pg;
}
</script>
</body>
</html>