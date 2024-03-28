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
	}
	th,td {
		padding: 5px;
	}
	
	
</style>
</head>
<body>
	<a href="/mini/"><img src="../image/스크린샷 2024-03-20 160335.png"/></a>
	<input type="hidden" id="seq" name="seq"value="${seq }">
	
	<table borde="1" frame="hsides" rules="rows">
		<tr>
			<td rowspan="3" width="300" height="200"><span id="imageSpan"></span></td>
			<td width="200">번호:${seq}</td>
		</tr>
		<tr>
			<td>상품명: <span id="imageNameSpan" ></span></td>
		</tr>
		<tr>
			<td>파일명: <span id="imageOriginalNameSpan" ></span></td>
		</tr>
		<tr>
			<td colspan="2" height="200"><span id="imageContentSpan"></span></td>
		</tr>
	</table>
	<div style="margin-top: 5px">
		<input type="button" value="목록" onclick="location.href='/mini/user/uploadList'">
		<input type="button" value="수정" onclick="location.href='/mini/user/uploadUpdateForm?seq=${seq}'">
	</div>
	<script src="http://code.jQuery.com/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/uploadView.js"></script>
</body>
</html>