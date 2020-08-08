package com.example.assignment.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse implements Serializable {
	
	private static final long serialVersionUID = 3L;

	DiscoverAPIResponse results;

	public DiscoverAPIResponse getResults() {
		return results;
	}

	public void setResults(DiscoverAPIResponse results) {
		this.results = results;
	}
	
}
