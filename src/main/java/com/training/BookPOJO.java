package com.training;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOK")
@NamedQueries({ 
	@NamedQuery(name = "query1", query = "SELECT B.bookId, B.bookName, B.price from BookPOJO B"),
	@NamedQuery(name = "query2", query = "SELECT B.bookId, B.bookName, B.price, P.pubName FROM BookPOJO B, PublisherPOJO P WHERE B.publisher.pubId = P.pubId AND P.pubId = :pubId") 
})
public class BookPOJO {
	private int bookId;
	private String bookName;
	private PublisherPOJO publisher;
	private double price;

	public BookPOJO() {

	}

	public BookPOJO(String name) {
		this.bookName = name;
	}

	public BookPOJO(String name, PublisherPOJO publisher) {
		this.bookName = name;
		this.publisher = publisher;
	}

	@Id
	@GeneratedValue
	@Column(name = "BOOK_ID")
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@Column(name = "BOOK_NAME")
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PUB_ID")
	public PublisherPOJO getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherPOJO publisher) {
		this.publisher = publisher;
	}

	@Column(name = "BOOK_PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
