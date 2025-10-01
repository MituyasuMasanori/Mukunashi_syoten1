package honya;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Book;
import model.User;

@WebServlet("/Okurizyo")
//result.jspで購入するボタンを押した時にメールに添付する送り状をPDFで作成
public class PdfOkurizyo extends HttpServlet {
	//unixはUNIXエポックから経過したミリ秒であり保存したPDFの名前
	protected void doGet(HttpServletRequest request, HttpServletResponse response, long unix) throws ServletException, IOException {
		//作成したPDFを動的Webプロジェクトに保存するために、プロパティーのロケーションでFileクラスのオブジェクトを作成
		PdfDocument pdf = new PdfDocument(
				new PdfWriter(new File("../workspace20240415/Mukunashi_syoten_ver1/src/main/webapp/okurizyo/" + unix + ".pdf")));
		Document doc = new Document(pdf);
		PdfFont font = PdfFontFactory.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H");
		doc.setFont(font);
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		//日付を「xxxx/xx/xx」と出力する
		var dateFormat = DateTimeFormatter.ofPattern("y/M/d");
		//郵便番号と住所と日付をPDFに書く。
		doc.add(new Paragraph(loginUser.getSendAddress()).setFontSize(20)
				.add(new Text( "                "+LocalDate.now().format(dateFormat))));
		//送付先の名前をPDFに書く
		doc.add(new Paragraph(loginUser.getSendName() + "様").setFontSize(20));
		doc.add(new Paragraph("下記は請求書のバーコードです").setFontSize(16)
				.add(new Text( "         福岡市東区千早４丁目２４－１")));
		//「0123456789」と書かれたバーコードをユーザー名の下に作成する
		doc.add(createBarcode("0123456789", pdf));
		//文字を中央に表示する
		doc.add(new Paragraph("納品のご案内").setFontSize(24).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph("拝啓平素は格別のご哀願を賜りまして誠にありがとうございます。").setFontSize(11));
		//注文したユーザーの名前をPDFに書く
		doc.add(new Paragraph(loginUser.getUserName() + "様より依頼された商品を送付いたしますので、ご査収のほどよろしくお願いいたします。").setFontSize(11));
		Table table = new Table(new float[] {255, 70});
		String[] headers = {"書名", "購入冊数"};
		for(String header : headers) {
			Cell cell = new Cell();
			cell.setFontSize(12)
				.setBold()
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setTextAlignment(TextAlignment.CENTER)
				.add(new Paragraph(header));
			table.addHeaderCell(cell);
		}
		//セッションスコープから購入する本のリストをキャスト
		List<Book> list = (List<Book>)session.getAttribute("list");
		//購入する本の種類ごとに表の一行で情報を表示する
		for(Book bok: list) {
			//クラス型の配列は要素数の宣言とクラスの宣言を別々にする
			Cell[] dcell = new Cell[2];
			for(int i = 0; i < 2; i++) dcell[i] = new Cell();
			dcell[0].add(new Paragraph(bok.getTitle()));
			dcell[1].add(new Paragraph(String.valueOf(bok.getNum())));
			//一行分のセル二つをテーブルに加える
			for(int j = 0; j < 2; j++) table.addCell(dcell[j]);
		}
		doc.add(table);
		doc.add(new Paragraph("以上                                    送り主 "+loginUser.getUserName()).setFontSize(16));
		doc.close();
	}
	
	//バーコードの下に入れる文字と請求書のPDFを引数にする
	private static Cell createBarcode(String code, PdfDocument pdfDoc) {
		Barcode128 barcode = new Barcode128(pdfDoc);
		barcode.setCode(code);
		Color myColor = new DeviceRgb(255, 100, 50);
		PdfFormXObject barcodeObject = barcode.createFormXObject(myColor, null, pdfDoc);
		Cell cell = new Cell().add(new Image(barcodeObject));
		cell.setPaddingTop(20);
		cell.setPaddingRight(200);
		cell.setPaddingBottom(000);
		cell.setPaddingLeft(70);
		return cell;
	}
}