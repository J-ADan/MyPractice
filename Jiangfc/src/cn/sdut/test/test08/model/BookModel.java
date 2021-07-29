package cn.sdut.test.test08.model;

public class BookModel {
	private int id;
	private	String bookname;
	private String bookwriter;
	private String yorn;
	private int count;
	
	
	
	public BookModel(String bookname) {
		super();
		this.bookname = bookname;
	}
	public BookModel(String bookname, String bookwriter) {
		super();
		this.bookname = bookname;
		this.bookwriter = bookwriter;
	}
	public BookModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getYorn() {
		return yorn;
	}
	public void setYorn(String yorn) {
		this.yorn = yorn;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
