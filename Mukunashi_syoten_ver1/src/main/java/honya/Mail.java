package honya;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class Mail extends HttpServlet {
	//メールサーバーの情報を格納するためのプライベート変数
	private String host = null;
	private String port = null;
	private String user = null;
	private String password = null;
	private String charset = null;
	
	//サーブレットの初期化時に初期化パラメータを読み込み
	@Override
	//configはサーブレット初期化パラメーターにアクセスする手段を提供
	public void init(ServletConfig config) throws ServletException{
		//HttpServletクラスでもともと定義されたinitメソッドを実行
		super.init(config);
		//appはユーザー間で共有可能な情報
		ServletContext app = config.getServletContext();
		//web.xmlの<param-name>から<param-value>を取得
		this.host = app.getInitParameter("smtp.host");
		this.port = app.getInitParameter("smtp.port");
		//userとpasswordはパスワード認証のためのユーザー名とパスワード
		this.user = app.getInitParameter("smtp.user");
		this.password = app.getInitParameter("smtp.password");
		//charesetはメールの文章の文字符号化方式
		this.charset = app.getInitParameter("smtp.charset");
	}
	
	//unixはUNIXエポックから経過したミリ秒であり保存するPDFの名前、logiUserはログインしたユーザーのインスタンス
	protected void doGet(HttpServletRequest request, HttpServletResponse response, long unix, User loginUser) throws ServletException, IOException {
		//送信時のパラメータ情報を設定
		Properties props = new Properties();
		//メールサーバーのホスト名
		props.setProperty("mail.smtp.host", this.host);
		//メールサーバーのポート番号
		props.setProperty("mail.smtp.port", this.port);
		//ユーザー認証を有効にするか
		props.setProperty("mail.smtp.auth", "true");
		//connectiontimeoutはサーバーとの接続が確立するまでに許容する時間
		props.setProperty("mail.smtp.connectiontimeout", "60000");
		//timeoutはサーバーが送信するのに許容する時間
		props.setProperty("mail.smtp.timeout", "60000");
		//メールサーバーへのセッションを確立
		Session session = Session.getInstance(props, new Authenticator() {
			//パスワード認証のための情報のオブジェクトで、認証クラスのオブジェクトを作成し、セッションオブジェクトに組み込む
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		//請求書を送るメールを作成
		try {
			MimeMessage message = new MimeMessage(session);
			//MIMEマルチパートを準備
			MimeMultipart multipart = new MimeMultipart();
			
			//テキスト本体を設定
			MimeBodyPart part1 = new MimeBodyPart();
			//テキストを設定
			part1.setText(loginUser.getUserName() + "様へ椋梨書店から請求書です。", charset);
			//個別のデータが入ったMimeBodyPartをMimeMultipartに梱包する
			multipart.addBodyPart(part1);
			
			//添付ファイルを準備
			MimeBodyPart part2 = new MimeBodyPart();
			//データを取得するためのハンドラーを設定
			DataHandler handler = new DataHandler(
					//動的Webプロジェクトに保存したPDFを添付するために、プロパティーのロケーションでFileクラスのオブジェクトを作成
					new FileDataSource(new File("../workspace20240415/Mukunashi_syoten_ver1/src/main/webapp/seikyusho/" + unix + ".pdf")));
			//データを設定
			part2.setDataHandler(handler);
			//添付ファイルに画像の名前を設定
			part2.setFileName(MimeUtility.encodeWord(handler.getName()));
			multipart.addBodyPart(part2);
			
			//送信先／元を設定
			message.setFrom(new InternetAddress(
					"mukunashi@example.com", "椋梨書店", charset));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					loginUser.getUserMail(), loginUser.getUserName(), charset));
			message.setSubject("椋梨書店の請求書", charset);
			//メール本体にマルチパートを設定
			message.setContent(multipart);
			Transport.send(message);
		}catch(MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//送り状を送るメールを作成
		try {
			MimeMessage message = new MimeMessage(session);
			//MIMEマルチパートを準備
			MimeMultipart multipart = new MimeMultipart();
			
			//テキスト本体を設定
			MimeBodyPart part1 = new MimeBodyPart();
			//テキストを設定
			part1.setText(loginUser.getSendName() + "様へ椋梨書店から送り状です。", charset);
			//個別のデータが入ったMimeBodyPartをMimeMultipartに梱包する
			multipart.addBodyPart(part1);
			
			//添付ファイルを準備
			MimeBodyPart part2 = new MimeBodyPart();
			//データを取得するためのハンドラーを設定
			DataHandler handler = new DataHandler(
					//動的Webプロジェクトに保存したPDFを添付するために、プロパティーのロケーションでFileクラスのオブジェクトを作成
					new FileDataSource(new File("../workspace20240415/Mukunashi_syoten_ver1/src/main/webapp/okurizyo/" + unix + ".pdf")));
			//データを設定
			part2.setDataHandler(handler);
			//添付ファイルに画像の名前を設定
			part2.setFileName(MimeUtility.encodeWord(handler.getName()));
			multipart.addBodyPart(part2);
			
			//送信先／元を設定
			message.setFrom(new InternetAddress(
					"mukunashi@example.com", "椋梨書店", charset));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					loginUser.getSendMail(), loginUser.getSendName(), charset));
			message.setSubject("椋梨書店の送り状", charset);
			//メール本体にマルチパートを設定
			message.setContent(multipart);
			Transport.send(message);
		}catch(MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}