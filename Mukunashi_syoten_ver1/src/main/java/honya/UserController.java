package honya;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
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
import model.UserDAO;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	//「ログアウトしてトップ画面に戻る」のリンクをクリックした場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープを破棄
		request.getSession().invalidate();
		//index.jspにフォワード
		this.getServletContext().getRequestDispatcher("/WEB-INF/honya/form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String controller = request.getParameter("controller");
		HttpSession session = request.getSession();
		//ログインしたユーザの個人情報をセッションスコープから取得
		User loginUser = (User)session.getAttribute("loginUser");
		
		//addlessList.jspで戻るボタンをクリックした場合は選んだ送付先の番号をセッションスコープに保存
		if(request.getParameter("send") != null) {
			session.setAttribute("send", request.getParameter("send"));
		}
		UserDAO dao = new UserDAO();
		
		//addressList.jspでボタンの送付先決定を選ぶ
		if(controller.equals("送付先決定")){
			//送付先番号の名前と住所を注文完了画面へ送る
			String send = request.getParameter("send");
			switch(send) {
				case "1" -> {
					loginUser.setSendName(loginUser.getName1());
					loginUser.setSendAddress(loginUser.getAddress1());
					loginUser.setSendMail(loginUser.getMail1());
				}
				case "2" -> {
					loginUser.setSendName(loginUser.getName2());
					loginUser.setSendAddress(loginUser.getAddress2());
					loginUser.setSendMail(loginUser.getMail2());
				}
				case "3" -> {
					loginUser.setSendName(loginUser.getName3());
					loginUser.setSendAddress(loginUser.getAddress3());
					loginUser.setSendMail(loginUser.getMail3());
				}
				case "4" -> {
					loginUser.setSendName(loginUser.getName4());
					loginUser.setSendAddress(loginUser.getAddress4());
					loginUser.setSendMail(loginUser.getMail4());
				}
				case "5" -> {
					loginUser.setSendName(loginUser.getName5());
					loginUser.setSendAddress(loginUser.getAddress5());
					loginUser.setSendMail(loginUser.getMail5());
				}
			}
			BookDAO Bdao = new BookDAO();
			List<Book> list = (List<Book>)session.getAttribute("list");	
			for(Book bok: list) {
				//購入する本の在庫数から購入冊数を引く
				Bdao.reduceBook(bok);
			}
			//UNIXエポックから経過したミリ秒をPDFの名前とする
			long unix = new Date().getTime();
			//請求書を作るクラスのメソッドを実行
			new PdfSeikyusho().doGet(request, response, unix);
			//送り状を作るクラスのメソッドを実行
			new PdfOkurizyo().doGet(request, response, unix);
			//請求書付きのメールを送信するためのクラスのメソッドを使うためにオブジェクトを作成
			Mail mail = new Mail();
			//初期化パラメータを読み込み
			mail.init(getServletConfig());
			Timer timer = new Timer(false);
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					try {
						//請求書付きのメールを送信する
						mail.doGet(request, response, unix, loginUser);
						//購入した本のリストと合計金額とメール送信のTimerTaskを持つセッションを破棄する
						session.removeAttribute("list");
						session.removeAttribute("sum");
						session.removeAttribute("task");
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				}
			};
			//請求書付きのメールを送信するのを５秒後に実行する
			timer.schedule(task, 5000);
			session.setAttribute("task", task);
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/payment.jsp").forward(request,response);
			
		//addlessList.jspでボタンの新規追加を選ぶかaddlessEntry.jspでボタンの入力訂正を選ぶ
		}else if(controller.equals("新規追加") || controller.equals("入力訂正")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessEntry.jsp").forward(request,response);
			
		//addlessList.jspでボタンの削除実行を選ぶかaddlessEntry.jspでボタンの戻るを選ぶ
		}else if(controller.equals("削除実行") || controller.equals("戻る")) {
			//addlessList.jspで削除にチェックを入れた送付先
			String[] delete = request.getParameterValues("delete");
			
			//addlessList.jspで一つでもチェックしていた場合
			if(delete != null) {
				//削除する送付先の番号の配列を昇順から降順に並べ替えた配列を作る
		        String[] reverseDelete = new String[delete.length];
		        for(int i = 0; i < delete.length; i++) {
		            reverseDelete[i] = delete[delete.length - i - 1];
		        }
				//numは現在の登録済み送付先件数
				int num = Integer.parseInt(request.getParameter("num"));
				//sendは選んだ送付先の番号
				int send = Integer.parseInt(request.getParameter("send"));
				for(String del:reverseDelete) {
					//登録済み送付先件数を一つ減らす
					loginUser.setNum(--num);
					//チェックした送付先番号の大きいほうから氏名と住所を消して、後の番号の氏名と住所を前に詰める
					switch(del) {
						case "1" -> loginUser.update1();
						case "2" -> loginUser.update2();
						case "3" -> loginUser.update3();
						case "4" -> loginUser.update4();
						case "5" -> loginUser.update5();
					}
					//選んだ送付先より番号が小さい送付先を削除した場合は番号を減らす
					if(Integer.parseInt(del) < send) {
						send--;
					//選んだ送付先と番号が同じ送付先を削除した場合は番号を1にする
					}else if(Integer.parseInt(del) == send) {
						send = 1;
					}
				}
				//セッションスコープの送付先番号を更新する
				session.setAttribute("send", Integer.toString(send));
				//ログインしたユーザの個人情報をデータベースで更新する
				dao.updateList(loginUser);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
		}
		
		//addlessEntry.jspでボタンの登録を選ぶ
		else if(controller.equals("登録")) {
			
			//入力欄のどれかが空白だった場合
			if(request.getParameter("name")=="" || request.getParameter("code")=="" || request.getParameter("address")=="" || request.getParameter("mail")=="") {
				//ログイン失敗時はsignUp.jspに戻って、「入力欄に空白があります」と表示する
				request.setAttribute("message", "入力欄に空白があります");
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessEntry.jsp").forward(request,response);
				
			}else {
				//登録済み送付先件数を一つ増やす
				int num = Integer.parseInt(request.getParameter("num"))+1;
				loginUser.setNum(num);
				//ログインしたユーザのインスタンスで登録済み送付先件数の番号の名前と住所を更新する
				switch(num) {
					case 1 -> {
						loginUser.setName1(request.getParameter("name"));
						//郵便番号と住所をまとめてユーザインスタンスにaddressとして保存する
						loginUser.setAddress1(request.getParameter("code") + "," + request.getParameter("address"));
						loginUser.setMail1(request.getParameter("mail"));
					}
					case 2 -> {
						loginUser.setName2(request.getParameter("name"));
						loginUser.setAddress2(request.getParameter("code") + "," + request.getParameter("address"));
						loginUser.setMail2(request.getParameter("mail"));
					}
					case 3 -> {
						loginUser.setName3(request.getParameter("name"));
						loginUser.setAddress3(request.getParameter("code") + "," + request.getParameter("address"));
						loginUser.setMail3(request.getParameter("mail"));
					}
					case 4 -> {
						loginUser.setName4(request.getParameter("name"));
						loginUser.setAddress4(request.getParameter("code") + "," + request.getParameter("address"));
						loginUser.setMail4(request.getParameter("mail"));
					}
					case 5 -> {
						loginUser.setName5(request.getParameter("name"));
						loginUser.setAddress5(request.getParameter("code") + "," + request.getParameter("address"));
						loginUser.setMail5(request.getParameter("mail"));
					}
				}
				//ログインしたユーザの個人情報をデータベースで更新する
				dao.updateList(loginUser);
				this.getServletContext().getRequestDispatcher("/WEB-INF/honya/addlessList.jsp").forward(request,response);
			}
		}
	}
}