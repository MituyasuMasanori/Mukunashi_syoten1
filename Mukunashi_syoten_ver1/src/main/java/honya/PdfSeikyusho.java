package honya;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
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

@WebServlet("/PdfSeikyusho")
//result.jspで購入するボタンを押した時にメールに添付する請求書をPDFで作成
public class PdfSeikyusho extends HttpServlet {
	//unixはUNIXエポックから経過したミリ秒であり保存したPDFの名前
	protected void doGet(HttpServletRequest request, HttpServletResponse response, long unix) throws ServletException, IOException {
		//作成したPDFを動的Webプロジェクトに保存するために、プロパティーのロケーションでFileクラスのオブジェクトを作成
		PdfDocument pdf = new PdfDocument(
				new PdfWriter(new File("../workspace20240415/Mukunashi_syoten_ver1/src/main/webapp/seikyusho/" + unix + ".pdf")));
		Document doc = new Document(pdf);
		PdfFont font = PdfFontFactory.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H");
		doc.setFont(font);
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		//ユーザーの名前をPDFに書く
		doc.add(new Paragraph(loginUser.getUserName() + "様").setFontSize(24)
				.add(new Text("                             椋梨書店").setFontSize(20)));
		Table table = new Table(new float[] {255, 50, 70});
		String[] headers = {"書名", "価格", "購入冊数"};
		for(String header : headers) {
			Cell cell = new Cell();
			cell.setFontSize(12)
				.setBold()
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setTextAlignment(TextAlignment.CENTER)
				.add(new Paragraph(header));
			table.addHeaderCell(cell);
		}
		//セッションスコープから購入する本のリストと合計金額をキャスト
		List<Book> list = (List<Book>)session.getAttribute("list");
		String sum = (String)session.getAttribute("sum");
		//購入する本の種類ごとに表の一行で情報を表示する
		for(Book bok: list) {
			//クラス型の配列は要素数の宣言とクラスの宣言を別々にする
			Cell[] dcell = new Cell[3];
			for(int i = 0; i < 3; i++) dcell[i] = new Cell();
			dcell[0].add(new Paragraph(bok.getTitle()));
			dcell[1].add(new Paragraph(String.valueOf(bok.getPrice())));
			dcell[2].add(new Paragraph(String.valueOf(bok.getNum())));
			//一行分のセル三つをテーブルに加える
			for(int j = 0; j < 3; j++) table.addCell(dcell[j]);
		}
		doc.add(table);
		doc.add(new Paragraph("合計金額：").setFontSize(24)
				.add(
					//合計金額の数値のTextだけ青色にする
					new Text(sum)
					.setFontColor(new DeviceRgb(0, 0, 255))
					).add(new Text("円")
				));
		//「0123456789」と書かれたバーコードを表の下に作成する
		doc.add(createBarcode("0123456789", pdf));
		//椋梨書店のURLでQRコードを作成
		createQRcode("http://localhost:8080/Mukunashi_syoten_ver1/BookController",pdf);
		doc.close();
	}
	
	//バーコードの下に入れる文字と請求書のPDFを引数にする
	private static Cell createBarcode(String code, PdfDocument pdfDoc) {
		Barcode128 barcode = new Barcode128(pdfDoc);
		barcode.setCode(code);
		Color myColor = new DeviceRgb(255, 100, 50);
		PdfFormXObject barcodeObject = barcode.createFormXObject(myColor, null, pdfDoc);
		Cell cell = new Cell().add(new Image(barcodeObject));
		cell.setPaddingTop(000);
		cell.setPaddingRight(200);
		cell.setPaddingBottom(000);
		cell.setPaddingLeft(200);
		return cell;
	}
	
	//QLコードが表すURLと請求書のPDFを引数にする
	private static void createQRcode(String url, PdfDocument pdfDocument) {
		PdfPage pdfPage = pdfDocument.getFirstPage();
		PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
		Rectangle rect = new Rectangle(250, 760, 50, 50);
		
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url);
		PdfFormXObject pdfFormXObject =
				barcodeQRCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
				Image qrCodeImage =
						new Image(pdfFormXObject).setWidth(rect.getWidth()).
							setHeight(rect.getHeight());
		
		Canvas qrCanvas = new Canvas(pdfCanvas, rect);
		qrCanvas.add(qrCodeImage);
		qrCanvas.close();
	}
}