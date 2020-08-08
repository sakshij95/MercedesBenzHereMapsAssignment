package com.example.assignment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dto.Places;
import com.example.assignment.service.PlacesOfInterestService;

@RestController
public class PlacesOfInterestController {
	
	@Autowired
	private PlacesOfInterestService placesOfInterestService;
	
	@RequestMapping("/findPlacesOfInterest/{location}")
	@Cacheable("location")
	public ResponseEntity<Map<String, List<Places>>> findPlacesOfInterest(@PathVariable("location") String location) {
		return new ResponseEntity<>(placesOfInterestService.findPlacesOfInterest(location), HttpStatus.OK);
	}
	
}
