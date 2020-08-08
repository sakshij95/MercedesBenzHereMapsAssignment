package com.example.assignment.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Places implements Serializable{

	private static final long serialVersionUID = 2L;
	
	private Integer distance;
	
	private String title;
	
	public Places() {
		
	}
	
	public Places(Integer distance, String title) {
		this.distance = distance;
		this.title = title;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
