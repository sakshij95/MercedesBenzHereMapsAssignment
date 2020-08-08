package com.example.assignment.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.assignment.dto.APIResponse;
import com.example.assignment.dto.DiscoverAPIResponse;
import com.example.assignment.service.HereMapsHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HereMapsHandlerImpl implements HereMapsHandler{
	
	@Value("${apiKey}")
	private String apiKey;
	
	@Value("${hereMaps.geoCode.baseUrl}")
	private String GEOCODE_BASE_URL;
	
	@Value("${hereMaps.explore.url}")
	private String EXPLORE_API_URL;

	@Override
	public String convertLocationCoordinates(String location) {
		final String uri = GEOCODE_BASE_URL + "/geocode";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
		        .queryParam("q", location)
		        .queryParam("apiKey", apiKey);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		return parseCoordinatesFromGeocodeAPI(response.getBody());
	}
	
	@Async
	@Override
	public CompletableFuture<DiscoverAPIResponse> getPlacesByLocation(String coordinates, String type) {
		final String uri = EXPLORE_API_URL;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
		        .queryParam("at", coordinates)
		        .queryParam("cat", type)
		        .queryParam("size", 3)
		        .queryParam("apiKey", apiKey);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<APIResponse> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        APIResponse.class);

		return CompletableFuture.completedFuture(response.getBody().getResults());
	}
	
	
	public String parseCoordinatesFromGeocodeAPI(String result) {
		String coordinates = "";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(result);
			JsonNode position = rootNode.path("items").get(0).path("position");
			String lat = position.path("lat").asText();
			String lon = position.path("lng").asText();
			coordinates = lat + "," + lon;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error Occured while converting location to geo coordinates.";
		}
		return coordinates;
	}
	
}
