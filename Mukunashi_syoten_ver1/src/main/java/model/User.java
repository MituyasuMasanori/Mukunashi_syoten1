package model;

import java.io.Serializable;

public class User implements Serializable {
	private String userId;
	private String password;
	private String userName;
	private String userAddress;
	private String userMail;
	//登録済み送付先件数
	private int num;
	private String name1;
	private String address1;
	private String mail1;
	private String name2;
	private String address2;
	private String mail2;
	private String name3;
	private String address3;
	private String mail3;
	private String name4;
	private String address4;
	private String mail4;
	private String name5;
	private String address5;
	private String mail5;
	//決定した送付先
	private String sendName;
	private String sendAddress;
	private String sendMail;

	public User() {}
	//ユーザーIDとパスワードがログインするための主キー
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	//ユーザーを新規登録する時
	public User(String userId, String password, String userName, String userAddress, String userMail) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userMail = userMail;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public String getUserMail() {
		return userMail;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getName1() {
		return name1;
	}
	public String getAddress1() {
		return address1;
	}
	public String getMail1() {
		return mail1;
	}
	public String getName2() {
		return name2;
	}
	public String getAddress2() {
		return address2;
	}
	public String getMail2() {
		return mail2;
	}
	public String getName3() {
		return name3;
	}
	public String getAddress3() {
		return address3;
	}
	public String getMail3() {
		return mail3;
	}
	public String getName4() {
		return name4;
	}
	public String getAddress4() {
		return address4;
	}
	public String getMail4() {
		return mail4;
	}
	public String getName5() {
		return name5;
	}
	public String getAddress5() {
		return address5;
	}
	public String getMail5() {
		return mail5;
	}
	
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public void setMail3(String mail3) {
		this.mail3 = mail3;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public void setMail4(String mail4) {
		this.mail4 = mail4;
	}
	public void setName5(String name5) {
		this.name5 = name5;
	}
	public void setAddress5(String address5) {
		this.address5 = address5;
	}
	public void setMail5(String mail5) {
		this.mail5 = mail5;
	}
	
	public String getSendName() {
		return sendName;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public String getSendMail() {
		return sendMail;
	}
	
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}
	
	//登録済送付先を削除するために、後の番号の氏名と住所を前に詰める
	public void update1() {
		setName1(getName2());
		setName2(getName3());
		setName3(getName4());
		setName4(getName5());
		setName5("");
		setAddress1(getAddress2());
		setAddress2(getAddress3());
		setAddress3(getAddress4());
		setAddress4(getAddress5());
		setAddress5("");
		setMail1(getMail2());
		setMail2(getMail3());
		setMail3(getMail4());
		setMail4(getMail5());
		setMail5("");
	}
	public void update2() {
		setName2(getName3());
		setName3(getName4());
		setName4(getName5());
		setName5("");
		setAddress2(getAddress3());
		setAddress3(getAddress4());
		setAddress4(getAddress5());
		setAddress5("");
		setMail2(getMail3());
		setMail3(getMail4());
		setMail4(getMail5());
		setMail5("");
	}
	public void update3() {
		setName3(getName4());
		setName4(getName5());
		setName5("");
		setAddress3(getAddress4());
		setAddress4(getAddress5());
		setAddress5("");
		setMail3(getMail4());
		setMail4(getMail5());
		setMail5("");
	}
	public void update4() {
		setName4(getName5());
		setName5("");
		setAddress4(getAddress5());
		setAddress5("");
		setMail4(getMail5());
		setMail5("");
	}
	public void update5() {
		setName5("");
		setAddress5("");
		setMail5("");
	}
}