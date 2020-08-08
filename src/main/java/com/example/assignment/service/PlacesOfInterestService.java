package com.example.assignment.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.assignment.dto.Places;

public interface PlacesOfInterestService {
	
	public Map<String, List<Places>> findPlacesOfInterest(String location);
}
