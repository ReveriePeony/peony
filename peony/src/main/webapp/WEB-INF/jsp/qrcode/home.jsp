<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("qrcode", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QRcode Creater</title>
<style type="text/css">
	.body{margin: 0 auto;text-align: center;}
	.body div{margin: 5px auto;}
	.urlinp{width: 200px;height: 25px;}
	.urlbtn{height: 30px;border:1px solid #37a2f5;background-color: #37a2f5;color: white;}
	.qrimg{width: 300px;height: 300px;}
</style>
</head>
<body>
<a href="/peony" style="position: fixed;top: 5px;left: 5px;">home</a>
<div class="body">
	<div>
		<form action="/peony/QRcode/homeImg" method="post" enctype="multipart/form-data">
			<input type="text" name="url" class="urlinp"/>
			<input type="file" name="logo" class="urlinp"/>
			<!-- <input type="button" value="create" class="urlbtn"/> -->
			<input type="submit" class="urlbtn"/>
		</form>
	</div>
	<div>
		<img alt="" src="${bi }" class="qrimg">
		${bi }
	</div>
</div>
</body>
<script type="text/javascript" src="/peony/script/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		/* $('.urlbtn').on('click', function(){
			console.log('/peony/QRcode/create?url='+$('.urlinp').val());
			$('.qrimg').attr('src', '/peony/QRcode/create?url='+$('.urlinp').val());
		}); */
	});
</script>
</html>