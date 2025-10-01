package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	//ログインしたユーザの登録済送付先をデータベースから取り出しUserインスタンスに追加
	public User setList(User loginUser){
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
			try(Connection db = ds.getConnection()){
				PreparedStatement ps = db.prepareStatement(
						"SELECT * FROM user_t where userId = ?");
				ps.setString(1, loginUser.getUserId());
				ResultSet rs = ps.executeQuery();
				//レコードは1件なのでレコードポインタを一回だけ動かす
				rs.next();
				loginUser.setUserName(rs.getString("userName"));
				loginUser.setUserAddress(rs.getString("userAddress"));
				loginUser.setUserMail(rs.getString("userMail"));
				loginUser.setName1(rs.getString("name1"));
				loginUser.setName2(rs.getString("name2"));
				loginUser.setName3(rs.getString("name3"));
				loginUser.setName4(rs.getString("name4"));
				loginUser.setName5(rs.getString("name5"));
				loginUser.setAddress1(rs.getString("address1"));
				loginUser.setAddress2(rs.getString("address2"));
				loginUser.setAddress3(rs.getString("address3"));
				loginUser.setAddress4(rs.getString("address4"));
				loginUser.setAddress5(rs.getString("address5"));
				loginUser.setMail1(rs.getString("mail1"));
				loginUser.setMail2(rs.getString("mail2"));
				loginUser.setMail3(rs.getString("mail3"));
				loginUser.setMail4(rs.getString("mail4"));
				loginUser.setMail5(rs.getString("mail5"));
			}
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return loginUser;
	}
	
	//Userインスタンスの情報を取り出しデータベースを更新
	public void updateList(User loginUser) {
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
			try(Connection db = ds.getConnection()){
				PreparedStatement ps = db.prepareStatement(
						"UPDATE user_t SET num=?,name1=?,name2=?,name3=?,name4=?,name5=?,address1=?,address2=?,address3=?,address4=?,address5=?,mail1=?,mail2=?,mail3=?,mail4=?,mail5=? where userId = ?");
				ps.setInt(1, loginUser.getNum());
				ps.setString(2, loginUser.getName1());
				ps.setString(3, loginUser.getName2());
				ps.setString(4, loginUser.getName3());
				ps.setString(5, loginUser.getName4());
				ps.setString(6, loginUser.getName5());
				ps.setString(7, loginUser.getAddress1());
				ps.setString(8, loginUser.getAddress2());
				ps.setString(9, loginUser.getAddress3());
				ps.setString(10, loginUser.getAddress4());
				ps.setString(11, loginUser.getAddress5());
				ps.setString(12, loginUser.getMail1());
				ps.setString(13, loginUser.getMail2());
				ps.setString(14, loginUser.getMail3());
				ps.setString(15, loginUser.getMail4());
				ps.setString(16, loginUser.getMail5());
				ps.setString(17, loginUser.getUserId());
				ps.executeUpdate();
			}
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}