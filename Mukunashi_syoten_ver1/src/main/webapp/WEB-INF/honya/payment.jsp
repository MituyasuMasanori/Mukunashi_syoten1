<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.TimerTask" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/script2.js" charset="utf-8" defer></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>注文完了</title>
</head>
<body>
<%--請求書をPDFで表示するが、ブラウザの「戻る」をクリックすればここに戻る --%>
<h1>注文完了しました。</h1>
<% User loginUser = (User)session.getAttribute("loginUser");%>
<h4><%= loginUser.getUserId() + "様ログイン中" %>
<a style="margin-left: 30px" href="UserController">ログアウト</a></h4>
<%--result.jspに戻ったら購入冊数はすべて0になる --%>
<a href="BookController">本を選ぶ画面に戻る</a>

<em><%--script2.jspで「5秒以内なら注文をキャンセルできます」の5をカウントダウン --%></em>
<form method="POST" action="BookController">
<span><%--script2.jspで「購入をキャンセルする」ボタンを表示するが、このボタンは五秒後に消える --%></span>
<hr>
<%--合計金額を改めて表示する --%>
<strong>合計金額：<%=session.getAttribute("sum")%>円</strong><br>

<%--送付先を改めて表示する --%>
<table class="table">
<thead>
<tr>
	<th>送付先</th><th>名前</th><th>住所</th><th>メールアドレス</th>
</tr>
</thead>
<tbody>
<tr>
	<td></td><td><%=loginUser.getSendName() %></td><td><%=loginUser.getSendAddress() %></td><td><%=loginUser.getSendMail() %></td>
</tr>
</tbody>
</table>

<%--購入した本のリストを改めて表示する --%>
<table class="table">
<thead>
<tr>
	<th>注文</th><th>書名</th><th>価格</th><th>出版社</th><th>刊行年月日</th><th>購入冊数</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${sessionScope['list']}">
	<tr>
		<td></td>
		<td>${fn:escapeXml(item.title)}</td>
		<td>${fn:escapeXml(item.price)}</td>
		<td>${fn:escapeXml(item.publish)}</td>
		<td>${fn:escapeXml(item.published)}</td>
		<td>${fn:escapeXml(item.num)}</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
</body>
</html>