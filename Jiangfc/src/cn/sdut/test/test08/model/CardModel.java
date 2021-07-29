package cn.sdut.test.test08.model;

public class CardModel {
	private int id;
	private String cardId;
	private String cardname;
	
	
	public CardModel(String cardId) {
		super();
		this.cardId = cardId;
	}


	public CardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CardModel(String cardId, String cardname) {
		super();
		this.cardId = cardId;
		this.cardname = cardname;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	
}
