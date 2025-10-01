package honya;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Book;
import model.BookDAO;
import model.User;

@WebServlet("/BookController")
public class BookController extends HttpServlet {

	//http://localhost:8080/Mukunashi_syoten_ver1/BookController
	//クライアントがURLで検索した場合やresult.jspかpayment.jspで「戻る」を押した場合かlogin.jspでキャンセルした場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//payment.jspで「戻る」を押した場合かlogin.jspでキャンセルした場合はログイン情報以外破棄
		if(session.getAttribute("sum") != null) {
			//購入した本のリストと合計金額とメール送信のTimerTaskを持つセッションを破棄する
			session.removeAttribute("list");
			session.removeAttribute("sum");
			session.removeAttribute("task");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/honya/form.jsp").forward(request, response);
	}

	//form.jspで「カートに入れる」を押した場合やresult.jspで「チェックした本を購入取り消し」や「購入する」を押した場合
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//result.jspでチェックした本のisbn
		String[] remove = request.getParameterValues("remove");
		//form.jspからカートに入れた場合は中身がdoneそうでなければnull
		String action = request.getParameter("action");
		
		// String actionがnullならその後のelse ifでエラーになってしまう
		if(action == null) {
			action = "no";
		}
		
		//result.jspで一つでもチェックしていた場合
		if(remove != null) {
			//セッションスコープのリストをBook型のリストにキャスト
			List<Book> list = (List<Book>)session.getAttribute("list");	
			for(String r: remove) {
				for(Book bok: list) {
					//チェックした本をリストから見つける
					if(bok.getIsbn().equals(r)) {
						//チェックした本をリストから削除
						list.remove(list.indexOf(bok));
						break;
					}
				}
			}
			//セッションスコープのリストを作り直す
			session.removeAttribute("list");
			session.setAttribute("list", list);
			
		//form.jspからカートに入れた場合
		}else if(action.equals("done")){
			//cntはform.jspで表示した本の数
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			//Book型のリストを宣言
			List<Book> list = new ArrayList<>();
			BookDAO dao = new BookDAO();
			//dao.selectBook(String isbn, String num)で本ごとにBeanを作成して返す
			for(int i = 1; i <= cnt; i++){
				//購入冊数が0でも空白でもない本の場合
				if((request.getParameter("num" + i)).indexOf("0") != 0 && !request.getParameter("num" + i).equals("")) {
					//Book型のリストに購入する本の情報と冊数を加える
					list.add(dao.selectBook(request.getParameter("isbn" + i), request.getParameter("num" + i)));
				}
			}
			//買い物カゴはセッションスコープで作成
			session.setAttribute("list", list);
		}
		
		//result.jspで「購入する」ボタンを押した場合
		if(action.equals("pdf")) {
			//セッションスコープからユーザー情報を取得
			User loginUser = (User)session.getAttribute("loginUser");
			
			//result.jspで購入するボタンを押した直後
			if(session.getAttribute("sum") == null) {
				//合計金額をセッションスコープに入れる
				session.setAttribute("sum", request.getParameter("sum"));
			}
			
			//ログインしていない場合はaddlessList.jspではなくlogin.jspへフォワード
			if(loginUser == null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/login.jsp").forward(request,response);
			}else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
			}
		//result.jspで何もチェックせずに購入取り消し、またはpayment.jspで購入キャンセルを押した場合はすぐここへ。
		//またはlogin.jspやaddlessList.jspで戻るボタンをクリックした場合はすぐここへ
		}else{
			
			//addlessList.jspで戻るボタンをクリックした場合は選んだ送付先の番号をセッションスコープに保存
			if(request.getParameter("send") != null) {
				session.setAttribute("send", request.getParameter("send"));
			}
			
			//payment.jspで「購入をキャンセル」を押した場合
			else if(session.getAttribute("task") != null) {
				//メールを送信するTimerTaskを終了する
				TimerTask task = (TimerTask)session.getAttribute("task");
				task.cancel();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/result.jsp").forward(request,response);
		}
	}
}