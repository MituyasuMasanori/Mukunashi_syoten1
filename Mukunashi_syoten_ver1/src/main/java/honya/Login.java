package honya;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;
import model.User;
import model.UserDAO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	//http://localhost:8080/Mukunashi_syoten_ver1/Loginで検索した場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしていれば送付先選択画面へフォワード
		if(request.getSession().getAttribute("loginUser") != null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
			
		//ログインしていなければログイン画面へフォワード
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/login.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String index = request.getParameter("index");
		HttpSession session = request.getSession();
		
		//login.jspでボタンの新規登録かキャンセルを選んだ場合
		if(index.equals("新規登録") || index.equals("キャンセル")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/signUp.jsp").forward(request,response);
			
		//signUp.jspでボタンの登録を選んだ場合
		}else if(index.equals("登録")) {
			//入力欄のどれかが空白だった場合
			if(request.getParameter("userId")=="" || request.getParameter("password")=="" || request.getParameter("name")=="" || request.getParameter("code")=="" || request.getParameter("address")=="" || request.getParameter("mail")=="") {
				//ログイン失敗時はsignUp.jspに戻って、「入力欄に空白があります」と表示する
				request.setAttribute("message", "入力欄に空白があります");
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/signUp.jsp").forward(request,response);
				
			}else {
				try {
					Context context = new InitialContext();
					DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/abcdpool");
					try(Connection db = ds.getConnection()){
						//データベースからユーザIDに対応するパスワードを取得
						PreparedStatement ps = db.prepareStatement(
								"INSERT INTO user_t(userId,password,userName,userAddress,userMail,num) values(?,?,?,?,?,0)");
						ps.setString(1, request.getParameter("userId"));
						ps.setString(2, request.getParameter("password"));
						ps.setString(3, request.getParameter("name"));
						//郵便番号と住所をまとめてデータベースにuserAddressとして登録する
						ps.setString(4, request.getParameter("code") + "," + request.getParameter("address"));
						ps.setString(5, request.getParameter("mail"));
						ps.executeUpdate();
					}
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
				//新規登録するユーザーのIDとパスワードと名前と住所とメールアドレスでUserインスタンスを作り、セッションスコープに保存
				User loginUser = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"), request.getParameter("code") + "," + request.getParameter("address"), request.getParameter("mail"));
				session.setAttribute("loginUser", loginUser);
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
			}
			
		//login.jspでボタンの認証チェックを選んだ場合
		}else if(index.equals("認証チェック")){
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			//Userインスタンス(ユーザー情報)の生成
			User user = new User(userId, password);
			//ログイン処理
			LoginLogic loginLogic = new LoginLogic();
			boolean isLogin = loginLogic.execute(user);
			
			//ログイン成功時の処理
			if(isLogin) {
				//UserインスタンスにユーザIDごとの登録済送付先を追加
				UserDAO dao = new UserDAO();
				user = dao.setList(user);
				//ユーザ情報をセッションスコープに保存
				session.setAttribute("loginUser", user);
				//ログイン中なら請求書とメールを作成する
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
				
			}else {
				//ログイン失敗時はlogin.jspに戻って、「ログインに失敗しました」と表示する
				request.setAttribute("message", "ログインに失敗しました");
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/login.jsp").forward(request,response);
			}
			
		//signUp.jspでボタンの戻るを選んだ場合かlogin.jspでボタンのキャンセルを選んだ場合
		}else if(index.equals("戻る") || index.equals("入力訂正")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/login.jsp").forward(request,response);
		}
	}
}