package com.training;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class PublisherPOJO {
	private int pubId;
	private String pubName;
	
	public PublisherPOJO(){
		
	}
	
	public PublisherPOJO(String name) {
		this.pubName = name;
	}

	@Id
	@GeneratedValue
	@Column(name = "PUB_ID")
	public int getPubId() {
		return pubId;
	}

	public void setPubId(int pubId) {
		this.pubId = pubId;
	}

	@Column(name = "PUB_NAME")
	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	
}
