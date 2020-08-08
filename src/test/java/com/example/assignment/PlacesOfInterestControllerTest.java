package com.example.assignment;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.assignment.controller.PlacesOfInterestController;
import com.example.assignment.dto.Places;
import com.example.assignment.service.PlacesOfInterestService;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(PlacesOfInterestController.class)
public class PlacesOfInterestControllerTest {

	@MockBean
	private PlacesOfInterestService placesOfInterestService;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void findPlacesOfInterestTest() throws Exception {
		Map<String, List<Places>> map = new HashMap<>();
		Places restaurant1 = new Places(100, "Restaurant1");
		Places chargingStation = new Places(100, "ChargingStation1");
		Places parkingSpot = new Places(100, "ParkingSpot1");
		ArrayList<Places> restaurantList = new ArrayList(Arrays.asList(restaurant1));
		ArrayList<Places> charginStationList = new ArrayList(Arrays.asList(chargingStation));
		ArrayList<Places> parkingSpotList = new ArrayList(Arrays.asList(parkingSpot));
		map.put("restaurants", restaurantList);
		map.put("chargingStations", charginStationList);
		map.put("parkingLots", parkingSpotList);
		
		when(placesOfInterestService.findPlacesOfInterest(Mockito.anyString())).thenReturn(map);
		
		mvc.perform(get("/findPlacesOfInterest/"+ Mockito.anyString()))
			.andExpect(status().isOk());
	}
}
