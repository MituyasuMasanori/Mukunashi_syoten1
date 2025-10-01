<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ログイン画面</h1>
<form action="?" method="post">
ユーザー名:<input type="text" name="userId"><br>
パスワード:<input type="password" name="password"><br>
<input type="submit" name="index" value="認証チェック" formaction="Login">
<input type="submit" name="index" value="新規登録" formaction="Login">
<input type="submit" name="index" value="入力訂正" formaction="Login">
<%--nameとvalueは何でもいい --%>
<input type="submit" name="index" value="戻る" formaction="BookController">
<% if(request.getAttribute("message") != null){%>
	<p><%= request.getAttribute("message")%></p>
<%}%>
</form>
</body>
</html>