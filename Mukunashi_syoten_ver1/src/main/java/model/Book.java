package model;

import java.io.Serializable;

public class Book implements Serializable {
	
	private String isbn;
	private String title;
	private int price;
	private String publish;
	private String published;
	private int num;

	public Book() {/*コンストラクター*/}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}
	
	//購入冊数のゲッターとセッター
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}