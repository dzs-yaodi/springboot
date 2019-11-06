package com.xw.sts.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@Column
	private String imagepath;
    private String name;
    private String author;
    private String mClass;
    private String status;
    private String role;
    private String costart;
    private String plot;
    private String synopsis;
    private int bookstatus;
    private int pushuser;
    
    @Lob
	@Column(columnDefinition="blob")
    private byte[] imageblob;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getmClass() {
		return mClass;
	}

	public void setmClass(String mClass) {
		this.mClass = mClass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCostart() {
		return costart;
	}

	public void setCostart(String costart) {
		this.costart = costart;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getBookstatus() {
		return bookstatus;
	}

	public void setBookstatus(int bookstatus) {
		this.bookstatus = bookstatus;
	}

	public int getPushuser() {
		return pushuser;
	}

	public void setPushuser(int pushuser) {
		this.pushuser = pushuser;
	}

	public byte[] getImageblob() {
		return imageblob;
	}

	public void setImageblob(byte[] imageblob) {
		this.imageblob = imageblob;
	}
    
}
