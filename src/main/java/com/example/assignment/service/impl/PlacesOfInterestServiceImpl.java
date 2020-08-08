package com.example.assignment.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.dto.Places;
import com.example.assignment.service.HereMapsHandler;
import com.example.assignment.service.PlacesOfInterestService;

@Service
public class PlacesOfInterestServiceImpl implements PlacesOfInterestService{
	
	@Autowired
	private HereMapsHandler hereMapsHandler;
	
	@Override
	public Map<String, List<Places>> findPlacesOfInterest(String location) {
		Map<String, List<Places>> map = new HashMap<>();
		//Convert location to lat-long
		String coordinates = hereMapsHandler.convertLocationCoordinates(location);
		try {
			//Call Here Maps API
			List<Places> restaurants = Arrays.asList(hereMapsHandler.getPlacesByLocation(coordinates, "restaurant").get().getPlaces());
			List<Places> evChargingStations = Arrays.asList(hereMapsHandler.getPlacesByLocation(coordinates, "ev-charging-station").get().getPlaces());
			List<Places> parkingLots = Arrays.asList(hereMapsHandler.getPlacesByLocation(coordinates, "parking-facility").get().getPlaces());
			//Convert the response into proper format
			
			map.put("restaurants", restaurants);
			map.put("evChargingStations", evChargingStations);
			map.put("parkingLots", parkingLots);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Return result
		return map;
	}
}
