package com.example.assignment.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoverAPIResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("items")
	private Places[] places;

	public Places[] getPlaces() {
		return places;
	}

	public void setPlaces(Places[] places) {
		this.places = places;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
