<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規送付先登録</title>
</head>
<body>
<h1>新規送付先登録</h1>
<form method="POST" action="?">
氏名:<input type="text" name="name"><br>
〒：<input type="text" name="code">住所：<input type="text" name="address" size=30><br>
メールアドレス:<input type="text" name="mail"><br>
<button type="submit" name="controller" value="登録" formaction="UserController">登録</button>
<button type="submit" name="controller" value="戻る" formaction="UserController">戻る</button>
<button type="submit" name="controller" value="入力訂正" formaction="UserController">入力訂正</button>
<%--numは登録済み送付先件数 --%>
<input type="hidden" name="num" value="<%=request.getParameter("num") %>">
</form>
<%--入力欄に空白がある状態で登録ボタンを押したら「入力欄に空白があります」と表示する --%>
<% if(request.getAttribute("message") != null){%>
	<p><%= request.getAttribute("message")%></p>
<%}%>
</body>
</html>