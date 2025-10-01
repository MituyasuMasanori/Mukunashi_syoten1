<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.sql.*,javax.naming.*,javax.sql.*,java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- Bookクラスとリストを扱うためのクラスをインポート --%>
<%@ page import="model.Book"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カートにあるもの</title>
</head>
<body>
<%
User loginUser = (User)session.getAttribute("loginUser");
if(loginUser != null){
%>
<h4><%= loginUser.getUserId() + "様ログイン中" %><a style="margin-left: 60px" href="UserController">ログアウト</a></h4>
<%} %>
<h3>カートにあるもの</h3>

<form method="POST" action="BookController">
<table class="table">
<thead>
<tr>
	<th>書名</th><th>価格</th><th>出版社</th><th>刊行年月日</th><th>購入冊数</th><th>購入取り消し</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${sessionScope['list']}">
	<tr>
		<td>${fn:escapeXml(item.title)}</td>
		<td>${fn:escapeXml(item.price)}</td>
		<td>${fn:escapeXml(item.publish)}</td>
		<td>${fn:escapeXml(item.published)}</td>
		<td>${fn:escapeXml(item.num)}</td>
		<%--購入取り消しにチェックした本のisbnを送信する --%>
		<td><input type="checkbox" name="remove" value="${fn:escapeXml(item.isbn)}"></td>
	</tr>
</c:forEach>
</tbody>
</table>

<input type="submit" value="チェックした本を購入取り消し">
<%--合計金額を計算するためにJavaの処理で、"list"オブジェクトをList(Book)でキャスト --%>
 <% List<Book> list = (List<Book>)session.getAttribute("list");	
 	int sum = 0;
 	//本ごとに価格×購入冊数を合計金額に加える
    for(Book bok: list) sum += bok.getPrice() * bok.getNum();
 %>
<%--form.jspに戻ると購入する本の個数が入力されている、合計金額を青色で表示する --%>
<h3>合計金額：<span style="color: blue;"><%=sum %></span>円 <a href="BookController">戻る</a></h3>
</form>

<%
//本を一冊でも選んでいた場合に「購入する」ボタンを表示する
if(sum > 0){
%>
	<form method="POST" action="BookController?action=pdf">
	<input type="hidden" name="sum" value="<%=sum %>">
	<%--合計金額を送信する --%>
	<input type="submit" value="購入する">
	</form>
<%
}
%>
</body>
</html>