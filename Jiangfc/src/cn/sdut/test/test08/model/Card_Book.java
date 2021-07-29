package cn.sdut.test.test08.model;

public class Card_Book {
	private int id;
	private String cardid;
	private String cardname;
	private String bookname;
	private String bookwriter;

	public Card_Book(String cardid, String cardname, String bookname, String bookwriter) {
		super();
		this.cardid = cardid;
		this.cardname = cardname;
		this.bookname = bookname;
		this.bookwriter = bookwriter;
	}

	public Card_Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookwriter() {
		return bookwriter;
	}

	public void setBookwriter(String bookwriter) {
		this.bookwriter = bookwriter;
	}

}
