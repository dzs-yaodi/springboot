package com.xw.sts.dto;

//收藏书籍
public class BookColletcDto {

	private int id;
	private int bookId;
	private int userId;
	private String bookName;
	private String imagepath;

	public BookColletcDto() {
		
	}

	public BookColletcDto(int id, int bookId, int userId, String bookName, String headString) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.bookName = bookName;
		this.imagepath = headString;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getHeadString() {
		return imagepath;
	}

	public void setHeadString(String headString) {
		this.imagepath = headString;
	}

}
