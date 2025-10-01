<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.sql.*,javax.naming.*,javax.sql.*,java.text.*"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録済み送付先</title>
</head>
<body>
<h1>登録済み送付先</h1>
<form method="POST" action="?">
<%--addlessList.jspにフォワードされる時はすでにログインしている --%>
<% User loginUser = (User)session.getAttribute("loginUser");%>
<h4><%= loginUser.getUserId() + "様ログイン中" %>
<a style="margin-left: 30px" href="UserController">ログアウト</a></h4>

<table class="table">
<thead>
<tr>
	<th>送付先番号</th><th>名前と住所とメールアドレス</th><th>削除</th>
</tr>
</thead>
<tbody>
<%
int num;
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
try(Connection db = ds.getConnection()){
	//ログインしているユーザIDの情報をデータベースから取得
	PreparedStatement ps = db.prepareStatement("SELECT * FROM user_t where userId = ?");
	ps.setString(1, loginUser.getUserId());
	ResultSet rs = ps.executeQuery();
	//レコードは1件なのでレコードポインタを一回だけ動かす
	rs.next();
	//numは登録済み送付先の件数
	num = rs.getInt("num");
	//sendは前回選んだ送付先の番号
	String send = (String)session.getAttribute("send");
	//iは送付先番号になる
	for(int i = 1; i <= num; i++){
%>

	<%--送付先の一件目もしくは前回選んだ送付先にだけラジオボタンのチェックを入れる --%>
	<tr><% if((i == 1 && send == null) || (send != null && Integer.parseInt(send) == i)){ %>
		<td rowspan="3"><input type="radio" name="send" value="<%=i %>" checked><%=i %></td>
		<% }else{ %>
		<td rowspan="3"><input type="radio" name="send" value="<%=i %>"><%=i %></td>
		<% } %>
		<%--送付先の氏名と住所はデータベースで列名の後ろに番号を1から5まで付けている --%>
		<td><%=rs.getString("name"+i) %></td>
		<td rowspan="3"><input type="checkbox" name="delete" value="<%=i %>"></td>
	</tr>
	<tr><td><%=rs.getString("address" + i) %></td></tr>
	<tr><td><%=rs.getString("mail" + i) %></td></tr>
<% }
} %>
</tbody>
</table>

<%--登録済送付先の件数であるnumも表示せずに送信する --%>
<input type="hidden" name="num" value="<%=num %>">
<% if(num > 0){%>
	<button type="submit" name="controller" value="送付先決定" formaction="UserController">送付先決定</button>
<% }
if(num < 5){ %>
	<button type="submit" name="controller" value="新規追加" formaction="UserController">新規追加</button>
<% }
if(num > 0){ %>
	<button type="submit" name="controller" value="削除実行" formaction="UserController">削除実行</button>
<% } %>
<%--nameとvalueは何でもいい --%>
<button type="submit" name="controller" value="戻る" formaction="BookController">戻る</button>
</form>
</body>
</html>