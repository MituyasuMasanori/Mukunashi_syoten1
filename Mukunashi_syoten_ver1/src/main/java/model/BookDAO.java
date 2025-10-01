package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDAO{
	
	//本の種類別に購入したい本のisbnと購入冊数を引数にして、購入したい本のインスタンスを返す
	public Book selectBook(String isbn, String num){
		Book bok = new Book();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
			try(Connection db = ds.getConnection()){
				//isbnが一致する本の情報をデータベースから取得
				PreparedStatement ps = db.prepareStatement(
						"SELECT * FROM book WHERE isbn = ?");
				ps.setString(1, isbn);
				ResultSet rs = ps.executeQuery();
				//レコードは1件なのでレコードポインタを一回だけ動かす
				rs.next();
				bok.setIsbn(rs.getString("isbn"));
				bok.setTitle(rs.getString("title"));
				bok.setPrice(rs.getInt("price"));
				bok.setPublish(rs.getString("publish"));
				bok.setPublished(rs.getString("published"));
				//request.getParameter()の戻り値であるnumはString型なのでint型に変換する
				bok.setNum(Integer.parseInt(num));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return bok;
	}
	
	//購入を決定した本の種類のインスタンスを引数にする
	public void reduceBook(Book bok) {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
			try(Connection db = ds.getConnection()){
				//isbnが一致する本の在庫をデータベースから取得
				PreparedStatement ps = db.prepareStatement(
						"SELECT zaiko FROM book WHERE isbn = ?");
				ps.setString(1, bok.getIsbn());
				ResultSet rs = ps.executeQuery();
				//レコードは1件なのでレコードポインタを一回だけ動かす
				rs.next();
				//購入する本の在庫数から購入冊数を引いた値を新たな在庫として取得
				int zaiko = rs.getInt("zaiko")- bok.getNum();
				//isbnが一致する本の在庫を新たな在庫に更新する
				ps = db.prepareStatement(
						"UPDATE book SET zaiko = ? WHERE isbn = ?");
				ps.setInt(1, zaiko);
				ps.setString(2, bok.getIsbn());
				ps.executeUpdate();
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}