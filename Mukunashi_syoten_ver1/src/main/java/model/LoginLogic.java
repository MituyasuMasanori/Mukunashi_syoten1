package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginLogic {
	public boolean execute(User user) {
		//ユーザIDとパスワードがどちらとも入力されている場合
		if(user.getUserId()!="" && user.getPassword()!="") {
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
				try(Connection db = ds.getConnection()){
					//データベースからユーザIDに対応するパスワードを取得
					PreparedStatement ps = db.prepareStatement(
							"SELECT password FROM user_t where userId=?");
					ps.setString(1, user.getUserId());
					ResultSet rs = ps.executeQuery();
					//レコードは1件なのでレコードポインタを一回だけ動かす
					rs.next();
					//ユーザIDに対応するパスワードとindex.jspから送られたパスワードが一致するならログイン
					if(user.getPassword().equals(rs.getString("password"))) {return true;}
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}
		}
		//ログインできるのならここは処理しない
		return false;
	}
}