<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<h1>新規ユーザー登録</h1>
<form method="post" action="?">
ユーザー名:<input type="text" name="userId"><br>
パスワード:<input type="password" name="password"><br>
氏名：<input type="text" name="name"><br>
〒：<input type="text" name="code">住所：<input type="text" name="address" size=30><br>
メールアドレス：<input type="text" name="mail"><br>
<button type="submit" name="index" value="登録" formaction="Login">登録</button>
<button type="submit" name="index" value="戻る" formaction="Login">戻る</button>
<button type="submit" name="index" value="キャンセル" formaction="Login">キャンセル</button>
<%--入力欄に空白がある状態で登録ボタンを押したら「入力欄に空白があります」と表示する --%>
<% if(request.getAttribute("message") != null){%>
	<p><%= request.getAttribute("message")%></p>
<%}%>
</body>
</html>