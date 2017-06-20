<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function is_weixn() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

if (is_weixn()) {
	console.log(" 是来自微信内置浏览器")
} else {
	console.log("不是来自微信内置浏览器")
}
</script>
</head>
<body>
	<a href="/peony" style="position: fixed;top: 5px;left: 5px;">home</a>
	<br/>	
	
</body>
</html>