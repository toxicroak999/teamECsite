<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<title>エラー画面</title>
	<meta http-equiv="refresh" content="3;URL='HomeAction'"/>
</head>
<body>
	<s:include value="header.jsp"/>
		<div id="contents">
			<h1>エラーが発生しました。<br>ホーム画面へ遷移します。</h1>
		</div>
	<s:include value="footer.jsp"/>
</body>
</html>