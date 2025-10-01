<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.sql.*,javax.naming.*,javax.sql.*,java.text.*"%>
<%-- Bookクラスとリストを扱うためのクラスをインポート --%>
<%@ page import="model.Book"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/script.js" charset="utf-8" defer></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>椋梨書店タイムセール</title>
</head>
<body>
<h2>椋梨書店タイムセール</h2>
<%
User loginUser = (User)session.getAttribute("loginUser");
if(loginUser != null){
%>
<h4><%= loginUser.getUserId() + "様ログイン中" %><a style="margin-left: 60px" href="UserController">ログアウト</a></h4>
<%} %>

<%--ここからBookControllerにリンクする場合だけリクエストパラメータを追加 --%>
<form method="POST" action="BookController?action=done">
<table class="table">
<thead>
<tr>
	<th>書名</th><th>価格</th><th>出版社</th><th>刊行年月日</th><th>購入冊数</th><th>在庫数量</th>
</tr>
</thead>
<tbody>

<%
//以前に1以上と入力した購入冊数を表示するため
List<Book> list = (List<Book>)session.getAttribute("list");	
//isbnと購入冊数のnameに本ごとに1ずつ異なるcntを付け加えて区別する
//listnumはlistの要素番号
int cnt = 0, listnum=0;
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
try(Connection db = ds.getConnection()){
	PreparedStatement ps = db.prepareStatement("SELECT * FROM book ORDER BY published DESC");
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		cnt++;
%>

	<tr>
		<td>
			<input type="hidden" name="isbn<%=cnt %>" value="<%=rs.getString("isbn") %>">
			<%=rs.getString("title") %>
		</td>
		<td>
			<%=rs.getInt("price") %>
		</td>
		<td>
			<%=rs.getString("publish") %>
		</td>
		<td>
			<%=rs.getString("published") %>
		</td>
		<td> <%--購入冊数は正の整数か0しか入力できない --%>
			<%--データベースから表示している本のisbnとlistの要素である1冊以上購入する本のisbnが一致する時に以前の入力内容を表示 --%>
			<input type="number" min="0" max="<%=rs.getInt("zaiko") %>" name="num<%=cnt %>" size="5" value="<%=(list == null || list.size() == 0)? 0 : rs.getString("isbn").equals(list.get(listnum).getIsbn())? list.get(listnum).getNum() : 0%>">
		</td>
		<td>
			<%=rs.getInt("zaiko") %>
		</td>
		<td style="color: red;">
			<%--赤色で購入冊数が在庫数量より多いか負の数か小数の場合にscript.jsからエラーと表示する --%>
		</td>
	</tr>
<%
		//listから購入冊数が1冊以上のisbnをデータベースから表示している時にlistの要素番号を増やす
		if(list != null && list.size() > 0 && rs.getString("isbn").equals(list.get(listnum).getIsbn())){
			listnum++;
			//listの要素番号がlistの範囲外にならないようにする
			if(listnum == list.size()) listnum--;
		}
	}
}
%>

</tbody>
</table>
<%--cntは画面に表示した本の数 --%>
<input type="hidden" name="cnt" value="<%=cnt %>">
<%--isbnと購入冊数と画面に表示した本の数を送信する --%>
<input type="submit" value="カートに入れる">
</form>
</body>
</html>