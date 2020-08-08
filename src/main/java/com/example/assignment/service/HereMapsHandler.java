package com.example.assignment.service;

import java.util.concurrent.CompletableFuture;

import com.example.assignment.dto.DiscoverAPIResponse;

public interface HereMapsHandler {

	public String convertLocationCoordinates(String location);
	
	public CompletableFuture<DiscoverAPIResponse> getPlacesByLocation(String coordinates, String type);
}
